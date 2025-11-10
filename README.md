# 🎰 Lottery sales app

<div align="center">

**Idioma:** 🇪🇸 español | [🇬🇧 english](./README.en.md)

[![java](https://img.shields.io/badge/java-17-orange.svg)](https://www.oracle.com/java/)
[![spring boot](https://img.shields.io/badge/spring%20boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![angular](https://img.shields.io/badge/angular-18+-red.svg)](https://angular.io/)
[![tailwind css](https://img.shields.io/badge/tailwind%20css-3.x-blue.svg)](https://tailwindcss.com/)

</div>

Sistema completo de gestión de ventas de lotería desarrollado con **spring boot** y **angular**.

---

## 📋 Descripción general

Aplicación full-stack para la gestión integral de ventas de billetes de lotería que permite:

- ✅ registrar y administrar clientes
- ✅ crear y gestionar sorteos
- ✅ generar billetes automáticamente
- ✅ realizar ventas de billetes a clientes
- ✅ consultar historial de compras
- ✅ visualizar billetes disponibles por sorteo

---

## 🏗️ Arquitectura del sistema

```
lottery-sales-app/
├── backend/          # api rest con spring boot
│   ├── src/
│   ├── pom.xml
│   └── readme.md     → documentación del backend
│
└── frontend/         # aplicación web con angular
    ├── src/
    ├── package.json
    └── readme.md     → documentación del frontend
```

### Backend (spring boot)

- **clean architecture** - separación en capas (domain, application, infrastructure)
- **api restful** con spring web
- **base de datos h2** en memoria
- **documentación con swagger/openapi**
- **validaciones con spring validation**

### Frontend (angular)

- **arquitectura modular** con componentes standalone
- **diseño responsivo** con tailwind css
- **gestión de estado** con rxjs
- **comunicación http** con servicios dedicados

---

## 🚀 Quick start

### Prerequisitos

- **java 17+**
- **maven 3.8+**
- **node.js 18+**
- **npm 9+** o **yarn**
- **angular cli 18+**

### 1️⃣ Clonar el repositorio

```bash
git clone git@github.com:blandoncj/lottery-sales-app.git
cd lottery-sales-app
```

### 2️⃣ Iniciar el backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

El backend estará disponible en: **<http://localhost:8080>**

- swagger ui: <http://localhost:8080/swagger-ui.html>
- h2 console: <http://localhost:8080/h2-console>

### 3️⃣ Iniciar el frontend

```bash
cd ../frontend
npm install
ng serve
```

El frontend estará disponible en: **<http://localhost:4200>**

---

## 📚 Documentación detallada

Para información específica de cada módulo, consulta:

- **[📖 backend readme](./backend/readme.md)** - arquitectura, endpoints, configuración y pruebas
- **[📖 frontend readme](./frontend/readme.md)** - estructura, componentes, configuración y estilos

---

## 🎯 Funcionalidades principales

### Gestión de clientes

- Registro de nuevos clientes con validación
- Listado completo de clientes
- Consulta de historial de compras por cliente

### Gestión de sorteos

- Creación de sorteos con fecha y nombre
- Listado de sorteos disponibles
- Visualización de billetes asociados a cada sorteo
- Venta directa desde la vista de sorteos

### Gestión de billetes

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

| tecnología      | versión | propósito                |
| --------------- | ------- | ------------------------ |
| java            | 17      | lenguaje de programación |
| spring boot     | 3.x     | framework principal      |
| spring data jpa | -       | persistencia de datos    |
| h2 database     | -       | base de datos en memoria |
| swagger/openapi | -       | documentación de api     |
| junit 5         | -       | testing                  |
| mockito         | -       | mocking para tests       |

### Frontend

| tecnología    | versión | propósito                |
| ------------- | ------- | ------------------------ |
| angular       | 18+     | framework frontend       |
| typescript    | 5+      | lenguaje de programación |
| tailwind css  | 3+      | framework de estilos     |
| rxjs          | 7+      | programación reactiva    |
| angular forms | -       | gestión de formularios   |

---

## 🧪 Testing

### Backend

```bash
cd backend
mvn test
```

---

## 🔧 Configuración

### Variables de entorno - backend

Edita `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:lottery_sales_app_db
spring.datasource.username=sa
spring.datasource.password=
server.port=8080
```

### Variables de entorno - frontend

Edita `frontend/src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiurl: "http://localhost:8080/api/v1",
};
```

---

## 📝 Api Endpoints (resumen)

| método | endpoint                            | descripción          |
| ------ | ----------------------------------- | -------------------- |
| get    | `/api/v1/customers`                 | listar clientes      |
| post   | `/api/v1/customers`                 | crear cliente        |
| get    | `/api/v1/lottery-draws`             | listar sorteos       |
| post   | `/api/v1/lottery-draws`             | crear sorteo         |
| post   | `/api/v1/lottery-tickets/generate`  | generar billetes     |
| get    | `/api/v1/lottery-tickets/draw/{id}` | billetes por sorteo  |
| post   | `/api/v1/lottery-tickets/sell`      | vender billetes      |
| get    | `/api/v1/sales/customer/{id}`       | historial de cliente |

**Documentación completa:** <http://localhost:8080/swagger-ui.html>

---

## 🐛 Troubleshooting

### Error: cannot connect to backend

Verifica que el backend esté corriendo en `http://localhost:8080` antes de iniciar el frontend

---

## 👨‍💻 Autor

**Jacobo Blandón Castro**

- GitHub: [@blandoncj](https://github.com/blandoncj)
- Email: <jacoboblandon94@gmail.com>
