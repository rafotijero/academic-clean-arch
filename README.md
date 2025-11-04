# 🎓 Academic Enrollment System

Sistema de Matrícula Universitaria implementado con **Clean Architecture** (Arquitectura Hexagonal) y **Domain-Driven Design (DDD)**.

## 📋 Descripción

Este proyecto es una aplicación completa de gestión de matrículas universitarias que demuestra las mejores prácticas de arquitectura de software:

- ✅ **Clean Architecture** - Separación clara entre Domain, Application e Infrastructure
- ✅ **Domain-Driven Design** - Entidades, Value Objects, Domain Services y Domain Events
- ✅ **Patrón CQRS** - Commands, Queries y Responses
- ✅ **Ports & Adapters** - Desacoplamiento de la lógica de negocio
- ✅ **Arquitectura Hexagonal** - Inversión de dependencias
- ✅ **Spring Boot 3.2** - Framework moderno con Jakarta EE
- ✅ **PostgreSQL** - Base de datos relacional robusta

## ⭐ Módulos Completamente Funcionales

### 🏛️ Faculty (Facultades) - IMPLEMENTADO Y FUNCIONANDO
El módulo de **Faculty** está completamente implementado siguiendo los principios de Clean Architecture:

**Características:**
- ✅ CRUD completo de facultades
- ✅ Gestión de estado activo/inactivo
- ✅ Validación de nombres únicos
- ✅ Integración completa con carreras (Careers)
- ✅ Events del dominio implementados
- ✅ Tests unitarios y de integración

**Endpoints Disponibles:**
- `POST /api/v1/faculties` - Crear nueva facultad
- `GET /api/v1/faculties` - Listar todas las facultades
- `GET /api/v1/faculties?active=true` - Listar facultades activas
- `GET /api/v1/faculties/{id}` - Obtener facultad por ID
- `PUT /api/v1/faculties/{id}` - Actualizar facultad
- `DELETE /api/v1/faculties/{id}` - Eliminar/Desactivar facultad

**Ejemplo de Uso:**
```bash
# Crear una facultad
curl -X POST http://localhost:8080/api/v1/faculties \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Facultad de Ingeniería",
    "description": "Facultad dedicada a carreras de ingeniería"
  }'

# Listar facultades activas
curl http://localhost:8080/api/v1/faculties?active=true
```

### 🎓 Career (Carreras) - IMPLEMENTADO Y FUNCIONANDO
El módulo de **Career** está completamente implementado con todas las funcionalidades:

**Características:**
- ✅ CRUD completo de carreras
- ✅ Vinculación con facultades
- ✅ Gestión de código de carrera único
- ✅ Control de duración en semestres
- ✅ Validación de reglas de negocio
- ✅ Events del dominio implementados
- ✅ Tests unitarios y de integración

**Endpoints Disponibles:**
- `POST /api/v1/careers` - Crear nueva carrera
- `GET /api/v1/careers` - Listar todas las carreras
- `GET /api/v1/careers?active=true` - Listar carreras activas
- `GET /api/v1/careers/{id}` - Obtener carrera por ID
- `GET /api/v1/careers/faculty/{facultyId}` - Listar carreras por facultad
- `PUT /api/v1/careers/{id}` - Actualizar carrera
- `DELETE /api/v1/careers/{id}` - Eliminar/Desactivar carrera

**Ejemplo de Uso:**
```bash
# Crear una carrera
curl -X POST http://localhost:8080/api/v1/careers \
  -H "Content-Type: application/json" \
  -d '{
    "code": "IS",
    "name": "Ingeniería de Sistemas",
    "description": "Carrera de Ingeniería de Sistemas y Computación",
    "durationSemesters": 10,
    "facultyId": 1
  }'

# Listar carreras de una facultad específica
curl http://localhost:8080/api/v1/careers/faculty/1
```

### 🔄 Estado de Otros Módulos

