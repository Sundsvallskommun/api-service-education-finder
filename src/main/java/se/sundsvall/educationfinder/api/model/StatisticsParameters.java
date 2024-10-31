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

	@Schema(description = "List of sub categories", example = "PSYKOTERAPI")
	private List<String> subCategories;

	@Schema(description = "List of levels", example = "gymnasial vuxenutbildning")
	private List<String> levels;

	@Schema(description = "List of study locations", example = "Sundsvall")
	private List<String> studyLocations;

	@Schema(description = "List of scopes", example = "25, 50, 75")
	private List<String> scopes;

	@NotNull
	@Schema(description = "Start date", example = "2024-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
	private LocalDate startDate;

	@NotNull
	@Schema(description = "End date", example = "2024-12-31", requiredMode = Schema.RequiredMode.REQUIRED)
	private LocalDate endDate;

	public static StatisticsParameters create() {
		return new StatisticsParameters();
	}

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(final List<String> scopes) {
		this.scopes = scopes;
	}

	public StatisticsParameters withScopes(final List<String> scope) {
		this.scopes = scope;
		return this;
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

	public List<String> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(final List<String> subCategories) {
		this.subCategories = subCategories;
	}

	public StatisticsParameters withSubCategories(final List<String> subCategory) {
		this.subCategories = subCategory;
		return this;
	}

	public List<String> getLevels() {
		return levels;
	}

	public void setLevels(final List<String> levels) {
		this.levels = levels;
	}

	public StatisticsParameters withLevels(final List<String> level) {
		this.levels = level;
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
			", subCategories=" + subCategories +
			", levels=" + levels +
			", studyLocations=" + studyLocations +
			", scopes=" + scopes +
			", startDate=" + startDate +
			", endDate=" + endDate +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final StatisticsParameters that = (StatisticsParameters) o;
		return Objects.equals(categories, that.categories) && Objects.equals(subCategories, that.subCategories) && Objects.equals(levels, that.levels) && Objects.equals(studyLocations, that.studyLocations) && Objects.equals(scopes, that.scopes) && Objects
			.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(categories, subCategories, levels, studyLocations, scopes, startDate, endDate);
	}
}
