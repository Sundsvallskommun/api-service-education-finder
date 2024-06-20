package se.sundsvall.educationfinder.integration.db.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SubjectEntityTest {

	@Test
	void testBean() {
		assertThat(SubjectEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = 1L;
		final var subjectId = 2;
		final var subject = "subject";
		final var subjectEnglish = "subjectEnglish";
		final var educationForm = "educationForm";
		final var subjectCode = "subjectCode";
		final var categoryId = 3;
		final var category = "category";
		final var categoryEnglish = "categoryEnglish";
		final var branchId = 4;
		final var branch = "branch";
		final var branchEnglish = "branchEnglish";

		final var bean = SubjectEntity.create()
			.withId(id)
			.withSubjectId(subjectId)
			.withSubject(subject)
			.withSubjectCode(subjectCode)
			.withSubjectEnglish(subjectEnglish)
			.withEducationForm(educationForm)
			.withCategoryId(categoryId)
			.withCategory(category)
			.withCategoryEnglish(categoryEnglish)
			.withBranchId(branchId)
			.withBranch(branch)
			.withBranchEnglish(branchEnglish);

		Assertions.assertThat(bean).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(bean.getId()).isEqualTo(id);
		Assertions.assertThat(bean.getSubjectId()).isEqualTo(subjectId);
		Assertions.assertThat(bean.getSubjectCode()).isEqualTo(subjectCode);
		Assertions.assertThat(bean.getSubject()).isEqualTo(subject);
		Assertions.assertThat(bean.getSubjectEnglish()).isEqualTo(subjectEnglish);
		Assertions.assertThat(bean.getEducationForm()).isEqualTo(educationForm);
		Assertions.assertThat(bean.getCategoryId()).isEqualTo(categoryId);
		Assertions.assertThat(bean.getCategory()).isEqualTo(category);
		Assertions.assertThat(bean.getCategoryEnglish()).isEqualTo(categoryEnglish);
		Assertions.assertThat(bean.getBranchId()).isEqualTo(branchId);
		Assertions.assertThat(bean.getBranch()).isEqualTo(branch);
		Assertions.assertThat(bean.getBranchEnglish()).isEqualTo(branchEnglish);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(SubjectEntity.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new SubjectEntity()).hasAllNullFieldsOrProperties();
	}
}