| Módulo | Estado | Funcionalidad |
|--------|--------|---------------|
| **Faculty** | ✅ COMPLETO | 100% operativo |
| **Career** | ✅ COMPLETO | 100% operativo |
| Student | 🟡 PARCIAL | Implementación básica |
| Professor | 🟡 PARCIAL | Implementación básica |
| Course | 🟡 PARCIAL | Implementación básica |
| Section | 🟡 PARCIAL | Implementación básica |
| Enrollment | 🟡 PARCIAL | En desarrollo |
| Payment | 🟡 PARCIAL | En desarrollo |
| GradeRecord | 🟡 PARCIAL | En desarrollo |

## 🚀 Quick Start - Prueba Faculty y Career

Para probar rápidamente los módulos completamente funcionales:

```bash
# 1. Iniciar la aplicación con Docker
docker-compose up -d
mvn spring-boot:run

# 2. Probar Facultades
# Crear una nueva facultad
curl -X POST http://localhost:8080/api/v1/faculties \
  -H "Content-Type: application/json" \
  -d '{"name": "Facultad de Arquitectura", "description": "Nueva facultad de arquitectura y diseño"}'

# Listar facultades activas
curl http://localhost:8080/api/v1/faculties?active=true

# 3. Probar Carreras
# Crear una carrera asociada a la facultad de Ingeniería (ID: 1)
curl -X POST http://localhost:8080/api/v1/careers \
  -H "Content-Type: application/json" \
  -d '{"code": "IM", "name": "Ingeniería Mecánica", "description": "Ingeniería Mecánica y Automotriz", "durationSemesters": 10, "facultyId": 1}'

# Listar carreras de Ingeniería
curl http://localhost:8080/api/v1/careers/faculty/1

# 4. Ver en Swagger UI
# Abre: http://localhost:8080/swagger-ui.html
```

## 🏗️ Arquitectura del Proyecto

```
academic-clean-arch/
├── src/main/java/com/academic/enrollment/
│   ├── domain/                    # Capa de Dominio
│   │   ├── model/                 # Entidades y Value Objects
│   │   ├── repository/            # Interfaces de repositorios
│   │   ├── service/               # Servicios de dominio
│   │   └── exception/             # Excepciones de negocio
│   │
│   ├── application/               # Capa de Aplicación
│   │   ├── service/               # Application Services (Use Cases)
│   │   ├── port/in/              # Puertos de entrada
│   │   ├── port/out/             # Puertos de salida
│   │   ├── dto/                  # DTOs (Commands, Queries, Responses)
│   │   └── mapper/               # Mappers
│   │
│   ├── infrastructure/            # Capa de Infraestructura
│   │   ├── adapter/in/web/       # REST Controllers
│   │   ├── adapter/out/
│   │   │   ├── persistence/      # JPA Repositories, Entities, Mappers
│   │   │   └── notification/     # Email, SMS adapters
│   │   └── config/               # Configuración de Spring
│   │
│   ├── shared/                    # Componentes compartidos
│   │   ├── annotation/           # Anotaciones personalizadas
│   │   ├── exception/            # Excepciones base
│   │   └── util/                 # Utilidades
│   │
│   └── AcademicEnrollmentApplication.java  # Clase principal
│
└── src/main/resources/
    ├── application.yml            # Configuración principal
    ├── application-dev.yml        # Configuración de desarrollo
    ├── application-prod.yml       # Configuración de producción
    └── application-test.yml       # Configuración de tests
```

## 📊 Estadísticas del Proyecto

### Módulos Completos (Faculty & Career)
| Componente | Faculty | Career | Total |
|------------|---------|--------|-------|
| **Domain Entities** | 1 | 1 | 2 |
| **Value Objects** | 3 | 5 | 8 |
| **Domain Events** | 3 | 3 | 6 |
| **Repositorios** | 1 | 1 | 2 |
| **Application Services** | 1 | 1 | 2 |
| **Use Cases Implementados** | 6 | 7 | 13 |
| **REST Controllers** | 1 | 1 | 2 |
| **REST Endpoints** | 6 | 7 | 13 |
| **Tests Unitarios** | ✅ | ✅ | 100% |
| **Cobertura de Código** | >80% | >80% | >80% |

### Proyecto Completo
| Componente | Cantidad |
|------------|----------|
| **Archivos Java** | 169 |
| **Entidades de Dominio** | 9 (2 completas) |
| **Value Objects** | 17 |
| **Domain Events** | 7 |
| **Repositorios** | 9 |
| **Application Services** | 9 |
| **Use Cases** | 21+ |
| **REST Controllers** | 10 |
| **JPA Entities** | 9 |
| **DTOs** | 26 |

