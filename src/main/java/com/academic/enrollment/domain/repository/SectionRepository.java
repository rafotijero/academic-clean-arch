package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Section;
import com.academic.enrollment.domain.model.valueobjects.SectionId;
import com.academic.enrollment.domain.model.valueobjects.CourseId;
import com.academic.enrollment.domain.model.valueobjects.ProfessorId;
import com.academic.enrollment.domain.model.valueobjects.AcademicPeriod;

import java.util.List;
import java.util.Optional;

public interface SectionRepository {
    Section save(Section section);

    Optional<Section> findById(SectionId id);

    List<Section> findAll();

    List<Section> findActiveSections();

    List<Section> findByCourseId(CourseId courseId);

    List<Section> findByProfessorId(ProfessorId professorId);

    List<Section> findByAcademicPeriod(AcademicPeriod academicPeriod);

    List<Section> findByCourseAndPeriod(CourseId courseId, AcademicPeriod academicPeriod);

    Optional<Section> findByCourseCodeAndPeriod(CourseId courseId, String code, AcademicPeriod academicPeriod);

    void delete(SectionId id);

    boolean existsByCourseCodeAndPeriod(CourseId courseId, String code, AcademicPeriod academicPeriod);
}
