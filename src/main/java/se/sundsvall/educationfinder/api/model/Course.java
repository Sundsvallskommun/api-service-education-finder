package se.sundsvall.educationfinder.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Course model", accessMode = READ_ONLY)
public class Course {

	@Schema(description = "Course code", example = "PRRPRR02", accessMode = READ_ONLY)
	private String code;

	@Schema(description = "Course name", example = "Programmering 2", accessMode = READ_ONLY)
	private String name;

	@Schema(description = "Education provider", example = "Sundsvalls Kommun", accessMode = READ_ONLY)
	private String educationProvider;

	@Schema(description = "The URL to the education provider", example = "http://sundsvall.se/vuxenutbildning", accessMode = READ_ONLY)
	private String providerUrl;

	@Schema(description = "Level", example = "gymnasial vuxenutbildning", accessMode = READ_ONLY)
	private String level;

	@Schema(description = "The URL to the course", example = "https://sundsvall.alvis.se/hittakurser/kurs/38837", accessMode = READ_ONLY)
	private String url;

	@Schema(description = "Course credits", example = "150.0", accessMode = READ_ONLY)
	private Double credits;

	@Schema(description = "Course scope in percent", example = "100.0", accessMode = READ_ONLY)
	private Double scope;

	@Schema(description = "Study location", example = "Sundsvall", accessMode = READ_ONLY)
	private String studyLocation;

	@Schema(description = "Subject code", example = "PRRPRR02", accessMode = READ_ONLY)
	private String subjectCode;

	@Schema(description = "Number of seats", example = "10", accessMode = READ_ONLY)
	private Integer numberOfSeats;

	@Schema(description = "The course start date", example = "2020-08-31", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate start;

	@Schema(description = "The course end date", example = "2020-12-20", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate end;

	@Schema(description = "The earliest application date", example = "2020-01-01", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate earliestApplication;

	@Schema(description = "The latest application date", example = "2020-04-15", accessMode = READ_ONLY)
	@DateTimeFormat(iso = DATE)
	private LocalDate latestApplication;

	@Schema(description = "Recommended information",
		example = "<![CDATA[ <p><a href=https://sundsvall.se/utbildning-och-forskola/vuxenutbildning/gymnasial--niva/studieformer-och-schema target=_blank>Läs&nbsp;om våra studieformer</a></p><p><br /><a href=https://www.csn.se/ target=_blank>Läs om studiemedel på&nbsp;www.csn.se</a><br />&nbsp;<br />Ditt antagningsbesked<br />Antagningsbesked skickas via e-post cirka två veckor före kursstart.&nbsp;<a href=https://sundsvall.alvis.se/>Se ditt antagningsbesked och följ din ansökan via Mina sidor</a>.</p> ]]>",
		accessMode = READ_ONLY)
	private String recommendedInformation;

	public static Course create() {
		return new Course();
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

	public String getEducationProvider() {
		return educationProvider;
	}

	public void setEducationProvider(String educationProvider) {
		this.educationProvider = educationProvider;
	}

	public Course withEducationProvider(String educationProvider) {
		this.educationProvider = educationProvider;
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

	public String getRecommendedInformation() {
		return recommendedInformation;
	}

	public void setRecommendedInformation(String recommendedInformation) {
		this.recommendedInformation = recommendedInformation;
	}

	public Course withRecommendedInformation(String recommendedInformation) {
		this.recommendedInformation = recommendedInformation;
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
	public int hashCode() {
		return Objects.hash(code, credits, earliestApplication, educationProvider, end, latestApplication, level, name, numberOfSeats, providerUrl, recommendedInformation, scope, start, studyLocation, subjectCode, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof final Course other)) { return false; }
		return Objects.equals(code, other.code) && Objects.equals(credits, other.credits) && Objects.equals(earliestApplication, other.earliestApplication) && Objects.equals(educationProvider, other.educationProvider) && Objects.equals(end, other.end)
			&& Objects.equals(latestApplication, other.latestApplication) && Objects.equals(level, other.level) && Objects.equals(name, other.name) && Objects.equals(numberOfSeats, other.numberOfSeats) && Objects.equals(providerUrl, other.providerUrl)
			&& Objects.equals(recommendedInformation, other.recommendedInformation) && Objects.equals(scope, other.scope) && Objects.equals(start, other.start) && Objects.equals(studyLocation, other.studyLocation) && Objects.equals(subjectCode,
				other.subjectCode) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Course [code=").append(code).append(", name=").append(name).append(", educationProvider=").append(educationProvider).append(", providerUrl=").append(providerUrl).append(", level=").append(level).append(", url=").append(url).append(
			", credits=").append(credits).append(", scope=").append(scope).append(", studyLocation=").append(studyLocation).append(", subjectCode=").append(subjectCode).append(", numberOfSeats=").append(numberOfSeats).append(", start=").append(start)
			.append(", end=").append(end).append(", earliestApplication=").append(earliestApplication).append(", latestApplication=").append(latestApplication).append(", recommendedInformation=").append(recommendedInformation).append("]");
		return builder.toString();
	}
}
