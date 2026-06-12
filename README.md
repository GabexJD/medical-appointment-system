# Sistema de Gestion de Citas Medicas

Aplicacion web completa de extremo a extremo para la gestion y agendamiento de citas medicas en intervalos de tiempo dinamicos.

## Tecnologias Utilizadas

- **Frontend**: React (Vite) + Reactstrap + Bootstrap + TanStack Query (React Query) + Axios
- **Backend**: Quarkus (Java) + Hibernate ORM + Panache + Jakarta Bean Validation
- **Base de Datos**: MySQL 8.0
- **Contenerizacion**: Docker + Docker Compose

## Estructura del Proyecto

- `/backend` → API REST desarrollada con Quarkus bajo principios de Clean Architecture.
- `/frontend` → Interfaz de usuario interactiva estructurada basada en componentes y hooks de React.
- `/docker` → Scripts de inicializacion SQL y configuracion del contenedor de MySQL.

## Requisitos Previos

Asegurate de tener instalado en tu maquina local:
- Docker y Docker Compose
- Java SDK 26.0.1 (o superior)
- Node.js v24.15.0 (o superior)

---

## Como Ejecutar el Proyecto

### 1. Levantar la Base de Datos
Navega a la raiz del proyecto y ejecuta el contenedor de MySQL mediante Docker Compose:
```bash
docker-compose up -d
```
Nota: Esto creara la base de datos citas_medicas y cargara automaticamente las tablas y los datos iniciales.

### 2. Iniciar el Backend (Quarkus)
Ingresa al directorio de backend e inicia el servidor en modo de desarrollo:
```bash
cd backend
cd medical-appointments-backend
./mvnw quarkus:dev
```
La API estara disponible en: http://localhost:8080

Puedes explorar la documentacion interactiva de Swagger UI en: http://localhost:8080/q/swagger-ui

### 3. Iniciar el Frontend (React)
Abre una nueva terminal, ingresa al directorio del frontend e inicia el servidor de desarrollo:
```bash
cd frontend
npm install
npm run dev
```
La aplicacion estara lista para usar en tu navegador en: http://localhost:5173

## Flujo de Validaciones de Negocio
El sistema cuenta con reglas de negocio robustas:

   - Conflicto de Especialidad: Un paciente no puede registrar dos citas para la misma especialidad el mismo dia.
   - Control de Concurrencia: El sistema bloquea el registro si el horario ya fue reservado por otro paciente.