## 🚀 Requisitos Previos

- **Java 17** o superior
- **Maven 3.8+** o superior
- **Docker Desktop** (para ejecutar con contenedores)
- **PostgreSQL 15** (si ejecutas sin Docker)

## 📦 Instalación y Ejecución

### Opción 1: Usando Docker (Recomendado)

1. **Clonar el repositorio**
   ```bash
   git clone <repository-url>
   cd academic-clean-arch
   ```

2. **Levantar los contenedores**
   ```bash
   docker-compose up -d
   ```

   Esto iniciará:
   - PostgreSQL en el puerto `5432`
   - PgAdmin en el puerto `8081`

3. **Compilar y ejecutar la aplicación**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - API REST: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - PgAdmin: http://localhost:8081
     - Email: `admin@academic.edu`
     - Password: `admin123`

### Opción 2: Sin Docker

1. **Instalar PostgreSQL 15**

2. **Crear la base de datos y ejecutar el script**
   ```bash
   # Crear la base de datos
   createdb -U postgres academic_enrollment

   # Ejecutar el script de inicialización
   psql -U postgres -d academic_enrollment -f scripts/schema-and-data.sql
   ```

3. **Configurar variables de entorno (opcional)**
   ```bash
   export DB_USERNAME=academic_user
   export DB_PASSWORD=academic_pass
   ```

4. **Compilar y ejecutar**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 🐳 Comandos Docker Útiles

```bash
# Levantar contenedores
docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener contenedores
docker-compose down

# Detener y eliminar volúmenes (¡cuidado! elimina datos)
docker-compose down -v

# Reconstruir imágenes
docker-compose up --build

# Ejecutar solo PostgreSQL
docker-compose up -d postgres
```

## 📚 API Endpoints

### ✅ Facultades (Faculties) - COMPLETAMENTE FUNCIONAL
- `POST /api/v1/faculties` - Crear nueva facultad
- `GET /api/v1/faculties` - Listar todas las facultades
- `GET /api/v1/faculties?active=true` - Listar facultades activas
- `GET /api/v1/faculties/{id}` - Obtener facultad por ID
- `PUT /api/v1/faculties/{id}` - Actualizar facultad
- `DELETE /api/v1/faculties/{id}` - Eliminar facultad

### ✅ Carreras (Careers) - COMPLETAMENTE FUNCIONAL
- `POST /api/v1/careers` - Crear nueva carrera
- `GET /api/v1/careers` - Listar todas las carreras
- `GET /api/v1/careers?active=true` - Listar carreras activas
- `GET /api/v1/careers/{id}` - Obtener carrera por ID
- `GET /api/v1/careers/faculty/{facultyId}` - Listar carreras por facultad
- `PUT /api/v1/careers/{id}` - Actualizar carrera
- `DELETE /api/v1/careers/{id}` - Eliminar carrera

### 🟡 Estudiantes (Students) - En desarrollo
- `POST /api/v1/students` - Crear estudiante
- `GET /api/v1/students?active=true` - Listar estudiantes activos
- `PUT /api/v1/students/{id}/contact` - Actualizar contacto

### 🟡 Matrículas (Enrollments) - En desarrollo
- `POST /api/v1/enrollments` - Crear matrícula
- `GET /api/v1/enrollments/student/{studentId}` - Matrículas por estudiante
- `PUT /api/v1/enrollments/{id}/paid` - Marcar como pagado

### 🟡 Secciones (Sections) - En desarrollo
- `POST /api/v1/sections` - Crear sección
- `GET /api/v1/sections?period=2024-1` - Listar por período

### 🟡 Pagos (Payments) - En desarrollo
- `POST /api/v1/payments` - Registrar pago
- `GET /api/v1/payments/enrollment/{enrollmentId}` - Pagos por matrícula

### 🟡 Calificaciones (Grades) - En desarrollo
- `POST /api/v1/grades` - Registrar calificación
- `GET /api/v1/grades/enrollment/{enrollmentId}` - Ver calificaciones

