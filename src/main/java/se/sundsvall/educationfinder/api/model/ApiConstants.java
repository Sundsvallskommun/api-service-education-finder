package se.sundsvall.educationfinder.api.model;

public final class ApiConstants {

	private ApiConstants() {}

	public static final String FIND_COURSE_DESCRIPTION = """
		Find courses and apply filters to narrow the result.

		Pagination parameters:
		- page: One-based page index (1..N)
		- limit: The size of the page to be returned
		- sortBy: Accepts a list of values separated by comma. The result is firstly sorted by the first values, followed by the next value.
		- sortDirection: The sort direction. Possible values: 'ASC' or 'DESC'. Case sensitive.
		- Example usage: ?page=1&limit=10&sortBy=credits,category&sortDirection=ASC
		- Example explanation: The example will return the first page with 10 courses sorted by credits in ascending order, followed by category in ascending order.

		Filter parameters:
		- studyLocations: The course study location. Parameter filter strategy: 'IN-IGNORE-CASE'. The parameter can be provided multiple times to specify multiple values. Example usage: ?studyLocations=Sundsvall&studyLocations=Östersund
		- scopes: The course scope in percent. Parameter filter strategy: 'IN-IGNORE-CASE'. The parameter can be provided multiple times to specify multiple values. Example usage: ?scopes=100&scopes=75
		- levels: The course level. Parameter filter strategy: 'IN-IGNORE-CASE'. The parameter can be provided multiple times to specify multiple values. Example usage: ?levels=gymnasial%20vuxenutbildning&levels=grundläggande%20vuxenutbildning
		- categories: The course category. Parameter filter strategy: 'IN-IGNORE-CASE'. The parameter can be provided multiple times to specify multiple values. Example usage: ?categories=Teknik&categories=Bygg%20och%20anläggning
		- code: The course code. Parameter filter strategy: 'EQUAL-IGNORE-CASE'. Example value: 'PRRPRR02'
		- name: The course name. Parameter filter strategy: 'EQUAL-IGNORE-CASE'. Example value: 'Programmering'
		- provider: The course provider. Parameter filter strategy: 'EQUAL-IGNORE-CASE'. Example value: 'Sundsvalls Kommun'
		- credits: The course credits. Parameter filter strategy: 'EQUAL'. Example value: '150'
		- start: The course start date. Parameter filter strategy: 'EQUAL'. Example value: '2025-01-01'
		- startBefore: A date that occurs before the course start date. Parameter filter strategy: 'EQUAL-OR-LESS-THAN'. Example value: '2020-01-01'
		- startAfter: A date that occurs after the course start date. Parameter filter strategy: 'EQUAL-OR-GREATER-THAN'. Example value: '2020-12-31'
		- end: The course end date. Parameter filter strategy: 'EQUAL'. Example value: '2025-01-01'
		- endBefore: A date that occurs before the course end date. Parameter filter strategy: 'EQUAL-OR-LESS-THAN'. Example value: '2020-01-01'
		- endAfter: A date that occurs after the course end date. Parameter filter strategy: 'EQUAL-OR-GREATER-THAN'. Example value: '2020-12-31'
		- earliestApplication: The course 'earliest application' date. Parameter filter strategy: 'EQUAL'. Example value: '2025-01-01'
		- earliestApplicationBefore: A date that occurs before the course 'earliest application' date. Parameter filter strategy: 'EQUAL-OR-LESS-THAN'. Example value: '2020-01-01'
		- earliestApplicationAfter: A date that occurs after the course 'earliest application' date. Parameter filter strategy: 'EQUAL-OR-GREATER-THAN'. Example value: '2020-12-31'
		- latestApplication: The course 'latest application' date. Parameter filter strategy: 'EQUAL-OR-NULL'. Example value: '2025-01-01'
		- latestApplicationBefore: A date that occurs before the course 'latest application' date. Parameter filter strategy: 'EQUAL-OR-LESS-THAN-OR-NULL'. Example value: '2020-01-01'
		- latestApplicationAfter: A date that occurs after the course 'latest application' date. Parameter filter strategy: 'EQUAL-OR-GREATER-THAN-OR-NULL'. Example value: '2020-12-31'
		- information: The course information. Parameter filter strategy: 'LIKE-IGNORE-CASE'. Example value: 'utbildning till affärsutvecklare'
		- searchString: Search for the parameter value in code, name, category and information attributes. Parameter filter strategy: 'LIKE-IGNORE-CASE'. Example value: 'Programmering'

		Parameter filter strategies:
		- IN-IGNORE-CASE: Accepts a list of values separated by comma. The parameter value must have an exact match, case insensitive.
		- EQUAL: The parameter must have an exact match, case sensitive.
		- EQUAL-OR-NULL: The parameter must have an exact match, case sensitive or be null.
		- EQUAL-IGNORE-CASE: The parameter must have an exact match, case insensitive.
		- LIKE-IGNORE-CASE: The parameter value must occur somewhere in the searched attribute, case insensitive.
		- EQUAL-OR-GREATER-THAN: The parameter value must be equal or greater than the searched attribute.
		- EQUAL-OR-GREATER-THAN-OR-NULL: The parameter value must be equal or greater than the searched attribute or be null.
		- EQUAL-OR-LESS-THAN: The parameter value must be equal or less than the searched attribute.
		- EQUAL-OR-LESS-THAN-OR-NULL: The parameter value must be equal or less than the searched attribute or be null.


		Important notes:
		- All date parameters are expected to be on the format: yyyy-MM-dd (example value: '2025-01-01')
		""";

