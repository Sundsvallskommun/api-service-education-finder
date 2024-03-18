package se.sundsvall.educationfinder.integration.db.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "api_vuxenutb", name = "KurserVuxenutbildning")
public class CourseEntity {

	@Id
	@Column(name = "kurstillfälle_id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "Kurskod", insertable = false, updatable = false)
	private String code;

	@Column(name = "Namn", insertable = false, updatable = false)
	private String name;

	@Column(name = "Utbildningsanordnare", insertable = false, updatable = false)
	private String educationProvider;

	@Column(name = "UrlAnordnare", insertable = false, updatable = false)
	private String providerUrl;

	@Column(name = "Niva", insertable = false, updatable = false)
	private String level;

	@Column(name = "UrlKurs", insertable = false, updatable = false)
	private String url;

	@Column(name = "Poang", insertable = false, updatable = false)
	private BigDecimal credits;

	@Column(name = "Omfattning", insertable = false, updatable = false)
	private BigDecimal scope;

	@Column(name = "Studieort", insertable = false, updatable = false)
	private String studyLocation;

	@Column(name = "Amneskod", insertable = false, updatable = false)
	private String subjectCode;

	@Column(name = "AntalPlatser", insertable = false, updatable = false)
	private Integer numberOfSeats;

	@Column(name = "KurstillfalleStartdatum", insertable = false, updatable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate start;

	@Column(name = "KurstillfalleSlutdatum", insertable = false, updatable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate end;

	@Column(name = "ForstaAnsokningsdag", insertable = false, updatable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate earliestApplication;

	@Column(name = "SistaAnsokningsdag", insertable = false, updatable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate latestApplication;

	@Column(name = "RekommenderadInformation", insertable = false, updatable = false)
	private String recommendedInformation;

	public static CourseEntity create() {
		return new CourseEntity();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CourseEntity withId(Long id) {
		this.id = id;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CourseEntity withCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CourseEntity withName(String name) {
		this.name = name;
		return this;
	}

	public String getEducationProvider() {
		return educationProvider;
	}

	public void setEducationProvider(String educationProvider) {
		this.educationProvider = educationProvider;
	}

	public CourseEntity withEducationProvider(String educationProvider) {
		this.educationProvider = educationProvider;
		return this;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public CourseEntity withProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
		return this;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public CourseEntity withLevel(String level) {
		this.level = level;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CourseEntity withUrl(String url) {
		this.url = url;
		return this;
	}

	public BigDecimal getCredits() {
		return credits;
	}

	public void setCredits(BigDecimal credits) {
		this.credits = credits;
	}

	public CourseEntity withCredits(BigDecimal credits) {
		this.credits = credits;
		return this;
	}

	public BigDecimal getScope() {
		return scope;
	}

	public void setScope(BigDecimal scope) {
		this.scope = scope;
	}

	public CourseEntity withScope(BigDecimal scope) {
		this.scope = scope;
		return this;
	}

	public String getStudyLocation() {
		return studyLocation;
	}

	public void setStudyLocation(String studyLocation) {
		this.studyLocation = studyLocation;
	}

	public CourseEntity withStudyLocation(String studyLocation) {
		this.studyLocation = studyLocation;
		return this;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public CourseEntity withSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
		return this;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public CourseEntity withNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
		return this;
	}

	public String getRecommendedInformation() {
		return recommendedInformation;
	}

	public void setRecommendedInformation(String recommendedInformation) {
		this.recommendedInformation = recommendedInformation;
	}

	public CourseEntity withRecommendedInformation(String recommendedInformation) {
		this.recommendedInformation = recommendedInformation;
		return this;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public CourseEntity withStart(LocalDate start) {
		this.start = start;
		return this;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public CourseEntity withEnd(LocalDate end) {
		this.end = end;
		return this;
	}

	public LocalDate getEarliestApplication() {
		return earliestApplication;
	}

	public void setEarliestApplication(LocalDate earliestApplication) {
		this.earliestApplication = earliestApplication;
	}

	public CourseEntity withEarliestApplication(LocalDate earliestApplication) {
		this.earliestApplication = earliestApplication;
		return this;
	}

	public LocalDate getLatestApplication() {
		return latestApplication;
	}

	public void setLatestApplication(LocalDate latestApplication) {
		this.latestApplication = latestApplication;
	}

	public CourseEntity withLatestApplication(LocalDate latestApplication) {
		this.latestApplication = latestApplication;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, credits, earliestApplication, educationProvider, end, id, latestApplication, level, name, numberOfSeats, providerUrl, recommendedInformation, scope, start, studyLocation, subjectCode, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof final CourseEntity other)) { return false; }
		return Objects.equals(code, other.code) && Objects.equals(credits, other.credits) && Objects.equals(earliestApplication, other.earliestApplication) && Objects.equals(educationProvider, other.educationProvider) && Objects.equals(end, other.end)
			&& Objects.equals(id, other.id) && Objects.equals(latestApplication, other.latestApplication) && Objects.equals(level, other.level) && Objects.equals(name, other.name) && Objects.equals(numberOfSeats, other.numberOfSeats) && Objects.equals(
				providerUrl, other.providerUrl) && Objects.equals(recommendedInformation, other.recommendedInformation) && Objects.equals(scope, other.scope) && Objects.equals(start, other.start) && Objects.equals(studyLocation, other.studyLocation)
			&& Objects.equals(subjectCode, other.subjectCode) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("CourseEntity [id=").append(id).append(", code=").append(code).append(", name=").append(name).append(", educationProvider=").append(educationProvider).append(", providerUrl=").append(providerUrl).append(", level=").append(level)
			.append(", url=").append(url).append(", credits=").append(credits).append(", scope=").append(scope).append(", studyLocation=").append(studyLocation).append(", subjectCode=").append(subjectCode).append(", numberOfSeats=").append(numberOfSeats)
			.append(", start=").append(start).append(", end=").append(end).append(", earliestApplication=").append(earliestApplication).append(", latestApplication=").append(latestApplication).append(", recommendedInformation=").append(
				recommendedInformation).append("]");
		return builder.toString();
	}
}
