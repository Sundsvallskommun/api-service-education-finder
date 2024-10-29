package se.sundsvall.educationfinder.api.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Sort;

import se.sundsvall.dept44.models.api.paging.AbstractParameterPagingAndSortingBase;

import io.swagger.v3.oas.annotations.media.Schema;

public class CourseParameters extends AbstractParameterPagingAndSortingBase {

	@Schema(description = "Course code", example = "KEMKEM02")
	private String code;

	@Schema(description = "Category", example = "Naturvetenskap")
	private String category;

	@Schema(description = "Subcategory", example = "Kemi")
	private String subcategory;

	@Schema(description = "Name of the course", example = "Etnicitet och kulturmöten")
	private String name;

	@Schema(description = "Provider of the course", example = "Sundsvalls Kommun")
	private String provider;

	@Schema(description = "Course credits", example = "100")
	private String credits;

	@Schema(description = "Information about the course", example = "This is course information")
	private String information;

	@Schema(description = "Language of instruction", example = "Swedish")
	private String languageOfInstruction;

	@Schema(description = "Search string", example = "searchString")
	private String searchString;

	@Schema(description = "Start date of the course", example = "2022-12-31")
	private LocalDate start;

	@Schema(description = "End date of the course", example = "2022-12-31")
	private LocalDate end;

	@Schema(description = "Earliest application date", example = "2022-12-31")
	private LocalDate earliestApplication;

	@Schema(description = "Latest application date", example = "2022-12-31")
	private LocalDate latestApplication;

	@Schema(description = "Scope of the course", example = "75")
	private List<String> scopes;

	@Schema(description = "Study location", example = "Sundsvall")
	private List<String> studyLocations;

	@Schema(description = "Level of the course", example = "gymnasial vuxenutbildning")
	private List<String> levels;

	public static CourseParameters create() {
		return new CourseParameters();
	}

	public CourseParameters withLimit(int limit) {
		this.limit = limit;
		return this;
	}

	public CourseParameters withPage(int page) {
		this.page = page;
		return this;
	}

	public CourseParameters withSortBy(List<String> sortBy) {
		this.sortBy = sortBy;
		return this;
	}

	public CourseParameters withSortDirection(Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
		return this;
	}

	public CourseParameters withCode(final String code) {
		this.code = code;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public CourseParameters withSubcategory(final String subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(final String subcategory) {
		this.subcategory = subcategory;
	}

	public CourseParameters withSearchString(final String searchString) {
		this.searchString = searchString;
		return this;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(final String searchString) {
		this.searchString = searchString;
	}

	public CourseParameters withCategory(final String category) {
		this.category = category;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public CourseParameters withName(final String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CourseParameters withProvider(final String provider) {
		this.provider = provider;
		return this;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(final String provider) {
		this.provider = provider;
	}

	public CourseParameters withCredits(final String credits) {
		this.credits = credits;
		return this;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(final String credits) {
		this.credits = credits;
	}

	public CourseParameters withInformation(final String information) {
		this.information = information;
		return this;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(final String information) {
		this.information = information;
	}

	public CourseParameters withLanguageOfInstruction(final String languageOfInstruction) {
		this.languageOfInstruction = languageOfInstruction;
		return this;
	}

	public String getLanguageOfInstruction() {
		return languageOfInstruction;
	}

	public void setLanguageOfInstruction(final String languageOfInstruction) {
		this.languageOfInstruction = languageOfInstruction;
	}

	public CourseParameters withStart(final LocalDate start) {
		this.start = start;
		return this;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(final LocalDate start) {
		this.start = start;
	}

	public CourseParameters withEnd(final LocalDate end) {
		this.end = end;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(final LocalDate end) {
		this.end = end;
	}

	public CourseParameters withEarliestApplication(final LocalDate earliestApplication) {
		this.earliestApplication = earliestApplication;
		return this;
	}

	public LocalDate getEarliestApplication() {
		return earliestApplication;
	}

	public void setEarliestApplication(final LocalDate earliestApplication) {
		this.earliestApplication = earliestApplication;
	}

	public CourseParameters withLatestApplication(final LocalDate latestApplication) {
		this.latestApplication = latestApplication;
		return this;
	}

	public LocalDate getLatestApplication() {
		return latestApplication;
	}

	public void setLatestApplication(final LocalDate latestApplication) {
		this.latestApplication = latestApplication;
	}

	public CourseParameters withScopes(final List<String> scopes) {
		this.scopes = scopes;
		return this;
	}

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(final List<String> scopes) {
		this.scopes = scopes;
	}

	public CourseParameters withStudyLocations(final List<String> studyLocation) {
		this.studyLocations = studyLocation;
		return this;
	}

	public List<String> getStudyLocations() {
		return studyLocations;
	}

	public void setStudyLocations(final List<String> studyLocation) {
		this.studyLocations = studyLocation;
	}

	public CourseParameters withLevels(final List<String> levels) {
		this.levels = levels;
		return this;
	}

	public List<String> getLevels() {
		return levels;
	}

	public void setLevels(final List<String> levels) {
		this.levels = levels;
	}

	@Override
	public String toString() {
		return "CourseParameters{" +
			"limit=" + limit +
			", page=" + page +
			", sortDirection=" + sortDirection +
			", sortBy=" + sortBy +
			", levels=" + levels +
			", studyLocations=" + studyLocations +
			", scopes=" + scopes +
			", latestApplication=" + latestApplication +
			", earliestApplication=" + earliestApplication +
			", end=" + end +
			", start=" + start +
			", searchString='" + searchString + '\'' +
			", languageOfInstruction='" + languageOfInstruction + '\'' +
			", information='" + information + '\'' +
			", credits='" + credits + '\'' +
			", provider='" + provider + '\'' +
			", name='" + name + '\'' +
			", subcategory='" + subcategory + '\'' +
			", category='" + category + '\'' +
			", code='" + code + '\'' +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		final CourseParameters that = (CourseParameters) o;
		return Objects.equals(code, that.code) && Objects.equals(category, that.category) && Objects.equals(subcategory, that.subcategory) && Objects.equals(name, that.name) && Objects.equals(provider, that.provider) && Objects.equals(credits, that.credits) && Objects.equals(information, that.information) && Objects.equals(languageOfInstruction, that.languageOfInstruction) && Objects.equals(searchString, that.searchString) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(earliestApplication, that.earliestApplication) && Objects.equals(latestApplication, that.latestApplication) && Objects.equals(scopes, that.scopes) && Objects.equals(studyLocations, that.studyLocations) && Objects.equals(levels, that.levels);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), code, category, subcategory, name, provider, credits, information, languageOfInstruction, searchString, start, end, earliestApplication, latestApplication, scopes, studyLocations, levels);
	}
}
