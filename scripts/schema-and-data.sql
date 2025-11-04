-- =====================================================
-- SCHEMA Y DATOS DE EJEMPLO - ACADEMIC ENROLLMENT SYSTEM
-- Sistema de Matrícula Universitaria
-- =====================================================

-- Eliminar objetos existentes (para re-ejecutar el script)
DROP VIEW IF EXISTS v_estadisticas_matricula CASCADE;
DROP VIEW IF EXISTS v_carga_academica_profesor CASCADE;
DROP VIEW IF EXISTS v_historial_academico CASCADE;
DROP VIEW IF EXISTS v_matriculas CASCADE;

DROP TABLE IF EXISTS calificacion CASCADE;
DROP TABLE IF EXISTS pago CASCADE;
DROP TABLE IF EXISTS matricula CASCADE;
DROP TABLE IF EXISTS seccion CASCADE;
DROP TABLE IF EXISTS prerrequisito CASCADE;
DROP TABLE IF EXISTS curso CASCADE;
DROP TABLE IF EXISTS carrera CASCADE;
DROP TABLE IF EXISTS facultad CASCADE;
DROP TABLE IF EXISTS profesor CASCADE;
DROP TABLE IF EXISTS estudiante CASCADE;

-- =====================================================
-- CREACIÓN DE TABLAS
-- =====================================================

