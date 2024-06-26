package se.sundsvall.educationfinder.integration.db.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "api_vuxenutb", name = "vAmnessok")
public class SubjectEntity {

	@Id
	@Column(name = "AmnessokID")
	private Long id;

	@Column(name = "AmnesId")
	private Integer subjectId;

	@Column(name = "Amne")
	private String subject;

	@Column(name = "AmneEng")
	private String subjectEnglish;

	@Column(name = "Amneskyp")
	private String educationForm;

	@Column(name = "Amneskod")
	private String subjectCode;

	@Column(name = "OmradeId")
	private Integer categoryId;

	@Column(name = "Omrade")
	private String category;

	@Column(name = "OmradeEng")
	private String categoryEnglish;

	@Column(name = "InriktningId")
	private Integer branchId;

	@Column(name = "Inriktning")
	private String branch;

	@Column(name = "InriktningEng")
	private String branchEnglish;

	public static SubjectEntity create() {
		return new SubjectEntity();
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public SubjectEntity withId(final Long id) {
		this.id = id;
		return this;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(final Integer subjectId) {
		this.subjectId = subjectId;
	}

	public SubjectEntity withSubjectId(final Integer subjectId) {
		this.subjectId = subjectId;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public SubjectEntity withSubject(final String subject) {
		this.subject = subject;
		return this;
	}

	public String getSubjectEnglish() {
		return subjectEnglish;
	}

	public void setSubjectEnglish(final String subjectEnglish) {
		this.subjectEnglish = subjectEnglish;
	}

	public SubjectEntity withSubjectEnglish(final String subjectEnglish) {
		this.subjectEnglish = subjectEnglish;
		return this;
	}

	public String getEducationForm() {
		return educationForm;
	}

	public void setEducationForm(final String educationForm) {
		this.educationForm = educationForm;
	}

	public SubjectEntity withEducationForm(final String educationForm) {
		this.educationForm = educationForm;
		return this;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
	}

	public SubjectEntity withCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public SubjectEntity withCategory(final String category) {
		this.category = category;
		return this;
	}

	public String getCategoryEnglish() {
		return categoryEnglish;
	}

	public void setCategoryEnglish(final String categoryEnglish) {
		this.categoryEnglish = categoryEnglish;
	}

	public SubjectEntity withCategoryEnglish(final String categoryEnglish) {
		this.categoryEnglish = categoryEnglish;
		return this;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(final Integer branchId) {
		this.branchId = branchId;
	}

	public SubjectEntity withBranchId(final Integer branchId) {
		this.branchId = branchId;
		return this;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(final String branch) {
		this.branch = branch;
	}

	public SubjectEntity withBranch(final String branch) {
		this.branch = branch;
		return this;
	}

	public String getBranchEnglish() {
		return branchEnglish;
	}

	public void setBranchEnglish(final String branchEnglish) {
		this.branchEnglish = branchEnglish;
	}

	public SubjectEntity withBranchEnglish(final String branchEnglish) {
		this.branchEnglish = branchEnglish;
		return this;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(final String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public SubjectEntity withSubjectCode(final String subjectCode) {
		this.subjectCode = subjectCode;
		return this;
	}

	@Override
	public String toString() {
		return "SubjectEntity{" +
			"id=" + id +
			", subjectId=" + subjectId +
			", subject='" + subject + '\'' +
			", subjectEnglish='" + subjectEnglish + '\'' +
			", educationForm='" + educationForm + '\'' +
			", subjectCode='" + subjectCode + '\'' +
			", categoryId=" + categoryId +
			", category='" + category + '\'' +
			", categoryEnglish='" + categoryEnglish + '\'' +
			", branchId=" + branchId +
			", branch='" + branch + '\'' +
			", branchEnglish='" + branchEnglish + '\'' +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final SubjectEntity that = (SubjectEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(subjectId, that.subjectId) && Objects.equals(subject, that.subject) && Objects.equals(subjectEnglish, that.subjectEnglish) && Objects.equals(educationForm, that.educationForm) && Objects.equals(subjectCode, that.subjectCode) && Objects.equals(categoryId, that.categoryId) && Objects.equals(category, that.category) && Objects.equals(categoryEnglish, that.categoryEnglish) && Objects.equals(branchId, that.branchId) && Objects.equals(branch, that.branch) && Objects.equals(branchEnglish, that.branchEnglish);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, subjectId, subject, subjectEnglish, educationForm, subjectCode, categoryId, category, categoryEnglish, branchId, branch, branchEnglish);
	}
}
