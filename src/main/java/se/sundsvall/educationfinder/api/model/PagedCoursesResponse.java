package se.sundsvall.educationfinder.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Objects;
import se.sundsvall.dept44.models.api.paging.PagingMetaData;

@Schema(description = "Paged course response model", accessMode = READ_ONLY)
public class PagedCoursesResponse {

	@ArraySchema(schema = @Schema(implementation = Course.class, accessMode = READ_ONLY))
	private List<Course> courses;

	@JsonProperty("_meta")
	@Schema(implementation = PagingMetaData.class, accessMode = READ_ONLY)
	private PagingMetaData metadata;

	public static PagedCoursesResponse create() {
		return new PagedCoursesResponse();
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public PagedCoursesResponse withCourses(List<Course> courses) {
		this.courses = courses;
		return this;
	}

	public PagingMetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(PagingMetaData metadata) {
		this.metadata = metadata;
	}

	public PagedCoursesResponse withMetaData(PagingMetaData metadata) {
		this.metadata = metadata;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courses, metadata);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof final PagedCoursesResponse other)) { return false; }
		return Objects.equals(courses, other.courses) && Objects.equals(metadata, other.metadata);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("PagedCoursesResponse [courses=").append(courses).append(", metadata=").append(metadata).append("]");
		return builder.toString();
	}
}
