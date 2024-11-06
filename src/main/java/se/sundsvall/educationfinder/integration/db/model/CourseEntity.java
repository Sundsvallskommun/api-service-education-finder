package se.sundsvall.educationfinder.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema = "api_vuxenutb", name = "KurserVuxenutbildning")
public class CourseEntity {

	@Id
	@Column(name = "KurserVuxenutbildningId", insertable = false, updatable = false)
	private Long id;

	@Column(name = "Kurskod", insertable = false, updatable = false)
	private String code;

	@Column(name = "Namn", insertable = false, updatable = false)
	private String name;

	@Column(name = "Utbildningsanordnare", insertable = false, updatable = false)
	private String provider;

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
	private String information;

	@Column(name = "Intresseomrade_kategori")
	private String category;

	@Column(name = "Undervisningssprak")
	private String languageOfInstruction;

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

	public String getLanguageOfInstruction() {
		return languageOfInstruction;
	}

	public void setLanguageOfInstruction(String languageOfInstruction) {
		this.languageOfInstruction = languageOfInstruction;
	}

	public CourseEntity withLanguageOfInstruction(String languageOfInstruction) {
		this.languageOfInstruction = languageOfInstruction;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public CourseEntity withCategory(String category) {
		this.category = category;
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

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public CourseEntity withProvider(String provider) {
		this.provider = provider;
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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public CourseEntity withInformation(String information) {
		this.information = information;
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
	public String toString() {
		return "CourseEntity{" +
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
			", start=" + start +
			", end=" + end +
			", earliestApplication=" + earliestApplication +
			", latestApplication=" + latestApplication +
			", information='" + information + '\'' +
			", category='" + category + '\'' +
			", languageOfInstruction='" + languageOfInstruction + '\'' +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final CourseEntity that = (CourseEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(provider, that.provider) && Objects.equals(providerUrl, that.providerUrl) && Objects.equals(level, that.level) && Objects
			.equals(url, that.url) && Objects.equals(credits, that.credits) && Objects.equals(scope, that.scope) && Objects.equals(studyLocation, that.studyLocation) && Objects.equals(subjectCode, that.subjectCode) && Objects.equals(numberOfSeats,
				that.numberOfSeats) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(earliestApplication, that.earliestApplication) && Objects.equals(latestApplication, that.latestApplication) && Objects.equals(
					information, that.information) && Objects.equals(category, that.category) && Objects.equals(languageOfInstruction, that.languageOfInstruction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, name, provider, providerUrl, level, url, credits, scope, studyLocation, subjectCode, numberOfSeats, start, end, earliestApplication, latestApplication, information, category, languageOfInstruction);
	}
}
