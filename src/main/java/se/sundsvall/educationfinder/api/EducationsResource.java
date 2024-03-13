package se.sundsvall.educationfinder.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@RequestMapping("/educations")
@Tag(name = "EducationFinder", description = "Education finder")
class EducationsResource {

	public EducationsResource() {}

}
