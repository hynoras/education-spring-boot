# 🏫 Education System - Backend

This is the **backend** of a full-stack Education Management System developed using **Spring Boot**. It provides RESTful APIs for managing student records with role-based access control for **admin** and **student** users.

> 🛠 Note: Features like course registration and tuition fee management are planned for future development.

---

## ⚙️ Technologies Used

- **Java 21 (development)** / **Java 17 (production)**
- **Spring Boot**
- **Spring MVC & Spring Validation**
- **Spring Security (JWT in HTTP-only cookies)**
- **Spring Data JPA with JDBC**
- **Jackson** (for JSON serialization/deserialization)
- **PostgreSQL**
- **Gradle**
- **JUnit + Mockito** (for unit testing)
- **Cloudinary** (for student avatar/image storage)
- **Docker** (for containerization)
- **Deployed on [Koyeb](https://www.koyeb.com/)**

---

## 🔐 Role-Based Access

The system supports two roles:

- **ADMIN**: Full access to manage student records.
- **STUDENT**: Read-only access to view their own student details.

Authentication is secured using **JWT** stored in **HTTP-only cookies** for better protection against XSS.

---

## 🚀 Current Features

### 🧑 Admin
- View paginated list of students
- View student detail
- Create new student records
- Update student records
- Delete student records

### 👨‍🎓 Student
- View their own student detail

> 🔜 Future features: course registration, tuition fee management, curriculum tracking, and reporting

---

## 🛠 Getting Started

### ✅ Prerequisites
- Java 21+
- Gradle
- PostgreSQL
- Docker (optional)

### 📦 Setup

```bash
# Clone the repo
git clone https://github.com/hynoras/education-spring-boot.git
cd education-spring-boot

# Run the app with Gradle
./gradlew bootRun
