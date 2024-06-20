package se.sundsvall.educationfinder.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistics model", accessMode = READ_ONLY)
public class Statistics {

	@Schema(description = "Number of on-going courses", example = "10", accessMode = READ_ONLY)
	private Integer onGoingCourses;

	@Schema(description = "Number of planned courses", example = "10", accessMode = READ_ONLY)
	private Integer plannedCourses;

	@Schema(description = "Number of finished courses", example = "10", accessMode = READ_ONLY)
	private Integer finishedCourses;

	@Schema(description = "Number of available seats", example = "10", accessMode = READ_ONLY)
	private Integer availableSeats;

	@Schema(description = "Total capacity", example = "10", accessMode = READ_ONLY)
	private Integer totalCapacity;

	@Schema(description = "Study locations used for filtering", accessMode = READ_ONLY)
	private List<String> studyLocations;

	@Schema(description = "Education forms used for filtering", accessMode = READ_ONLY)
	private List<String> educationForms;

	@Schema(description = "Categories used for filtering", accessMode = READ_ONLY)
	private List<String> categories;

	@Schema(description = "Start date used for filtering", accessMode = READ_ONLY)
	private LocalDate startDate;

	@Schema(description = "End date used for filtering", accessMode = READ_ONLY)
	private LocalDate endDate;

	public static Statistics create() {
		return new Statistics();
	}

	public Integer getOnGoingCourses() {
		return onGoingCourses;
	}

	public void setOnGoingCourses(final Integer onGoingCourses) {
		this.onGoingCourses = onGoingCourses;
	}

	public Statistics withOnGoingCourses(final Integer onGoingCourses) {
		this.onGoingCourses = onGoingCourses;
		return this;
	}

	public Integer getPlannedCourses() {
		return plannedCourses;
	}

	public void setPlannedCourses(final Integer plannedCourses) {
		this.plannedCourses = plannedCourses;
	}

	public Statistics withPlannedCourses(final Integer plannedCourses) {
		this.plannedCourses = plannedCourses;
		return this;
	}

	public Integer getFinishedCourses() {
		return finishedCourses;
	}

	public void setFinishedCourses(final Integer finishedCourses) {
		this.finishedCourses = finishedCourses;
	}

	public Statistics withFinishedCourses(final Integer finishedCourses) {
		this.finishedCourses = finishedCourses;
		return this;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(final Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Statistics withAvailableSeats(final Integer availableSeats) {
		this.availableSeats = availableSeats;
		return this;
	}

	public Integer getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(final Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Statistics withTotalCapacity(final Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
		return this;
	}

	public List<String> getStudyLocations() {
		return studyLocations;
	}

	public void setStudyLocations(final List<String> studyLocations) {
		this.studyLocations = studyLocations;
	}

	public Statistics withStudyLocations(final List<String> studyLocations) {
		this.studyLocations = studyLocations;
		return this;
	}

	public List<String> getEducationForms() {
		return educationForms;
	}

	public void setEducationForms(final List<String> educationForms) {
		this.educationForms = educationForms;
	}

	public Statistics withEducationForms(final List<String> educationForms) {
		this.educationForms = educationForms;
		return this;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(final List<String> categories) {
		this.categories = categories;
	}

	public Statistics withCategories(final List<String> categories) {
		this.categories = categories;
		return this;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}

	public Statistics withStartDate(final LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}

	public Statistics withEndDate(final LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}

	@Override
	public String toString() {
		return "Statistics{" +
			"onGoingCourses=" + onGoingCourses +
			", plannedCourses=" + plannedCourses +
			", finishedCourses=" + finishedCourses +
			", availableSeats=" + availableSeats +
			", totalCapacity=" + totalCapacity +
			", studyLocations=" + studyLocations +
			", educationForms=" + educationForms +
			", categories=" + categories +
			", startDate=" + startDate +
			", endDate=" + endDate +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Statistics that = (Statistics) o;
		return Objects.equals(onGoingCourses, that.onGoingCourses) && Objects.equals(plannedCourses, that.plannedCourses) && Objects.equals(finishedCourses, that.finishedCourses) && Objects.equals(availableSeats, that.availableSeats) && Objects.equals(totalCapacity, that.totalCapacity) && Objects.equals(studyLocations, that.studyLocations) && Objects.equals(educationForms, that.educationForms) && Objects.equals(categories, that.categories) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(onGoingCourses, plannedCourses, finishedCourses, availableSeats, totalCapacity, studyLocations, educationForms, categories, startDate, endDate);
	}
}
