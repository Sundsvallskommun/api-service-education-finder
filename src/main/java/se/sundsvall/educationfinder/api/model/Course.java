package se.sundsvall.educationfinder.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "Course model", accessMode = READ_ONLY)
public class Course {

	@Schema(description = "Course ID", example = "1", accessMode = READ_ONLY)
	private Long id;

	@Schema(description = "Course code", example = "PRRPRR02", accessMode = READ_ONLY)
	private String code;

	@Schema(description = "Course name", example = "Programmering 2", accessMode = READ_ONLY)
	private String name;

	@Schema(description = "Course provider", example = "Sundsvalls Kommun", accessMode = READ_ONLY)
	private String provider;

	@Schema(description = "Course provider URL", example = "http://sundsvall.se/vuxenutbildning", accessMode = READ_ONLY)
	private String providerUrl;

	@Schema(description = "Course level", example = "gymnasial vuxenutbildning", accessMode = READ_ONLY)
	private String level;

	@Schema(description = "Course URL", example = "https://sundsvall.alvis.se/hittakurser/kurs/38837", accessMode = READ_ONLY)
	private String url;

	@Schema(description = "Course credits", example = "150", accessMode = READ_ONLY)
	private Double credits;

	@Schema(description = "Course scope in percent", example = "100", accessMode = READ_ONLY)
	private Double scope;

	@Schema(description = "Study location", example = "Sundsvall", accessMode = READ_ONLY)
	private String studyLocation;

	@Schema(description = "Subject code", example = "PRRPRR02", accessMode = READ_ONLY)
	private String subjectCode;

	@Schema(description = "Number of seats", example = "10", accessMode = READ_ONLY)
	private Integer numberOfSeats;

	@Schema(description = "Course category", example = "Ekonomi, marknadsföring och administration", accessMode = READ_ONLY)
	private String category;

	@Schema(description = "Course subcategory", example = "Administration", accessMode = READ_ONLY)
	private String subcategory;

	@Schema(description = "Language of instruction", example = "Swedish", accessMode = READ_ONLY)
	private String languageOfInstruction;

