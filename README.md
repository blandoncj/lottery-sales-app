# 🎰 Lottery Sales App

Sistema completo de gestión de ventas de lotería desarrollado con **Spring Boot** y **Angular**.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-18+-red.svg)](https://angular.io/)
[![Tailwind CSS](https://img.shields.io/badge/Tailwind%20CSS-3.x-blue.svg)](https://tailwindcss.com/)

---

## 📋 Descripción General

Aplicación full-stack para la gestión integral de ventas de billetes de lotería que permite:

- ✅ Registrar y administrar clientes
- ✅ Crear y gestionar sorteos
- ✅ Generar billetes automáticamente
- ✅ Realizar ventas de billetes a clientes
- ✅ Consultar historial de compras
- ✅ Visualizar billetes disponibles por sorteo

---

## 🏗️ Arquitectura del Sistema

```
lottery-sales-app/
├── backend/          # API REST con Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── README.md     → Documentación del Backend
│
└── frontend/         # Aplicación web con Angular
    ├── src/
    ├── package.json
    └── README.md     → Documentación del Frontend
```

### Backend (Spring Boot)

- **Clean Architecture** - Separación en capas (Domain, Application, Infrastructure)
- **API RESTful** con Spring Web
- **Base de datos H2** en memoria
- **Documentación con Swagger/OpenAPI**
- **Validaciones con Spring Validation**

### Frontend (Angular)

- **Arquitectura modular** con componentes standalone
- **Diseño responsivo** con Tailwind CSS
- **Gestión de estado** con RxJS
- **Comunicación HTTP** con servicios dedicados

---

## 🚀 Quick Start

### Prerequisitos

- **Java 17+**
- **Maven 3.8+**
- **Node.js 18+**
- **npm 9+** o **yarn**
- **Angular CLI 18+**

### 1️⃣ Clonar el repositorio

```bash
git clone git@github.com:blandoncj/lottery-sales-app.git
cd lottery-sales-app
```

### 2️⃣ Iniciar el Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

El backend estará disponible en: **<http://localhost:8080>**

- Swagger UI: <http://localhost:8080/swagger-ui.html>
- H2 Console: <http://localhost:8080/h2-console>

### 3️⃣ Iniciar el Frontend

```bash
cd ../frontend
npm install
ng serve
```

El frontend estará disponible en: **<http://localhost:4200>**

---

## 📚 Documentación Detallada

Para información específica de cada módulo, consulta:

- **[📖 Backend README](./backend/README.md)** - Arquitectura, endpoints, configuración y pruebas
- **[📖 Frontend README](./frontend/README.md)** - Estructura, componentes, configuración y estilos

---

## 🎯 Funcionalidades Principales

### Gestión de Clientes

- Registro de nuevos clientes con validación
- Listado completo de clientes
- Consulta de historial de compras por cliente

### Gestión de Sorteos

- Creación de sorteos con fecha y nombre
- Listado de sorteos disponibles
- Visualización de billetes asociados a cada sorteo
- Venta directa desde la vista de sorteos

### Gestión de Billetes

- Generación automática de billetes por lote
- Configuración de cantidad y precio
- Visualización de billetes disponibles
- Selección múltiple para venta

### Ventas

- Proceso de venta intuitivo
- Selección de cliente y billetes

---

## 🛠️ Stack Tecnológico

### Backend

| Tecnología      | Versión | Propósito                |
| --------------- | ------- | ------------------------ |
| Java            | 17      | Lenguaje de programación |
| Spring Boot     | 3.x     | Framework principal      |
| Spring Data JPA | -       | Persistencia de datos    |
| H2 Database     | -       | Base de datos en memoria |
| Swagger/OpenAPI | -       | Documentación de API     |
| JUnit 5         | -       | Testing                  |
| Mockito         | -       | Mocking para tests       |

### Frontend

| Tecnología    | Versión | Propósito                |
| ------------- | ------- | ------------------------ |
| Angular       | 18+     | Framework frontend       |
| TypeScript    | 5+      | Lenguaje de programación |
| Tailwind CSS  | 3+      | Framework de estilos     |
| RxJS          | 7+      | Programación reactiva    |
| Angular Forms | -       | Gestión de formularios   |

---

## 🧪 Testing

### Backend

```bash
cd backend
mvn test
```

---

## 🔧 Configuración

### Variables de Entorno - Backend

Edita `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:lottery_sales_app_db
spring.datasource.username=sa
spring.datasource.password=
server.port=8080
```

### Variables de Entorno - Frontend

Edita `frontend/src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: "http://localhost:8080/api/v1",
};
```

---

## 📝 API Endpoints (Resumen)

| Método | Endpoint                            | Descripción          |
| ------ | ----------------------------------- | -------------------- |
| GET    | `/api/v1/customers`                 | Listar clientes      |
| POST   | `/api/v1/customers`                 | Crear cliente        |
| GET    | `/api/v1/lottery-draws`             | Listar sorteos       |
| POST   | `/api/v1/lottery-draws`             | Crear sorteo         |
| POST   | `/api/v1/lottery-tickets/generate`  | Generar billetes     |
| GET    | `/api/v1/lottery-tickets/draw/{id}` | Billetes por sorteo  |
| POST   | `/api/v1/lottery-tickets/sell`      | Vender billetes      |
| GET    | `/api/v1/sales/customer/{id}`       | Historial de cliente |

**Documentación completa:** <http://localhost:8080/swagger-ui.html>

---

## 🐛 Troubleshooting

### Error: Cannot connect to backend

Verifica que el backend esté corriendo en `http://localhost:8080` antes de iniciar el frontend

---

## 👨‍💻 Autor

**Jacobo Blandón Castro**

- GitHub: [@blandoncj](https://github.com/blandoncj)
- Email: <jacoboblandon94@gmail.com>
