package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.FindSectionsByPeriodUseCase;
import com.academic.enrollment.application.dto.query.FindSectionsByPeriodQuery;
import com.academic.enrollment.application.dto.response.SectionResponse;
import com.academic.enrollment.application.mapper.SectionMapper;
import com.academic.enrollment.domain.model.*;
import com.academic.enrollment.domain.model.valueobjects.AcademicPeriod;
import com.academic.enrollment.domain.repository.*;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional(readOnly = true)
public class SectionApplicationService implements FindSectionsByPeriodUseCase {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final SectionMapper sectionMapper;

    public SectionApplicationService(SectionRepository sectionRepository,
                                    CourseRepository courseRepository,
                                    ProfessorRepository professorRepository,
                                    EnrollmentRepository enrollmentRepository,
                                    SectionMapper sectionMapper) {
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.sectionMapper = sectionMapper;
    }

    @Override
    public List<SectionResponse> findByPeriod(FindSectionsByPeriodQuery query) {
        AcademicPeriod period = new AcademicPeriod(query.getAcademicPeriod());

        List<Section> sections = sectionRepository.findByAcademicPeriod(period);

        // Filtrar por curso si se especifica
        if (query.getCourseId() != null) {
            sections = sections.stream()
                    .filter(section -> section.getCourseId().toString().equals(query.getCourseId()))
                    .collect(Collectors.toList());
        }

        return sections.stream()
                .map(this::enrichSectionResponse)
                .collect(Collectors.toList());
    }

    private SectionResponse enrichSectionResponse(Section section) {
        Course course = courseRepository.findById(section.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Professor professor = professorRepository.findById(section.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor not found"));

        int currentEnrollment = enrollmentRepository.countBySectionId(section.getId());

        return sectionMapper.toResponse(section, course, professor, currentEnrollment);
    }
}