	public static final String GET_STATISTICS_DESCRIPTION = """
		Find statistics based on the provided parameters.

		Filter parameters:
		- categories: Which categories to include in the statistics. Parameter filter strategy: IN-IGNORE-CASE. The parameter can be provided multiple times to specify multiple values. Example usage: ?categories=Teknik&categories=Bygg%20och%20anläggning
		- subcategories: Which subcategories to include in the statistics. Parameter filter strategy: IN-IGNORE-CASE. The parameter can be provided multiple times to specify multiple values. Example usage: ?subcategories=fysik&subcategories=matematik
		- levels: Which levels to include in the statistics. Parameter filter strategy: IN-IGNORE-CASE. The parameter can be provided multiple times to specify multiple values. Example usage: ?levels=gymnasial%20vuxenutbildning&levels=grundläggande%20vuxenutbildning
		- studyLocations: Which study locations to include in the statistics. Parameter filter strategy: IN-IGNORE-CASE. The parameter can be provided multiple times to specify multiple values. Example usage: ?studyLocations=Sundsvall&studyLocations=Östersund
		- scopes: Which scopes to include in the statistics. Parameter filter strategy: IN-IGNORE-CASE. The parameter can be provided multiple times to specify multiple values. Example usage: ?scopes=100&scopes=75
		- startDate: The start date of the statistics. 'startDate' is used in parallel with 'endDate' to form a period. Parameter filter strategy: WITHIN-OR-EQUAL. Example usage: ?startDate=2025-01-01
		- endDate: The end date of the statistics. 'endDate' is used in parallel with 'startDate' to form a period. Parameter filter strategy: WITHIN-OR-EQUAL. Example usage: ?endDate=2025-12-31

		Parameter filter strategies:
		- IN-IGNORE-CASE: Accepts a list of values separated by comma. The parameter value must have an exact match, case insensitive.
		- WITHIN-OR-EQUAL: The parameter value must be within the range of the searched attribute or equal to it. Example - startDate=2025-01-01, endDate=2025-12-31: The statistics will include all courses that have a start date between(inclusive) 2025-01-01 and 2025-12-31.

		Returned statistics:
		- OnGoingCourses: Number of courses which pass the filter parameters and have a start date before the given 'startDate' and after the given 'endDate'.
		- PlannedCourses: Number of courses which pass the filter parameters and have a start date after the given 'startDate' and before the given 'endDate'.
		- FinishedCourses: Number of courses which pass the filter parameters and have an end date after the given 'startDate' and before the given 'endDate'.
		- AvailableSeats: Number of available seats for all courses which pass the filter parameters and have a start date after the given 'startDate' and before the given 'endDate'.
		- TotalCapacity: Total capacity for all courses which pass the filter parameters and have a start date after the given 'startDate' and before the given 'endDate'.

		Important notes:
		- All date parameters are expected to be on the format: yyyy-MM-dd (example value: '2025-01-01')
		""";

}
