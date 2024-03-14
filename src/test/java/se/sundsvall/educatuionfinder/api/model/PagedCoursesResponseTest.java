package se.sundsvall.educatuionfinder.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import se.sundsvall.dept44.models.api.paging.PagingMetaData;
import se.sundsvall.educationfinder.api.model.Course;
import se.sundsvall.educationfinder.api.model.PagedCoursesResponse;

class PagedCoursesResponseTest {

	@Test
	void testBean() {
		assertThat(PagedCoursesResponse.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {

		final var courses = List.of(Course.create());
		final var pagingMetadata = PagingMetaData.create();

		final var bean = PagedCoursesResponse.create()
			.withCourses(courses)
			.withMetaData(pagingMetadata);

		assertThat(bean).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(bean.getCourses()).isEqualTo(courses);
		assertThat(bean.getMetadata()).isEqualTo(pagingMetadata);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(PagedCoursesResponse.create()).hasAllNullFieldsOrProperties();
		assertThat(new PagedCoursesResponse()).hasAllNullFieldsOrProperties();
	}
}
