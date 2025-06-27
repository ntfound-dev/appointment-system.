document.addEventListener('DOMContentLoaded', () => {
    // --- Router Sederhana ---
    if (window.location.pathname.endsWith('login.html') || window.location.pathname === '/') {
        handleLoginPage();
    } else if (window.location.pathname.endsWith('doctors.html')) {
        handleDoctorPage();
    }

    // --- Logika Halaman Login ---
    function handleLoginPage() {
        const loginForm = document.getElementById('login-form');
        if (!loginForm) return;

        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const messageEl = document.getElementById('message');
            const loginButton = document.getElementById('login-button');
            
            loginButton.disabled = true;
            loginButton.textContent = 'Memproses...';
            messageEl.style.display = 'none';

            try {
                const response = await fetch('/api/auth/login', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ email, password })
                });
                
                if (!response.ok) {
                    const errorData = await response.json().catch(() => ({}));
                    throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                localStorage.setItem('jwtToken', data.token);
                window.location.href = '/doctors.html';
            } catch (error) {
                console.error('Login Error:', error);
                messageEl.textContent = 'Login gagal. Periksa kembali email dan password Anda.';
                messageEl.className = 'message error';
                messageEl.style.display = 'block';
                loginButton.disabled = false;
                loginButton.textContent = 'Masuk';
            }
        });
    }

    // --- Logika Halaman Dokter ---
    function handleDoctorPage() {
        const token = localStorage.getItem('jwtToken');
        if (!token) {
            window.location.href = '/login.html';
            return;
        }
        setupDoctorPage(token);
    }

    function setupDoctorPage(token) {
        const logoutButton = document.getElementById('logout-button');
        if (logoutButton) {
            logoutButton.addEventListener('click', () => {
                localStorage.removeItem('jwtToken');
                window.location.href = '/login.html';
            });
        }
        fetchDoctors(token);
        setupModal(token);
    }

    async function fetchDoctors(token) {
        const doctorListContainer = document.getElementById('doctor-list');
        try {
            const response = await fetch('/api/doctors', {
                headers: { 'Authorization': `Bearer ${token}` }
            });
            if (!response.ok) throw new Error('Gagal memuat dokter.');
            const doctors = await response.json();
            displayDoctors(doctors);
        } catch (error) {
            console.error('Fetch Doctors Error:', error);
            doctorListContainer.innerHTML = `<p class="message error">Tidak dapat memuat data dokter.</p>`;
        }
    }

    function displayDoctors(doctors) {
        const doctorListContainer = document.getElementById('doctor-list');
        doctorListContainer.innerHTML = '';
        if (!doctors || doctors.length === 0) {
            doctorListContainer.innerHTML = '<p>Tidak ada dokter yang tersedia.</p>';
            return;
        }
        doctors.forEach(doctor => {
            const doctorCard = document.createElement('div');
            doctorCard.className = 'doctor-card';
            doctorCard.innerHTML = `
                <h2>${doctor.name}</h2>
                <p>Spesialis: ${doctor.specialty}</p>
                <button class="btn-schedule btn" data-doctor-id="${doctor.id}" data-doctor-name="${doctor.name}">Lihat Jadwal</button>
            `;
            doctorListContainer.appendChild(doctorCard);
        });
    }

    // --- Logika Modal ---
    function setupModal(token) {
        const modal = document.getElementById('schedule-modal');
        const closeBtn = document.querySelector('.modal-close');
        const dateInput = document.getElementById('appointment-date');
        let currentDoctorId = null;

        document.getElementById('doctor-list').addEventListener('click', (e) => {
            if (e.target.classList.contains('btn-schedule')) {
                currentDoctorId = e.target.dataset.doctorId;
                document.getElementById('modal-doctor-name').textContent = `Pilih Jadwal untuk ${e.target.dataset.doctorName}`;
                modal.style.display = 'flex';
                // Set tanggal minimal ke hari ini
                dateInput.min = new Date().toISOString().split("T")[0];
            }
        });

        closeBtn.onclick = () => { modal.style.display = 'none'; resetModal(); };
        window.onclick = (e) => { if (e.target == modal) { modal.style.display = 'none'; resetModal(); } };

        dateInput.addEventListener('change', () => {
            if(currentDoctorId && dateInput.value) {
                fetchAvailability(currentDoctorId, dateInput.value, token);
            }
        });
        
        document.getElementById('availability-slots').addEventListener('click', (e) => {
            if (e.target.classList.contains('btn-book')) {
                const appointmentTime = e.target.dataset.slot;
                bookAppointment(currentDoctorId, appointmentTime, token);
            }
        });
    }
    
    async function fetchAvailability(doctorId, date, token) {
        const slotsContainer = document.getElementById('availability-slots');
        slotsContainer.innerHTML = '<p class="info-text">Memuat slot...</p>';
        try {
            const response = await fetch(`/api/doctors/${doctorId}/availability?date=${date}`, {
                headers: { 'Authorization': `Bearer ${token}` }
            });
            if (!response.ok) throw new Error('Gagal mengambil ketersediaan.');
            const slots = await response.json();
            displaySlots(slots);
        } catch (error) {
            console.error('Fetch Availability Error:', error);
            slotsContainer.innerHTML = '<p class="message error">Tidak dapat memuat slot.</p>';
        }
    }

    function displaySlots(slots) {
        const slotsContainer = document.getElementById('availability-slots');
        slotsContainer.innerHTML = '';
        if (slots.length === 0) {
            slotsContainer.innerHTML = '<p class="info-text">Tidak ada jadwal tersedia pada tanggal ini.</p>';
            return;
        }
        slots.forEach(slot => {
            const time = new Date(slot).toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit', hour12: false });
            const slotEl = document.createElement('div');
            slotEl.className = 'slot';
            slotEl.innerHTML = `
                <span>${time}</span>
                <button class="btn btn-secondary btn-book" data-slot="${slot}">Pesan</button>
            `;
            slotsContainer.appendChild(slotEl);
        });
    }
    
    async function bookAppointment(doctorId, appointmentTime, token) {
        const messageEl = document.getElementById('modal-message');
        messageEl.style.display = 'none';
        try {
            const response = await fetch('/api/appointments/book', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` },
                body: JSON.stringify({ doctorId, appointmentTime })
            });
            if (!response.ok) throw new Error('Gagal membuat janji temu.');
            
            messageEl.textContent = 'Janji temu berhasil dibuat!';
            messageEl.className = 'message success';
            messageEl.style.display = 'block';
            
            // Refresh ketersediaan setelah booking berhasil
            const dateInput = document.getElementById('appointment-date');
            if(dateInput.value) {
                fetchAvailability(doctorId, dateInput.value, token);
            }
        } catch (error) {
            console.error('Booking Error:', error);
            messageEl.textContent = 'Gagal membuat janji temu. Slot mungkin sudah terisi.';
            messageEl.className = 'message error';
            messageEl.style.display = 'block';
        }
    }

    function resetModal() {
        document.getElementById('appointment-date').value = '';
        document.getElementById('availability-slots').innerHTML = '<p class="info-text">Silakan pilih tanggal untuk melihat slot yang tersedia.</p>';
        document.getElementById('modal-message').style.display = 'none';
    }
});
