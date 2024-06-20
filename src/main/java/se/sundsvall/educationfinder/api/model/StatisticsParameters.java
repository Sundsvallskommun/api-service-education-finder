package se.sundsvall.educationfinder.api.model;

import java.time.LocalDate;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistics parameters model")
public class StatisticsParameters {

	@Schema(description = "Category", example = "Information och media")
	private String category;

	@Schema(description = "Category id", example = "5")
	private String categoryId;

	@Schema(description = "Education form", example = "YH")
	private String educationForm;

	@Schema(description = "Study location", example = "Sundsvall")
	private String studyLocation;

	@Schema(description = "Start date", example = "2024-01-01")
	private LocalDate startDate;

	@Schema(description = "End date", example = "2024-12-31")
	private LocalDate endDate;

	public static StatisticsParameters create() {
		return new StatisticsParameters();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public StatisticsParameters withCategory(final String category) {
		this.category = category;
		return this;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final String categoryId) {
		this.categoryId = categoryId;
	}

	public StatisticsParameters withCategoryId(final String categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public String getEducationForm() {
		return educationForm;
	}

	public void setEducationForm(final String educationForm) {
		this.educationForm = educationForm;
	}

	public StatisticsParameters withEducationForm(final String educationForm) {
		this.educationForm = educationForm;
		return this;
	}

	public String getStudyLocation() {
		return studyLocation;
	}

	public void setStudyLocation(final String studyLocation) {
		this.studyLocation = studyLocation;
	}

	public StatisticsParameters withStudyLocation(final String studyLocation) {
		this.studyLocation = studyLocation;
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
			"category='" + category + '\'' +
			", categoryId='" + categoryId + '\'' +
			", educationForm='" + educationForm + '\'' +
			", studyLocation='" + studyLocation + '\'' +
			", startDate=" + startDate +
			", endDate=" + endDate +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final StatisticsParameters that = (StatisticsParameters) o;
		return Objects.equals(category, that.category) && Objects.equals(categoryId, that.categoryId) && Objects.equals(educationForm, that.educationForm) && Objects.equals(studyLocation, that.studyLocation) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, categoryId, educationForm, studyLocation, startDate, endDate);
	}
}
