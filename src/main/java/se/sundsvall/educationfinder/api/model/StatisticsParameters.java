package se.sundsvall.educationfinder.api.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistics parameters model")
public class StatisticsParameters {

	@Schema(description = "List of categories", example = "Medicin och vård")
	private List<String> categories;

	@Schema(description = "List of category IDs", example = "5")
	private List<String> categoryIds;

	@Schema(description = "List of education forms", example = "YH")
	private List<String> educationForms;

	@Schema(description = "List of study locations", example = "Sundsvall")
	private List<String> studyLocations;

	@NotNull
	@Schema(description = "Start date", example = "2024-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
	private LocalDate startDate;

	@NotNull
	@Schema(description = "End date", example = "2024-12-31", requiredMode = Schema.RequiredMode.REQUIRED)
	private LocalDate endDate;

	public static StatisticsParameters create() {
		return new StatisticsParameters();
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(final List<String> categories) {
		this.categories = categories;
	}

	public StatisticsParameters withCategories(final List<String> category) {
		this.categories = category;
		return this;
	}

	public List<String> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(final List<String> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public StatisticsParameters withCategoryIds(final List<String> categoryId) {
		this.categoryIds = categoryId;
		return this;
	}

	public List<String> getEducationForms() {
		return educationForms;
	}

	public void setEducationForms(final List<String> educationForms) {
		this.educationForms = educationForms;
	}

	public StatisticsParameters withEducationForms(final List<String> educationForm) {
		this.educationForms = educationForm;
		return this;
	}

	public List<String> getStudyLocations() {
		return studyLocations;
	}

	public void setStudyLocations(final List<String> studyLocations) {
		this.studyLocations = studyLocations;
	}

	public StatisticsParameters withStudyLocations(final List<String> studyLocation) {
		this.studyLocations = studyLocation;
		return this;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}

	public StatisticsParameters withStartDate(final LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}

	public StatisticsParameters withEndDate(final LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}

	@Override
	public String toString() {
		return "StatisticsParameters{" +
			"categories=" + categories +
			", categoryIds=" + categoryIds +
			", educationForms=" + educationForms +
			", studyLocations=" + studyLocations +
			", startDate=" + startDate +
			", endDate=" + endDate +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final StatisticsParameters that = (StatisticsParameters) o;
		return Objects.equals(categories, that.categories) && Objects.equals(categoryIds, that.categoryIds) && Objects.equals(educationForms, that.educationForms) && Objects.equals(studyLocations, that.studyLocations) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(categories, categoryIds, educationForms, studyLocations, startDate, endDate);
	}
}
