package se.sundsvall.educationfinder.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static se.sundsvall.educationfinder.api.model.ApiConstants.FIND_COURSE_DESCRIPTION;
import static se.sundsvall.educationfinder.api.model.ApiConstants.FIND_FILTER_VALUES_DESCRITPTION;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;

import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.educationfinder.api.model.Course;
import se.sundsvall.educationfinder.api.model.CourseParameters;
import se.sundsvall.educationfinder.api.model.PagedCoursesResponse;
import se.sundsvall.educationfinder.api.model.enums.CourseFilter;
import se.sundsvall.educationfinder.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@RequestMapping(path = "/{municipalityId}/courses", produces = {APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE})
@Tag(name = "Courses", description = "Find courses")
@ApiResponse(responseCode = "200", description = "Successful operation", useReturnTypeSchema = true)
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {Problem.class, ConstraintViolationProblem.class})))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class CoursesResource {

	private final CourseService courseService;

	CoursesResource(final CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	@Operation(summary = "Find course", description = FIND_COURSE_DESCRIPTION)
	ResponseEntity<PagedCoursesResponse> find(
		@Parameter(name = "municipalityId", description = "Municipality id", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId,
		final CourseParameters parameters) {
		return ok(courseService.find(parameters));
	}

	@GetMapping(path = "/{courseId}")
	@Operation(summary = "Find course by id")
	ResponseEntity<Course> findCourseById(
		@Parameter(name = "municipalityId", description = "Municipality id", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId,
		@Parameter(name = "courseId", description = "The id of the course", example = "1234") @PathVariable final Long courseId) {
		return ok(courseService.findCourseById(courseId));
	}


	@GetMapping(path = "/filters/{courseFilter}/values")
	@Operation(summary = "Find available filter values", description = FIND_FILTER_VALUES_DESCRITPTION)
	ResponseEntity<List<String>> findFilterValues(
		@Parameter(name = "municipalityId", description = "Municipality id", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId,
		@Parameter(description = "The attribute name to get available values from") @PathVariable final CourseFilter courseFilter) {
		return ok(courseService.findFilterValues(courseFilter));
	}

}
