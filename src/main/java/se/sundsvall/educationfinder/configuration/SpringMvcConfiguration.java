package se.sundsvall.educationfinder.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import se.sundsvall.educationfinder.api.converter.StringToCourseFilterConverter;
import se.sundsvall.educationfinder.api.converter.StringToStatisticsFilterConverter;

import net.kaczmarzyk.spring.data.jpa.swagger.springdoc.SpecificationArgResolverSpringdocOperationCustomizer;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

@Configuration
class SpringMvcConfiguration implements WebMvcConfigurer {

	@Bean
	SpecificationArgResolverSpringdocOperationCustomizer specificationArgResolverSpringdocOperationCustomizer() {
		return new SpecificationArgResolverSpringdocOperationCustomizer();
	}

	@Override
	public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new SpecificationArgumentResolver());
		resolvers.add(new PageableHandlerMethodArgumentResolver());
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToCourseFilterConverter());
		registry.addConverter(new StringToStatisticsFilterConverter());
	}
}