	@Schema(description = "Course start date", example = "2020-08-31", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate start;

	@Schema(description = "Course end date", example = "2020-12-20", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate end;

	@Schema(description = "Earliest application date", example = "2020-01-01", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate earliestApplication;

	@Schema(description = "Latest application date", example = "2020-04-15", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate latestApplication;

	@Schema(description = "Course information",
		example = "<![CDATA[ <p><a href=https://sundsvall.se/utbildning-och-forskola/vuxenutbildning/gymnasial--niva/studieformer-och-schema target=_blank>Läs&nbsp;om våra studieformer</a></p><p><br /><a href=https://www.csn.se/ target=_blank>Läs om studiemedel på&nbsp;www.csn.se</a><br />&nbsp;<br />Ditt antagningsbesked<br />Antagningsbesked skickas via e-post cirka två veckor före kursstart.&nbsp;<a href=https://sundsvall.alvis.se/>Se ditt antagningsbesked och följ din ansökan via Mina sidor</a>.</p> ]]>",
		accessMode = READ_ONLY)
	private String information;

	@Schema(description = "Study location municipality ID", example = "2301", accessMode = READ_ONLY)
	private String studyLocationMunicipalityId;

	@Schema(description = "Visiting address municipality ID", example = "2301", accessMode = READ_ONLY)
	private String visitingAddressMunicipalityId;

	public static Course create() {
		return new Course();
	}

	public Course withStudyLocationMunicipalityId(String studyLocationMunicipalityId) {
		this.studyLocationMunicipalityId = studyLocationMunicipalityId;
		return this;
	}

	public String getStudyLocationMunicipalityId() {
		return studyLocationMunicipalityId;
	}

	public void setStudyLocationMunicipalityId(String studyLocationMunicipalityId) {
		this.studyLocationMunicipalityId = studyLocationMunicipalityId;
	}

	public Course withVisitingAddressMunicipalityId(String visitingAddressMunicipalityId) {
		this.visitingAddressMunicipalityId = visitingAddressMunicipalityId;
		return this;
	}

	public String getVisitingAddressMunicipalityId() {
		return visitingAddressMunicipalityId;
	}

	public void setVisitingAddressMunicipalityId(String visitingAddressMunicipalityId) {
		this.visitingAddressMunicipalityId = visitingAddressMunicipalityId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course withId(Long id) {
		this.id = id;
		return this;
	}

	public String getLanguageOfInstruction() {
		return languageOfInstruction;
	}

	public void setLanguageOfInstruction(String languageOfInstruction) {
		this.languageOfInstruction = languageOfInstruction;
	}

	public Course withLanguageOfInstruction(String languageOfInstruction) {
		this.languageOfInstruction = languageOfInstruction;
		return this;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Course withSubcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Course withCode(String code) {
		this.code = code;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Course withCategory(String category) {
		this.category = category;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course withName(String name) {
		this.name = name;
		return this;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Course withProvider(String provider) {
		this.provider = provider;
		return this;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public Course withProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
		return this;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Course withLevel(String level) {
		this.level = level;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Course withUrl(String url) {
		this.url = url;
		return this;
	}

	public Double getCredits() {
		return credits;
	}

	public void setCredits(Double credits) {
		this.credits = credits;
	}

	public Course withCredits(Double credits) {
		this.credits = credits;
		return this;
	}

	public Double getScope() {
		return scope;
	}

	public void setScope(Double scope) {
		this.scope = scope;
	}

	public Course withScope(Double scope) {
		this.scope = scope;
		return this;
	}

	public String getStudyLocation() {
		return studyLocation;
	}

	public void setStudyLocation(String studyLocation) {
		this.studyLocation = studyLocation;
	}

	public Course withStudyLocation(String studyLocation) {
		this.studyLocation = studyLocation;
		return this;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Course withSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
		return this;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Course withNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
		return this;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Course withInformation(String information) {
		this.information = information;
		return this;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public Course withStart(LocalDate start) {
		this.start = start;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public Course withEnd(LocalDate end) {
		this.end = end;
		return this;
	}

	public LocalDate getEarliestApplication() {
		return earliestApplication;
	}

	public void setEarliestApplication(LocalDate earliestApplication) {
		this.earliestApplication = earliestApplication;
	}

	public Course withEarliestApplication(LocalDate earliestApplication) {
		this.earliestApplication = earliestApplication;
		return this;
	}

	public LocalDate getLatestApplication() {
		return latestApplication;
	}

	public void setLatestApplication(LocalDate latestApplication) {
		this.latestApplication = latestApplication;
	}

	public Course withLatestApplication(LocalDate latestApplication) {
		this.latestApplication = latestApplication;
		return this;
	}

	@Override
	public String toString() {
		return "Course{" +
			"id=" + id +
			", code='" + code + '\'' +
			", name='" + name + '\'' +
			", provider='" + provider + '\'' +
			", providerUrl='" + providerUrl + '\'' +
			", level='" + level + '\'' +
			", url='" + url + '\'' +
			", credits=" + credits +
			", scope=" + scope +
			", studyLocation='" + studyLocation + '\'' +
			", subjectCode='" + subjectCode + '\'' +
			", numberOfSeats=" + numberOfSeats +
			", category='" + category + '\'' +
			", subcategory='" + subcategory + '\'' +
			", languageOfInstruction='" + languageOfInstruction + '\'' +
			", start=" + start +
			", end=" + end +
			", earliestApplication=" + earliestApplication +
			", latestApplication=" + latestApplication +
			", information='" + information + '\'' +
			", studyLocationMunicipalityId='" + studyLocationMunicipalityId + '\'' +
			", visitingAddressMunicipalityId='" + visitingAddressMunicipalityId + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Course course = (Course) o;
		return Objects.equals(id, course.id) && Objects.equals(code, course.code) && Objects.equals(name, course.name) && Objects.equals(provider, course.provider) && Objects.equals(providerUrl,
			course.providerUrl) && Objects.equals(level, course.level) && Objects.equals(url, course.url) && Objects.equals(credits, course.credits) && Objects.equals(scope, course.scope) && Objects.equals(
				studyLocation, course.studyLocation) && Objects.equals(subjectCode, course.subjectCode) && Objects.equals(numberOfSeats, course.numberOfSeats) && Objects.equals(category, course.category) && Objects.equals(
					subcategory, course.subcategory) && Objects.equals(languageOfInstruction, course.languageOfInstruction) && Objects.equals(start, course.start) && Objects.equals(end, course.end) && Objects.equals(
						earliestApplication, course.earliestApplication) && Objects.equals(latestApplication, course.latestApplication) && Objects.equals(information, course.information) && Objects.equals(studyLocationMunicipalityId,
							course.studyLocationMunicipalityId) && Objects.equals(visitingAddressMunicipalityId, course.visitingAddressMunicipalityId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, name, provider, providerUrl, level, url, credits, scope, studyLocation, subjectCode, numberOfSeats, category, subcategory, languageOfInstruction, start, end, earliestApplication, latestApplication, information,
			studyLocationMunicipalityId, visitingAddressMunicipalityId);
	}
}
