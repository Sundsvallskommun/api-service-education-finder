package se.sundsvall.educationfinder.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.educationfinder.api.model.Statistics;
import se.sundsvall.educationfinder.api.model.StatisticsParameters;
import se.sundsvall.educationfinder.service.StatisticsService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static se.sundsvall.educationfinder.api.model.ApiConstants.GET_STATISTICS_DESCRIPTION;

@RestController
@Validated
@RequestMapping(path = "/{municipalityId}/statistics", produces = {
	APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE
})
@Tag(name = "Statistics", description = "Get statistics")
@ApiResponse(responseCode = "200", description = "Successful operation", useReturnTypeSchema = true)
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class StatisticsResource {

	private final StatisticsService service;

	StatisticsResource(final StatisticsService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(description = GET_STATISTICS_DESCRIPTION)
	ResponseEntity<Statistics> getStatistics(
		@Parameter(name = "municipalityId", description = "Municipality id", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId,
		@ParameterObject @Valid final StatisticsParameters parameters) {
		return ok(service.getStatisticsByParameters(parameters));
	}

	@GetMapping(path = "/filters/{filterAttribute}/values")
	@Operation(summary = "Find available filter values")
	ResponseEntity<List<String>> findFilterValues(
		@Parameter(name = "municipalityId", description = "Municipality id", example = "2281") @ValidMunicipalityId @PathVariable final String municipalityId,
		@Parameter(description = "The attribute name to get available values from") @PathVariable final String filterAttribute) {
		return ok(service.findStatisticsFilterValues(filterAttribute));
	}

}
