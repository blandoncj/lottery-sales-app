# 🎰 Lottery sales app

<div align="center">
**idioma:** 🇬🇧 english | [🇪🇸 spanish](./README.md)
</div>

Complete lottery ticket sales management system developed with **spring boot** and **angular**.

[![java](https://img.shields.io/badge/java-17-orange.svg)](https://www.oracle.com/java/)
[![spring boot](https://img.shields.io/badge/spring%20boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![angular](https://img.shields.io/badge/angular-18+-red.svg)](https://angular.io/)
[![tailwind css](https://img.shields.io/badge/tailwind%20css-3.x-blue.svg)](https://tailwindcss.com/)

---

## 📋 Overview

Full-stack application for comprehensive lottery ticket sales management that allows:

- ✅ register and manage customers
- ✅ create and manage lottery draws
- ✅ automatically generate tickets
- ✅ sell tickets to customers
- ✅ query purchase history
- ✅ view available tickets per draw

---

## 🏗️ System Architecture

```
lottery-sales-app/
├── backend/          # rest api with spring boot
│   ├── src/
│   ├── pom.xml
│   └── readme.md     → backend documentation
│
└── frontend/         # web application with angular
    ├── src/
    ├── package.json
    └── readme.md     → frontend documentation
```

### Backend (spring boot)

- **clean architecture** - layer separation (domain, application, infrastructure)
- **restful api** with spring web
- **h2 database** in-memory
- **documentation with swagger/openapi**
- **validation with spring validation**

### Frontend (angular)

- **modular architecture** with standalone components
- **responsive design** with tailwind css
- **state management** with rxjs
- **http communication** with dedicated services

---

## 🚀 Quick start

### Prerequisites

- **java 17+**
- **maven 3.8+**
- **node.js 18+**
- **npm 9+** or **yarn**
- **angular cli 18+**

### 1️⃣ Clone the repository

```bash
git clone git@github.com:blandoncj/lottery-sales-app.git
cd lottery-sales-app
```

### 2️⃣ Start the backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend will be available at: **<http://localhost:8080>**

- swagger ui: <http://localhost:8080/swagger-ui.html>
- h2 console: <http://localhost:8080/h2-console>

### 3️⃣ Start the frontend

```bash
cd ../frontend
npm install
ng serve
```

The frontend will be available at: **<http://localhost:4200>**

---

## 📚 Detailed documentation

For specific information about each module, check:

- **[📖 backend readme](./backend/readme.md)** - architecture, endpoints, configuration and tests
- **[📖 frontend readme](./frontend/readme.md)** - structure, components, configuration and styles

---

## 🎯 Main features

### Customer management

- Register new customers with validation
- Complete customer listing
- Query purchase history per customer

### Draw management

- Create draws with date and name
- List available draws
- View tickets associated with each draw
- Direct sale from draw view

### Ticket management

- Automatic batch ticket generation
- Configure quantity and price
- View available tickets
- Multiple selection for sale

### Sales

- Intuitive sales process
- Customer and ticket selection

---

## 🛠️ Rech stack

### Backend

| technology      | version | purpose              |
| --------------- | ------- | -------------------- |
| java            | 17      | programming language |
| spring boot     | 3.x     | main framework       |
| spring data jpa | -       | data persistence     |
| h2 database     | -       | in-memory database   |
| swagger/openapi | -       | api documentation    |
| junit 5         | -       | testing              |
| mockito         | -       | mocking for tests    |

### Frontend

| technology    | version | purpose              |
| ------------- | ------- | -------------------- |
| angular       | 18+     | frontend framework   |
| typescript    | 5+      | programming language |
| tailwind css  | 3+      | styling framework    |
| rxjs          | 7+      | reactive programming |
| angular forms | -       | form management      |

---

## 🧪 Testing

### Backend

```bash
cd backend
mvn test
```

---

## 🔧 Configuration

### Environment variables - backend

Edit `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:lottery_sales_app_db
spring.datasource.username=sa
spring.datasource.password=
server.port=8080
```

### Environment variables - frontend

Edit `frontend/src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiurl: "http://localhost:8080/api/v1",
};
```

---

## 📝 Api Endpoints (summary)

| method | endpoint                            | description      |
| ------ | ----------------------------------- | ---------------- |
| get    | `/api/v1/customers`                 | list customers   |
| post   | `/api/v1/customers`                 | create customer  |
| get    | `/api/v1/lottery-draws`             | list draws       |
| post   | `/api/v1/lottery-draws`             | create draw      |
| post   | `/api/v1/lottery-tickets/generate`  | generate tickets |
| get    | `/api/v1/lottery-tickets/draw/{id}` | tickets by draw  |
| post   | `/api/v1/lottery-tickets/sell`      | sell tickets     |
| get    | `/api/v1/sales/customer/{id}`       | customer history |

**Complete documentation:** <http://localhost:8080/swagger-ui.html>

---

## 🐛 Troubleshooting

### Error: cannot connect to backend

Verify that the backend is running on `http://localhost:8080` before starting the frontend

---

## 👨‍💻 Author

**Jacobo Blandón Castro**

- GitHub: [@blandoncj](https://github.com/blandoncj)
- Email: <jacoboblandon94@gmail.com>