**Ver documentación completa en:** http://localhost:8080/swagger-ui.html


## 🎯 Modelo de Dominio

### Entidades Principales

#### ✅ Completamente Implementadas

**1. Faculty (Facultad)** - 100% FUNCIONAL
- Entidad de dominio completa con lógica de negocio
- Gestión de estado (activo/inactivo)
- Relación uno-a-muchos con Career
- Value Objects: `FacultyId`, `Name`, `Description`
- Domain Events: `FacultyCreatedEvent`, `FacultyUpdatedEvent`, `FacultyDeletedEvent`
- Archivos:
  - Domain: `src/main/java/com/academic/enrollment/domain/model/Faculty.java`
  - Application: `src/main/java/com/academic/enrollment/application/service/FacultyApplicationService.java`
  - Infrastructure: `src/main/java/com/academic/enrollment/infrastructure/adapter/in/web/FacultyController.java`

**2. Career (Carrera)** - 100% FUNCIONAL
- Entidad de dominio completa con lógica de negocio
- Código único de carrera
- Duración en semestres (validación: 6-12 semestres)
- Vinculación con Faculty
- Value Objects: `CareerId`, `CareerCode`, `Name`, `DurationSemesters`
- Domain Events: `CareerCreatedEvent`, `CareerUpdatedEvent`, `CareerDeletedEvent`
- Archivos:
  - Domain: `src/main/java/com/academic/enrollment/domain/model/Career.java`
  - Application: `src/main/java/com/academic/enrollment/application/service/CareerApplicationService.java`
  - Infrastructure: `src/main/java/com/academic/enrollment/infrastructure/adapter/in/web/CareerController.java`

#### 🟡 En Desarrollo

3. **Student** - Estudiante (implementación básica)
4. **Professor** - Profesor (implementación básica)
5. **Course** - Curso (implementación básica)
6. **Section** - Sección (curso en un período específico)
7. **Enrollment** - Matrícula
8. **Payment** - Pago
9. **GradeRecord** - Registro de calificación

### Value Objects Implementados

#### Para Faculty y Career:
- `FacultyId` - Identificador único de facultad
- `CareerId` - Identificador único de carrera
- `CareerCode` - Código alfanumérico único (2-10 caracteres)
- `Name` - Nombre con validación
- `Description` - Descripción opcional
- `DurationSemesters` - Duración entre 6 y 12 semestres
- `Active` - Estado activo/inactivo

#### Otros Value Objects:
- `StudentId`, `ProfessorId`, `CourseId`, etc.
- `Email`, `DNI`
- `Credits`, `Grade`, `Money`
- `AcademicPeriod`, `EnrollmentStatus`, `PaymentStatus`

## 💡 Casos de Uso Implementados (Faculty y Career)

### Faculty Module
Los siguientes casos de uso están completamente implementados y probados:

1. **CreateFacultyUseCase** - Crear nueva facultad con validaciones
2. **GetFacultyByIdUseCase** - Obtener facultad específica
3. **GetAllFacultiesUseCase** - Listar todas las facultades
4. **GetActiveFacultiesUseCase** - Filtrar facultades activas
5. **UpdateFacultyUseCase** - Actualizar información de facultad
6. **DeleteFacultyUseCase** - Soft delete de facultad

**Validaciones implementadas:**
- Nombre no puede estar vacío
- Nombre debe ser único
- No se puede eliminar una facultad con carreras activas asociadas

### Career Module
Los siguientes casos de uso están completamente implementados y probados:

1. **CreateCareerUseCase** - Crear nueva carrera con validaciones
2. **GetCareerByIdUseCase** - Obtener carrera específica
3. **GetAllCareersUseCase** - Listar todas las carreras
4. **GetActiveCareersUseCase** - Filtrar carreras activas
5. **GetCareersByFacultyUseCase** - Listar carreras por facultad
6. **UpdateCareerUseCase** - Actualizar información de carrera
7. **DeleteCareerUseCase** - Soft delete de carrera

**Validaciones implementadas:**
- Código de carrera debe ser único
- Código debe tener entre 2 y 10 caracteres
- Duración debe estar entre 6 y 12 semestres
- La facultad asociada debe existir y estar activa
- Nombre no puede estar vacío