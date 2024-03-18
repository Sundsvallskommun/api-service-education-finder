package se.sundsvall.educationfinder.service;

import static se.sundsvall.educationfinder.service.mapper.CourseMapper.toPagedCoursesResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import se.sundsvall.educationfinder.api.model.PagedCoursesResponse;
import se.sundsvall.educationfinder.integration.db.CourseRepository;
import se.sundsvall.educationfinder.integration.db.specification.CourseSpecification;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public PagedCoursesResponse find(CourseSpecification specification, Pageable pageable) {
		return toPagedCoursesResponse(courseRepository.findAll(specification, pageable));
	}
}
