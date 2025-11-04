package com.academic.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase principal de la aplicación Academic Enrollment System.
 *
 * Esta aplicación implementa Clean Architecture (Arquitectura Hexagonal)
 * con Domain-Driven Design para gestionar el proceso de matrícula universitaria.
 *
 * Características principales:
 * - Separación clara entre Domain, Application e Infrastructure
 * - Patrón CQRS (Commands, Queries, Responses)
 * - Domain Events para comunicación asíncrona
 * - Ports & Adapters para desacoplar la lógica de negocio
 *
 * @author Academic Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class AcademicEnrollmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicEnrollmentApplication.class, args);
    }
}
