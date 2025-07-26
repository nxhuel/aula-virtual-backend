# Aula Virtual - Backend

Este proyecto corresponde al backend de un sistema de Aula Virtual desarrollado como parte de la Tecnicatura en Análisis de Sistemas. Está orientado a la gestión académica de usuarios, materias, exámenes y roles dentro de un entorno educativo.
> Repositorio Frontend: https://github.com/nxhuel/aula-virtual-frontend

## 🧩 Características principales

- Gestión de usuarios (estudiantes, profesores, administrativos)
- Registro y consulta de historial académico
- Inscripción a materias y exámenes finales
- Control de permisos y roles por usuario
- Gestión de programas y materias

## 🗄️ Modelo de Datos

La base de datos está estructurada mediante un DER relacional. Algunas de las entidades más importantes son:

<img width="951" height="950" alt="unnamed" src="https://github.com/user-attachments/assets/8c4ce9a7-6feb-4970-b9e2-c8c2df168fa9" />

### 📘 `user`
Contiene la información de cada usuario del sistema, incluyendo roles, credenciales y relación con el historial académico.

- `username`, `password`, `dni`, `lastname`, `legajo`
- Relaciones:
  - Pertenece a un `program`
  - Tiene uno o varios `roles`
  - Puede ser profesor o alumno

### 📘 `program`
Define los programas académicos (carreras/tecnicaturas).

- `code`, `duration`, `study_plan_pdf`

### 📘 `subject`
Representa una materia de un programa.

- `code`, `name`, `academic_plan`, `id_professor`
- Relacionada con: `program`, `final_exam`, `subject_inscribed`

### 📘 `final_exam`
Tabla de exámenes finales.

- `subject_code`, `final_date`

### 📘 `academic_history`
Agrupa la trayectoria académica del estudiante.

### 📘 `subject_inscribed`
Registro de inscripciones a materias por estudiante.

- Notas, fechas, estado de inscripción

### 📘 `final_exam_registered`
Registro de inscripciones a exámenes finales.

- `registration_number`, `student_id`, `subject_id`, `professor_id`

### 🔐 `user_roles`, `role`, `permission`, `role_permissions`
Sistema de roles y permisos para gestionar el acceso a distintas funcionalidades del backend.

## 🛠️ Tecnologías utilizadas

- **Java / Spring Boot**
- **MySQL**
- **JPA / Hibernate**
- **Postman** (para testing de endpoints)
- **Docker** 

## 📦 Requisitos

- JDK 17+
- MySQL Server
- Maven 3.8+
- Postman o similar
- Docker (opcional)

## 🚀 Instalación

1. Clona el repositorio
   ```bash
   git clone https://github.com/tu_usuario/aula-virtual-backend.git
   cd aula-virtual-backend
   ```
2. Configura tu archivo 
  ```application.properties
  spring.datasource.url=jdbc:mysql://localhost:3306/aula_virtual
  spring.datasource.username=root
  spring.datasource.password=tu_password
  spring.jpa.hibernate.ddl-auto=update
  ```
3. Ejecuta el proyecto
  ```bash
  mvn spring-boot:run
  ```
