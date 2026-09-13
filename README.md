# 📚 Library Management System

A full-stack **Library Management System** built using **Spring Boot**, **Hibernate (JPA)**, and **MySQL** for the backend, with a clean and responsive frontend developed using **HTML, CSS, Bootstrap, and vanilla JavaScript**.

---
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/b4de6256-6dca-440b-848f-2b616c0b6ccc" />

## 🚀 Features

- User authentication (login system)
- Dashboard with summary statistics
- Student management
- Book management
- Publisher management
- Vendor management
- Book allotment and order tracking
- Clean layered architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

### Frontend
- HTML5  
- CSS3  
- Bootstrap  
- Vanilla JavaScript  

### Backend
- Java  
- Spring Boot  
- Hibernate / JPA  
- MySQL  

---

## 📂 Project Structure
```bash
com.sajid.librarymanagementsystem
│
├── controller
│ ├── LoginController
│ ├── DashboardController
│ ├── BookController
│ ├── StudentController
│ ├── PublisherController
│ ├── VendorController
│ └── AllotmentController
│
├── service
│ ├── DashboardService
│ ├── BookService
│ ├── StudentService
│ ├── PublisherService
│ ├── VendorService
│ └── AllotmentService
│
├── repository
│ ├── BookRepository
│ ├── StudentRepository
│ ├── PublisherRepository
│ ├── VendorRepository
│ ├── BookOrderRepository
│ └── AllotmentRepository
│
├── model
│ ├── Book
│ ├── Student
│ ├── Publisher
│ ├── Vendor
│ ├── BookOrder
│ └── Allotment
│
└── dto
├── LoginDto
└── DashboardDto
```
1. Clone the repository
```bash
git clone https://github.com/sadman128/library-management-system.git
```
2. Configure MySQL database in application.properties
```bash
spring.application.name=Library Management System
server.address=0.0.0.0
server.port=9090
spring.datasource.url=jdbc:mysql://localhost:3306/library_database
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```
3. Run the application
```bash
mvn spring-boot:run
```
4. Use the Application(current logic for login is keep the username and password same 😮‍💨)
```bash
http://localhost:9090/login
```
