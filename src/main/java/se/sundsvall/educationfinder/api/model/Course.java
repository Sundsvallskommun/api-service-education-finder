package se.sundsvall.educationfinder.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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

	@Schema(description = "Course credits", example = "100", accessMode = READ_ONLY)
	private String credits;

	@Schema(description = "Course scope in percent", example = "100", accessMode = READ_ONLY)
	private String scope;

	@Schema(description = "Study location", example = "Sundsvall", accessMode = READ_ONLY)
	private String studyLocation;

	@Schema(description = "Subject code", example = "PRRPRR02", accessMode = READ_ONLY)
	private String subjectCode;

	@Schema(description = "Number of seats", example = "10", accessMode = READ_ONLY)
	private Integer numberOfSeats;

	@Schema(description = "The course start date", example = "2020-08-31", accessMode = READ_ONLY)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;

	@Schema(description = "The course end date", example = "2020-12-20", accessMode = READ_ONLY)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate endDate;

	@Schema(description = "The course first application date", example = "2020-01-01", accessMode = READ_ONLY)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate firstApplicationDate;

	@Schema(description = "The course last application date", example = "2020-04-15", accessMode = READ_ONLY)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate lastApplicationDate;

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

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public Course withCredits(String credits) {
		this.credits = credits;
		return this;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Course withScope(String scope) {
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Course withStartDate(LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Course withEndDate(LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}

	public LocalDate getFirstApplicationDate() {
		return firstApplicationDate;
	}

	public void setFirstApplicationDate(LocalDate firstApplicationDate) {
		this.firstApplicationDate = firstApplicationDate;
	}

	public Course withFirstApplicationDate(LocalDate firstApplicationDate) {
		this.firstApplicationDate = firstApplicationDate;
		return this;
	}

	public LocalDate getLastApplicationDate() {
		return lastApplicationDate;
	}

	public void setLastApplicationDate(LocalDate lastApplicationDate) {
		this.lastApplicationDate = lastApplicationDate;
	}

	public Course withLastApplicationDate(LocalDate lastApplicationDate) {
		this.lastApplicationDate = lastApplicationDate;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, credits, educationProvider, endDate, firstApplicationDate, lastApplicationDate, level, name, numberOfSeats, providerUrl, recommendedInformation, scope, startDate, studyLocation, subjectCode, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof final Course other)) { return false; }
		return Objects.equals(code, other.code) && Objects.equals(credits, other.credits) && Objects.equals(educationProvider, other.educationProvider) && Objects.equals(endDate, other.endDate) && Objects.equals(firstApplicationDate,
			other.firstApplicationDate) && Objects.equals(lastApplicationDate, other.lastApplicationDate) && Objects.equals(level, other.level) && Objects.equals(name, other.name) && Objects.equals(numberOfSeats, other.numberOfSeats) && Objects.equals(
				providerUrl, other.providerUrl) && Objects.equals(recommendedInformation, other.recommendedInformation) && Objects.equals(scope, other.scope) && Objects.equals(startDate, other.startDate) && Objects.equals(studyLocation,
					other.studyLocation) && Objects.equals(subjectCode, other.subjectCode) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Course [code=").append(code).append(", name=").append(name).append(", educationProvider=").append(educationProvider).append(", providerUrl=").append(providerUrl).append(", level=").append(level).append(", url=").append(url).append(
			", credits=").append(credits).append(", scope=").append(scope).append(", studyLocation=").append(studyLocation).append(", subjectCode=").append(subjectCode).append(", numberOfSeats=").append(numberOfSeats).append(", recommendedInformation=")
			.append(recommendedInformation).append(", startDate=").append(startDate).append(", endDate=").append(endDate).append(", firstApplicationDate=").append(firstApplicationDate).append(", lastApplicationDate=").append(lastApplicationDate).append(
				"]");
		return builder.toString();
	}
}
