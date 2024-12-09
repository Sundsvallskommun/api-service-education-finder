package se.sundsvall.educationfinder.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "Statistics model", accessMode = READ_ONLY)
public class Statistics {

	@Schema(description = "Number of on-going courses", example = "10", accessMode = READ_ONLY)
	private int onGoingCourses;

	@Schema(description = "Number of planned courses", example = "10", accessMode = READ_ONLY)
	private int plannedCourses;

	@Schema(description = "Number of finished courses", example = "10", accessMode = READ_ONLY)
	private int finishedCourses;

	@Schema(description = "Number of available seats", example = "10", accessMode = READ_ONLY)
	private int availableSeats;

	@Schema(description = "Total capacity", example = "10", accessMode = READ_ONLY)
	private int totalCapacity;

	@Schema(description = "Study locations used for filtering", accessMode = READ_ONLY)
	private List<String> studyLocations = new ArrayList<>();

	@Schema(description = "Scopes used for filtering", accessMode = READ_ONLY)
	private List<Integer> scopes = new ArrayList<>();

	@Schema(description = "Levels used for filtering", accessMode = READ_ONLY)
	private List<String> levels = new ArrayList<>();

	@Schema(description = "Categories used for filtering", accessMode = READ_ONLY)
	private List<String> categories = new ArrayList<>();

	@Schema(description = "Category ids used for filtering", accessMode = READ_ONLY)
	private List<String> subCategories = new ArrayList<>();

	@Schema(description = "Start date used for filtering", accessMode = READ_ONLY)
	private LocalDate startDate;

	@Schema(description = "End date used for filtering", accessMode = READ_ONLY)
	private LocalDate endDate;

	public static Statistics create() {
		return new Statistics();
	}

	public int getOnGoingCourses() {
		return onGoingCourses;
	}

	public void setOnGoingCourses(final int onGoingCourses) {
		this.onGoingCourses = onGoingCourses;
	}

	public Statistics withOnGoingCourses(final int onGoingCourses) {
		this.onGoingCourses = onGoingCourses;
		return this;
	}

	public int getPlannedCourses() {
		return plannedCourses;
	}

	public void setPlannedCourses(final int plannedCourses) {
		this.plannedCourses = plannedCourses;
	}

	public Statistics withPlannedCourses(final int plannedCourses) {
		this.plannedCourses = plannedCourses;
		return this;
	}

	public int getFinishedCourses() {
		return finishedCourses;
	}

	public void setFinishedCourses(final int finishedCourses) {
		this.finishedCourses = finishedCourses;
	}

	public Statistics withFinishedCourses(final int finishedCourses) {
		this.finishedCourses = finishedCourses;
		return this;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(final int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Statistics withAvailableSeats(final int availableSeats) {
		this.availableSeats = availableSeats;
		return this;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(final int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Statistics withTotalCapacity(final int totalCapacity) {
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

	public List<Integer> getScopes() {
		return scopes;
	}

	public void setScopes(final List<Integer> scopes) {
		this.scopes = scopes;
	}

	public Statistics withScopes(final List<Integer> scopes) {
		this.scopes = scopes;
		return this;
	}

	public List<String> getLevels() {
		return levels;
	}

	public void setLevels(final List<String> levels) {
		this.levels = levels;
	}

	public Statistics withLevels(final List<String> levels) {
		this.levels = levels;
		return this;
	}

	public List<String> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(final List<String> subCategories) {
		this.subCategories = subCategories;
	}

	public Statistics withSubCategories(final List<String> subCategories) {
		this.subCategories = subCategories;
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
			", scopes=" + scopes +
			", levels=" + levels +
			", categories=" + categories +
			", subCategories=" + subCategories +
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
		final Statistics that = (Statistics) o;
		return onGoingCourses == that.onGoingCourses && plannedCourses == that.plannedCourses && finishedCourses == that.finishedCourses && availableSeats == that.availableSeats && totalCapacity == that.totalCapacity && Objects.equals(studyLocations,
			that.studyLocations) && Objects.equals(scopes, that.scopes) && Objects.equals(levels, that.levels) && Objects.equals(categories, that.categories) && Objects.equals(subCategories, that.subCategories) && Objects.equals(startDate, that.startDate)
			&& Objects.equals(endDate, that.endDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(onGoingCourses, plannedCourses, finishedCourses, availableSeats, totalCapacity, studyLocations, scopes, levels, categories, subCategories, startDate, endDate);
	}
}
