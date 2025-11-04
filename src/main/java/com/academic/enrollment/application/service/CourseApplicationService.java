package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.CreateCourseUseCase;
import com.academic.enrollment.application.port.in.FindCoursesByCareerUseCase;
import com.academic.enrollment.application.dto.command.CreateCourseCommand;
import com.academic.enrollment.application.dto.query.FindCoursesByCareerQuery;
import com.academic.enrollment.application.dto.response.CourseResponse;
import com.academic.enrollment.application.mapper.CourseMapper;
import com.academic.enrollment.domain.model.Course;
import com.academic.enrollment.domain.model.valueobjects.CareerId;
import com.academic.enrollment.domain.model.valueobjects.CourseCode;
import com.academic.enrollment.domain.model.valueobjects.Credits;
import com.academic.enrollment.domain.repository.CourseRepository;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class CourseApplicationService implements CreateCourseUseCase,
                                                   FindCoursesByCareerUseCase {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseApplicationService(CourseRepository courseRepository,
                                     CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseResponse create(CreateCourseCommand command) {
        // Validate unique code
        CourseCode code = new CourseCode(command.getCode());
        if (courseRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Course with code " + command.getCode() + " already exists");
        }

        // Create course
        Course course = Course.create(
                CareerId.of(command.getCareerId()),
                code,
                command.getName(),
                command.getDescription(),
                new Credits(command.getCredits()),
                command.getSemesterLevel()
        );

        // Save course
        Course savedCourse = courseRepository.save(course);

        // Convert to response DTO
        return courseMapper.toResponse(savedCourse, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> findByCareerId(FindCoursesByCareerQuery query) {
        List<Course> courses = courseRepository.findByCareerId(CareerId.of(query.getCareerId()));
        return courses.stream()
                .map(course -> courseMapper.toResponse(course, null))
                .collect(Collectors.toList());
    }
}
