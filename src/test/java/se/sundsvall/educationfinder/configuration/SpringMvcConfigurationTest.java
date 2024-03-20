package se.sundsvall.educationfinder.configuration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.kaczmarzyk.spring.data.jpa.swagger.springdoc.SpecificationArgResolverSpringdocOperationCustomizer;
import se.sundsvall.educationfinder.Application;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("junit")
class SpringMvcConfigurationTest {

	@Autowired(required = false)
	private SpecificationArgResolverSpringdocOperationCustomizer specificationArgResolverSpringdocOperationCustomizer;

	@Test
	void testBean() {
		assertThat(specificationArgResolverSpringdocOperationCustomizer).isNotNull();
	}
}
