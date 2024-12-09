package se.sundsvall.educationfinder.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Sort;
import se.sundsvall.dept44.models.api.paging.AbstractParameterPagingAndSortingBase;

public class CourseParameters extends AbstractParameterPagingAndSortingBase {

	@Schema(description = "Course code", example = "KEMKEM02")
	private String code;

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

	@Schema(description = "Start date of the course is after", example = "2022-12-31")
	private LocalDate startAfter;

	@Schema(description = "Start date of the course is before", example = "2022-12-31")
	private LocalDate startBefore;

	@Schema(description = "End date of the course", example = "2022-12-31")
	private LocalDate end;

	@Schema(description = "End date of the course is after", example = "2022-12-31")
	private LocalDate endAfter;

	@Schema(description = "End date of the course is before", example = "2022-12-31")
	private LocalDate endBefore;

	@Schema(description = "Earliest application date", example = "2022-12-31")
	private LocalDate earliestApplication;

	@Schema(description = "Earliest application date is after", example = "2022-12-31")
	private LocalDate earliestApplicationAfter;

	@Schema(description = "Earliest application date is before", example = "2022-12-31")
	private LocalDate earliestApplicationBefore;

	@Schema(description = "Latest application date", example = "2022-12-31")
	private LocalDate latestApplication;

	@Schema(description = "Latest application date is after", example = "2022-12-31")
	private LocalDate latestApplicationAfter;

	@Schema(description = "Latest application date is before", example = "2022-12-31")
	private LocalDate latestApplicationBefore;

	@Schema(description = "Scope of the course", example = "75")
	private List<Integer> scopes;

	@Schema(description = "Study location", example = "Sundsvall")
	private List<String> studyLocations;

	@Schema(description = "Level of the course", example = "gymnasial vuxenutbildning")
	private List<String> levels;

	@Schema(description = "Category", example = "Naturvetenskap")
	private List<String> categories;

	@Schema(description = "Subcategory", example = "Kemi")
	private List<String> subcategories;

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

