-- init-db.sql
-- Script de inicialización de la base de datos Academic Enrollment

-- Mensaje de inicio
SELECT 'Iniciando configuración de la base de datos Academic Enrollment...' AS mensaje;

-- Configurar zona horaria
SET timezone = 'UTC';

-- Mensaje de confirmación
SELECT 'Base de datos configurada exitosamente!' AS mensaje;
SELECT 'Tablas serán creadas automáticamente por Hibernate (ddl-auto: update)' AS mensaje;

-- Nota: Las tablas se crearán automáticamente mediante JPA/Hibernate
-- cuando la aplicación Spring Boot se conecte por primera vez.
--
-- Si prefieres gestionar el esquema manualmente, desactiva
-- spring.jpa.hibernate.ddl-auto y crea las tablas aquí.
