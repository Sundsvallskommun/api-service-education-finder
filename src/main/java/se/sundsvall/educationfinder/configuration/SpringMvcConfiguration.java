package se.sundsvall.educationfinder.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.kaczmarzyk.spring.data.jpa.swagger.springdoc.SpecificationArgResolverSpringdocOperationCustomizer;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import se.sundsvall.educationfinder.api.converter.StringToCourseFilterConverter;

@Configuration
class SpringMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new SpecificationArgumentResolver());
		resolvers.add(new PageableHandlerMethodArgumentResolver());
	}

	@Bean
	SpecificationArgResolverSpringdocOperationCustomizer specificationArgResolverSpringdocOperationCustomizer() {
		return new SpecificationArgResolverSpringdocOperationCustomizer();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToCourseFilterConverter());
	}
}