	public CourseParameters withCategories(final List<String> categories) {
		this.categories = categories;
		return this;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(final List<String> categories) {
		this.categories = categories;
	}

	public CourseParameters withSubcategories(final List<String> subcategories) {
		this.subcategories = subcategories;
		return this;
	}

	public List<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(final List<String> subcategories) {
		this.subcategories = subcategories;
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

	public CourseParameters withScopes(final List<Integer> scopes) {
		this.scopes = scopes;
		return this;
	}

	public List<Integer> getScopes() {
		return scopes;
	}

	public void setScopes(final List<Integer> scopes) {
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

	public CourseParameters withStartAfter(final LocalDate startAfter) {
		this.startAfter = startAfter;
		return this;
	}

	public LocalDate getStartAfter() {
		return startAfter;
	}

	public void setStartAfter(LocalDate startAfter) {
		this.startAfter = startAfter;
	}

	public CourseParameters withStartBefore(final LocalDate startBefore) {
		this.startBefore = startBefore;
		return this;
	}

	public LocalDate getStartBefore() {
		return startBefore;
	}

	public void setStartBefore(LocalDate startBefore) {
		this.startBefore = startBefore;
	}

	public CourseParameters withEndAfter(final LocalDate endAfter) {
		this.endAfter = endAfter;
		return this;
	}

	public LocalDate getEndAfter() {
		return endAfter;
	}

	public void setEndAfter(LocalDate endAfter) {
		this.endAfter = endAfter;
	}

	public CourseParameters withEndBefore(final LocalDate endBefore) {
		this.endBefore = endBefore;
		return this;
	}

	public LocalDate getEndBefore() {
		return endBefore;
	}

	public void setEndBefore(LocalDate endBefore) {
		this.endBefore = endBefore;
	}

	public CourseParameters withEarliestApplicationAfter(final LocalDate earliestApplicationAfter) {
		this.earliestApplicationAfter = earliestApplicationAfter;
		return this;
	}

	public LocalDate getEarliestApplicationAfter() {
		return earliestApplicationAfter;
	}

	public void setEarliestApplicationAfter(LocalDate earliestApplicationAfter) {
		this.earliestApplicationAfter = earliestApplicationAfter;
	}

	public CourseParameters withEarliestApplicationBefore(final LocalDate earliestApplicationBefore) {
		this.earliestApplicationBefore = earliestApplicationBefore;
		return this;
	}

	public LocalDate getEarliestApplicationBefore() {
		return earliestApplicationBefore;
	}

	public void setEarliestApplicationBefore(LocalDate earliestApplicationBefore) {
		this.earliestApplicationBefore = earliestApplicationBefore;
	}

	public CourseParameters withLatestApplicationAfter(final LocalDate latestApplicationAfter) {
		this.latestApplicationAfter = latestApplicationAfter;
		return this;
	}

	public LocalDate getLatestApplicationAfter() {
		return latestApplicationAfter;
	}

	public void setLatestApplicationAfter(LocalDate latestApplicationAfter) {
		this.latestApplicationAfter = latestApplicationAfter;
	}

	public CourseParameters withLatestApplicationBefore(final LocalDate latestApplicationBefore) {
		this.latestApplicationBefore = latestApplicationBefore;
		return this;
	}

	public LocalDate getLatestApplicationBefore() {
		return latestApplicationBefore;
	}

	public void setLatestApplicationBefore(LocalDate latestApplicationBefore) {
		this.latestApplicationBefore = latestApplicationBefore;
	}

	@Override
	public String toString() {
		return "CourseParameters{" +
			"code='" + code + '\'' +
			", name='" + name + '\'' +
			", provider='" + provider + '\'' +
			", credits='" + credits + '\'' +
			", information='" + information + '\'' +
			", languageOfInstruction='" + languageOfInstruction + '\'' +
			", searchString='" + searchString + '\'' +
			", start=" + start +
			", startAfter=" + startAfter +
			", startBefore=" + startBefore +
			", end=" + end +
			", endAfter=" + endAfter +
			", endBefore=" + endBefore +
			", earliestApplication=" + earliestApplication +
			", earliestApplicationAfter=" + earliestApplicationAfter +
			", earliestApplicationBefore=" + earliestApplicationBefore +
			", latestApplication=" + latestApplication +
			", latestApplicationAfter=" + latestApplicationAfter +
			", latestApplicationBefore=" + latestApplicationBefore +
			", scopes=" + scopes +
			", studyLocations=" + studyLocations +
			", levels=" + levels +
			", categories=" + categories +
			", subcategories=" + subcategories +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		CourseParameters that = (CourseParameters) o;
		return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(provider, that.provider) && Objects.equals(credits, that.credits) && Objects.equals(information,
			that.information) && Objects.equals(languageOfInstruction, that.languageOfInstruction) && Objects.equals(searchString, that.searchString) && Objects.equals(start, that.start) && Objects.equals(startAfter,
				that.startAfter) && Objects.equals(startBefore, that.startBefore) && Objects.equals(end, that.end) && Objects.equals(endAfter, that.endAfter) && Objects.equals(endBefore, that.endBefore)
			&& Objects.equals(earliestApplication, that.earliestApplication) && Objects.equals(earliestApplicationAfter, that.earliestApplicationAfter) && Objects.equals(earliestApplicationBefore, that.earliestApplicationBefore)
			&& Objects.equals(latestApplication, that.latestApplication) && Objects.equals(latestApplicationAfter, that.latestApplicationAfter) && Objects.equals(latestApplicationBefore, that.latestApplicationBefore)
			&& Objects.equals(scopes, that.scopes) && Objects.equals(studyLocations, that.studyLocations) && Objects.equals(levels, that.levels) && Objects.equals(categories, that.categories) && Objects.equals(
				subcategories, that.subcategories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), code, name, provider, credits, information, languageOfInstruction, searchString, start, startAfter, startBefore, end, endAfter, endBefore, earliestApplication, earliestApplicationAfter,
			earliestApplicationBefore,
			latestApplication, latestApplicationAfter, latestApplicationBefore, scopes, studyLocations, levels, categories, subcategories);
	}
}
