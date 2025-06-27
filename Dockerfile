# --- Tahap 1: Build ---
# Gunakan image Maven dengan JDK 17 untuk membangun aplikasi
FROM maven:3.8-openjdk-17 AS build
LABEL stage=builder

# Set direktori kerja di dalam container
WORKDIR /app

# Salin file pom.xml untuk men-download dependensi
COPY pom.xml .
RUN mvn dependency:go-offline

# Salin seluruh kode sumber
COPY src ./src

# Build aplikasi dan hasilkan file JAR
RUN mvn clean package -DskipTests


# --- Tahap 2: Run ---
# Gunakan image OpenJDK 17 yang ramping untuk menjalankan aplikasi
FROM openjdk:17-jdk-alpine
LABEL maintainer="your-email@example.com"

# Set direktori kerja
WORKDIR /app

# Salin file JAR dari tahap build ke image final
COPY --from=build /app/target/*.jar app.jar

# Expose port yang digunakan oleh aplikasi Spring Boot
EXPOSE 8080

# Perintah untuk menjalankan aplikasi saat container dimulai
ENTRYPOINT ["java", "-jar", "app.jar"]