-- Tabla ESTUDIANTE
CREATE TABLE estudiante (
    estudiante_id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla PROFESOR
CREATE TABLE profesor (
    profesor_id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    especialidad VARCHAR(100),
    titulo_academico VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla FACULTAD
CREATE TABLE facultad (
    facultad_id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    descripcion TEXT,
    ubicacion VARCHAR(100),
    decano VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla CARRERA
CREATE TABLE carrera (
    carrera_id BIGSERIAL PRIMARY KEY,
    facultad_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    duracion_semestres INTEGER NOT NULL,
    titulo_otorgado VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_facultad FOREIGN KEY (facultad_id) REFERENCES facultad(facultad_id) ON DELETE RESTRICT,
    CONSTRAINT uk_carrera_nombre UNIQUE (nombre)
);

-- Tabla CURSO
CREATE TABLE curso (
    curso_id BIGSERIAL PRIMARY KEY,
    carrera_id BIGINT NOT NULL,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    creditos INTEGER NOT NULL,
    nivel_semestre INTEGER NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_carrera FOREIGN KEY (carrera_id) REFERENCES carrera(carrera_id) ON DELETE RESTRICT,
    CONSTRAINT ck_creditos CHECK (creditos > 0),
    CONSTRAINT ck_nivel_semestre CHECK (nivel_semestre > 0)
);

-- Tabla PRERREQUISITO
CREATE TABLE prerrequisito (
    prerrequisito_id BIGSERIAL PRIMARY KEY,
    curso_id BIGINT NOT NULL,
    curso_req_id BIGINT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES curso(curso_id) ON DELETE CASCADE,
    CONSTRAINT fk_curso_req FOREIGN KEY (curso_req_id) REFERENCES curso(curso_id) ON DELETE CASCADE,
    CONSTRAINT uk_prerrequisito UNIQUE (curso_id, curso_req_id),
    CONSTRAINT ck_curso_diferente CHECK (curso_id != curso_req_id)
);

-- Tabla SECCION
CREATE TABLE seccion (
    seccion_id BIGSERIAL PRIMARY KEY,
    curso_id BIGINT NOT NULL,
    profesor_id BIGINT NOT NULL,
    codigo VARCHAR(20) NOT NULL,
    capacidad_maxima INTEGER NOT NULL,
    aula VARCHAR(50),
    horario VARCHAR(50),
    dias VARCHAR(50),
    periodo_academico VARCHAR(20) NOT NULL,
    fecha_inicio DATE,
    fecha_fin DATE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES curso(curso_id) ON DELETE RESTRICT,
    CONSTRAINT fk_profesor FOREIGN KEY (profesor_id) REFERENCES profesor(profesor_id) ON DELETE RESTRICT,
    CONSTRAINT uk_seccion_periodo UNIQUE (curso_id, codigo, periodo_academico),
    CONSTRAINT ck_capacidad_maxima CHECK (capacidad_maxima > 0)
);

-- Tabla MATRICULA
CREATE TABLE matricula (
    matricula_id BIGSERIAL PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    seccion_id BIGINT NOT NULL,
    fecha_matricula DATE NOT NULL DEFAULT CURRENT_DATE,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    costo NUMERIC(10, 2) NOT NULL,
    metodo_pago VARCHAR(50),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiante(estudiante_id) ON DELETE RESTRICT,
    CONSTRAINT fk_seccion FOREIGN KEY (seccion_id) REFERENCES seccion(seccion_id) ON DELETE RESTRICT,
    CONSTRAINT uk_matricula_seccion UNIQUE (estudiante_id, seccion_id),
    CONSTRAINT ck_estado CHECK (estado IN ('PENDIENTE', 'PAGADO', 'ANULADO', 'COMPLETADO')),
    CONSTRAINT ck_costo CHECK (costo >= 0)
);

-- Tabla PAGO
CREATE TABLE pago (
    pago_id BIGSERIAL PRIMARY KEY,
    matricula_id BIGINT NOT NULL,
    fecha_pago DATE NOT NULL DEFAULT CURRENT_DATE,
    monto NUMERIC(10, 2) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    referencia VARCHAR(100),
    estado VARCHAR(20) NOT NULL DEFAULT 'PROCESADO',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_matricula FOREIGN KEY (matricula_id) REFERENCES matricula(matricula_id) ON DELETE RESTRICT,
    CONSTRAINT ck_monto CHECK (monto > 0),
    CONSTRAINT ck_estado_pago CHECK (estado IN ('PENDIENTE', 'PROCESADO', 'RECHAZADO'))
);

-- Tabla CALIFICACION
CREATE TABLE calificacion (
    calificacion_id BIGSERIAL PRIMARY KEY,
    matricula_id BIGINT NOT NULL,
    nota NUMERIC(5, 2),
    observacion TEXT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_matricula FOREIGN KEY (matricula_id) REFERENCES matricula(matricula_id) ON DELETE RESTRICT,
    CONSTRAINT uk_calificacion_matricula UNIQUE (matricula_id),
    CONSTRAINT ck_nota CHECK (nota >= 0 AND nota <= 20)
);

-- =====================================================
-- ÍNDICES PARA MEJORAR RENDIMIENTO
-- =====================================================

CREATE INDEX idx_estudiante_apellido ON estudiante(apellido);
CREATE INDEX idx_profesor_apellido ON profesor(apellido);
CREATE INDEX idx_curso_nombre ON curso(nombre);
CREATE INDEX idx_curso_codigo ON curso(codigo);
CREATE INDEX idx_matricula_estudiante ON matricula(estudiante_id);
CREATE INDEX idx_matricula_seccion ON matricula(seccion_id);
CREATE INDEX idx_seccion_curso ON seccion(curso_id);
CREATE INDEX idx_seccion_profesor ON seccion(profesor_id);
CREATE INDEX idx_seccion_periodo ON seccion(periodo_academico);
CREATE INDEX idx_carrera_facultad ON carrera(facultad_id);

-- =====================================================
-- DATOS DE EJEMPLO
-- =====================================================

-- FACULTADES
INSERT INTO facultad (nombre, descripcion, ubicacion, decano) VALUES
('Facultad de Ingeniería', 'Facultad dedicada a las ciencias de la ingeniería y tecnología', 'Edificio A, Campus Central', 'Dr. Roberto Pérez'),
('Facultad de Ciencias', 'Facultad de ciencias exactas y naturales', 'Edificio B, Campus Central', 'Dra. María González'),
('Facultad de Humanidades', 'Facultad de letras, filosofía y ciencias sociales', 'Edificio C, Campus Norte', 'Dr. Carlos Mendoza');

-- CARRERAS
INSERT INTO carrera (facultad_id, nombre, descripcion, duracion_semestres, titulo_otorgado) VALUES
(1, 'Ingeniería de Sistemas', 'Carrera enfocada en el desarrollo de software y sistemas de información', 10, 'Ingeniero de Sistemas'),
(1, 'Ingeniería Industrial', 'Carrera enfocada en la optimización de procesos y sistemas productivos', 10, 'Ingeniero Industrial'),
(2, 'Matemática', 'Carrera dedicada al estudio de las matemáticas puras y aplicadas', 10, 'Licenciado en Matemática'),
(3, 'Psicología', 'Carrera dedicada al estudio del comportamiento humano', 10, 'Licenciado en Psicología');

-- PROFESORES
INSERT INTO profesor (nombre, apellido, dni, email, telefono, especialidad, titulo_academico) VALUES
('Juan Carlos', 'Rodríguez', '12345678', 'jrodriguez@academic.edu', '+51-999-111-222', 'Bases de Datos', 'Magíster en Ciencias de la Computación'),
('Ana María', 'Torres', '23456789', 'atorres@academic.edu', '+51-999-222-333', 'Algoritmos y Estructuras de Datos', 'Doctora en Ciencias de la Computación'),
('Pedro Luis', 'Sánchez', '34567890', 'psanchez@academic.edu', '+51-999-333-444', 'Arquitectura de Software', 'Magíster en Ingeniería de Software'),
('Laura Isabel', 'Morales', '45678901', 'lmorales@academic.edu', '+51-999-444-555', 'Cálculo y Análisis Matemático', 'Doctora en Matemática Aplicada');

-- ESTUDIANTES
INSERT INTO estudiante (nombre, apellido, dni, email, telefono, fecha_nacimiento, direccion) VALUES
('Carlos Alberto', 'Fernández', '72345678', 'cfernandez@estudiante.edu', '+51-987-111-222', '2004-03-15', 'Av. Los Olivos 123, Lima'),
('María Teresa', 'López', '72456789', 'mlopez@estudiante.edu', '+51-987-222-333', '2003-07-22', 'Jr. Las Flores 456, Lima'),
('José Antonio', 'García', '72567890', 'jgarcia@estudiante.edu', '+51-987-333-444', '2004-11-08', 'Calle Los Pinos 789, Lima'),
('Ana Sofía', 'Martínez', '72678901', 'amartinez@estudiante.edu', '+51-987-444-555', '2003-01-30', 'Av. El Sol 321, Lima'),
('Diego Fernando', 'Ramírez', '72789012', 'dramirez@estudiante.edu', '+51-987-555-666', '2004-05-18', 'Jr. La Luna 654, Lima'),
('Patricia Elena', 'Castro', '72890123', 'pcastro@estudiante.edu', '+51-987-666-777', '2003-09-25', 'Calle Las Estrellas 987, Lima');

-- CURSOS
INSERT INTO curso (carrera_id, codigo, nombre, descripcion, creditos, nivel_semestre) VALUES
(1, 'IS101', 'Introducción a la Programación', 'Fundamentos de programación estructurada', 4, 1),
(1, 'IS102', 'Matemática Discreta', 'Lógica, conjuntos y teoría de grafos', 3, 1),
(1, 'IS201', 'Estructura de Datos', 'Listas, árboles, grafos y algoritmos fundamentales', 4, 2),
(1, 'IS202', 'Programación Orientada a Objetos', 'Paradigma orientado a objetos con Java', 4, 2),
(1, 'IS301', 'Bases de Datos', 'Diseño y gestión de bases de datos relacionales', 4, 3),
(1, 'IS302', 'Arquitectura de Software', 'Patrones de diseño y arquitecturas modernas', 3, 3),
(1, 'IS401', 'Desarrollo de Aplicaciones Web', 'Frontend y backend con tecnologías modernas', 4, 4),
(3, 'MAT101', 'Cálculo I', 'Límites, derivadas e integrales', 4, 1),
(3, 'MAT201', 'Cálculo II', 'Integrales múltiples y series', 4, 2),
(4, 'PSI101', 'Introducción a la Psicología', 'Fundamentos de la psicología', 3, 1);

-- PRERREQUISITOS
INSERT INTO prerrequisito (curso_id, curso_req_id) VALUES
(3, 1),  -- Estructura de Datos requiere Intro Programación
(4, 1),  -- POO requiere Intro Programación
(5, 3),  -- Bases de Datos requiere Estructura de Datos
(6, 4),  -- Arquitectura requiere POO
(7, 5),  -- Desarrollo Web requiere Bases de Datos
(7, 6),  -- Desarrollo Web requiere Arquitectura
(9, 8);  -- Cálculo II requiere Cálculo I

-- SECCIONES (Periodo 2024-1)
INSERT INTO seccion (curso_id, profesor_id, codigo, capacidad_maxima, aula, horario, dias, periodo_academico, fecha_inicio, fecha_fin) VALUES
(1, 2, 'A', 30, 'Lab-101', '08:00-10:00', 'Lunes, Miércoles', '2024-1', '2024-03-18', '2024-07-12'),
(2, 4, 'A', 35, 'Aula-201', '10:00-12:00', 'Martes, Jueves', '2024-1', '2024-03-18', '2024-07-12'),
(3, 2, 'A', 25, 'Lab-102', '14:00-16:00', 'Lunes, Miércoles', '2024-1', '2024-03-18', '2024-07-12'),
(4, 3, 'A', 30, 'Lab-103', '08:00-10:00', 'Martes, Jueves', '2024-1', '2024-03-18', '2024-07-12'),
(5, 1, 'A', 28, 'Lab-201', '16:00-18:00', 'Lunes, Miércoles', '2024-1', '2024-03-18', '2024-07-12');

-- MATRÍCULAS
INSERT INTO matricula (estudiante_id, seccion_id, fecha_matricula, estado, costo, metodo_pago) VALUES
(1, 1, '2024-02-15', 'PAGADO', 450.00, 'Tarjeta de Crédito'),
(1, 2, '2024-02-15', 'PAGADO', 350.00, 'Tarjeta de Crédito'),
(1, 3, '2024-02-20', 'PAGADO', 450.00, 'Transferencia'),
(2, 1, '2024-02-16', 'PAGADO', 450.00, 'Efectivo'),
(2, 4, '2024-02-16', 'PAGADO', 450.00, 'Efectivo'),
(3, 2, '2024-02-17', 'PAGADO', 350.00, 'Tarjeta de Débito'),
(3, 3, '2024-02-22', 'PENDIENTE', 450.00, NULL),
(4, 4, '2024-02-18', 'PAGADO', 450.00, 'Transferencia'),
(4, 5, '2024-02-18', 'PAGADO', 450.00, 'Transferencia');

-- PAGOS
INSERT INTO pago (matricula_id, fecha_pago, monto, metodo_pago, referencia, estado) VALUES
(1, '2024-02-15', 450.00, 'Tarjeta de Crédito', 'TXN-2024-001', 'PROCESADO'),
(2, '2024-02-15', 350.00, 'Tarjeta de Crédito', 'TXN-2024-002', 'PROCESADO'),
(3, '2024-02-20', 450.00, 'Transferencia', 'TXN-2024-003', 'PROCESADO'),
(4, '2024-02-16', 450.00, 'Efectivo', 'REC-2024-001', 'PROCESADO'),
(5, '2024-02-16', 450.00, 'Efectivo', 'REC-2024-002', 'PROCESADO'),
(6, '2024-02-17', 350.00, 'Tarjeta de Débito', 'TXN-2024-004', 'PROCESADO'),
(8, '2024-02-18', 450.00, 'Transferencia', 'TXN-2024-005', 'PROCESADO'),
(9, '2024-02-18', 450.00, 'Transferencia', 'TXN-2024-006', 'PROCESADO');

-- CALIFICACIONES (algunas ya registradas)
INSERT INTO calificacion (matricula_id, nota, observacion) VALUES
(1, 16.50, 'Excelente desempeño en los laboratorios'),
(2, 14.00, 'Buen rendimiento en los parciales'),
(4, 17.00, 'Destacada participación en clase'),
(5, 15.50, 'Muy buen trabajo en proyectos'),
(8, 18.00, 'Excelente comprensión de POO');



-- =====================================================
-- MENSAJES DE CONFIRMACIÓN
-- =====================================================

SELECT 'Base de datos inicializada exitosamente!' AS mensaje;
SELECT 'Tablas creadas: ' || COUNT(*) AS tablas_creadas
FROM information_schema.tables
WHERE table_schema = 'public' AND table_type = 'BASE TABLE';
SELECT 'Vistas creadas: ' || COUNT(*) AS vistas_creadas
FROM information_schema.views
WHERE table_schema = 'public';
