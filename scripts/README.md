# Scripts de Base de Datos

Este directorio contiene scripts SQL para inicializar y gestionar la base de datos del sistema.

## Archivos

### 📄 schema-and-data.sql

Script completo que incluye:
- **Creación de tablas** con todas las restricciones y relaciones
- **Índices** para optimizar consultas
- **Vistas** útiles para reportes
- **Datos de ejemplo** para pruebas

**Contenido de datos de ejemplo:**
- 3 Facultades
- 4 Carreras
- 4 Profesores
- 6 Estudiantes
- 10 Cursos con prerrequisitos
- 5 Secciones (periodo 2024-1)
- 9 Matrículas
- 8 Pagos
- 5 Calificaciones

### 📄 init-db.sql

Script básico de inicialización que solo configura extensiones y zona horaria.

## Uso

### Con Docker Compose

El script `schema-and-data.sql` se ejecuta automáticamente cuando inicias PostgreSQL por primera vez:

```bash
docker-compose up -d
```

### Manualmente con psql

```bash
# Conectarse a la base de datos
psql -U academic_user -d academic_enrollment

# Ejecutar el script
\i scripts/schema-and-data.sql
```

### Reiniciar la base de datos

Si necesitas reiniciar la base de datos desde cero:

```bash
# Detener y eliminar volúmenes
docker-compose down -v

# Levantar de nuevo (ejecutará el script automáticamente)
docker-compose up -d
```

## Vistas Disponibles

El script crea 4 vistas útiles:

1. **v_matriculas** - Información completa de matrículas
2. **v_historial_academico** - Historial académico por estudiante
3. **v_carga_academica_profesor** - Carga académica de profesores
4. **v_estadisticas_matricula** - Estadísticas por periodo académico

### Ejemplos de consultas

```sql
-- Ver todas las matrículas
SELECT * FROM v_matriculas;

-- Ver historial de un estudiante
SELECT * FROM v_historial_academico WHERE estudiante_id = 1;

-- Ver carga de un profesor
SELECT * FROM v_carga_academica_profesor WHERE profesor_id = 1;

-- Ver estadísticas del periodo
SELECT * FROM v_estadisticas_matricula WHERE periodo_academico = '2024-1';
```

## Datos de Ejemplo

### Credenciales de Acceso

**Estudiantes:**
- Carlos Fernández: cfernandez@estudiante.edu (DNI: 72345678)
- María López: mlopez@estudiante.edu (DNI: 72456789)
- José García: jgarcia@estudiante.edu (DNI: 72567890)

**Profesores:**
- Juan Carlos Rodríguez: jrodriguez@academic.edu
- Ana María Torres: atorres@academic.edu
- Pedro Luis Sánchez: psanchez@academic.edu

### Cursos Disponibles

**Ingeniería de Sistemas:**
- IS101: Introducción a la Programación (4 créditos)
- IS201: Estructura de Datos (4 créditos) - Req: IS101
- IS301: Bases de Datos (4 créditos) - Req: IS201
- IS302: Arquitectura de Software (3 créditos) - Req: IS202

## Notas

- El script incluye `DROP TABLE IF EXISTS` al inicio, así que puede ejecutarse múltiples veces
- Las fechas de las secciones son para el periodo 2024-1 (Marzo - Julio 2024)
- Los costos de matrícula varían entre $350 y $450 por curso
- Las calificaciones están en escala de 0-20 (sistema peruano)
