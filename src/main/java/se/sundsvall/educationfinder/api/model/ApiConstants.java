package se.sundsvall.educationfinder.api.model;

public final class ApiConstants {

	private ApiConstants() {}

	public static final String FIND_COURSE_DESCRIPTION = """
		Find courses and apply filters to narrow the result.
		
		Pagination parameters:
		- page: Zero-based page index (0..N)
		- size: The size of the page to be returned
		- sort: Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported.
		
		Filter parameters:
		- studyLocation: The course study location. Parameter filter strategy: 'IN'. The parameter can be provided multiple times to specify multiple values. Example usage: ?studyLocation=Sundsvall&studyLocation=Östersund
		- scope: The course scope in percent. Parameter filter strategy: 'IN'. The parameter can be provided multiple times to specify multiple values. Example usage: ?scope=100&scope=75
		- level: The course level. Parameter filter strategy: 'IN'. The parameter can be provided multiple times to specify multiple values. Example usage: ?level=gymnasial%20vuxenutbildning&level=grundläggande%20vuxenutbildning
		- code: The course code. Parameter filter strategy: 'EQUAL-IGNORE-CASE'. Example value: 'PRRPRR02'
		- name: The course name. Parameter filter strategy: 'LIKE-IGNORE-CASE'. Example value: 'Programmering'
		- provider: The course provider. Parameter filter strategy: 'LIKE-IGNORE-CASE'. Example value: 'Sundsvalls Kommun'
		- credits: The course credits. Parameter filter strategy: 'EQUAL'. Example value: '150'
		- start: The course start date. Parameter filter strategy: 'EQUAL'. Example value: '2025-01-01'
		- startBefore: A date that occurs before the course start date. Parameter filter strategy: 'LESS-THAN'. Example value: '2020-01-01'
		- startAfter: A date that occurs after the course start date. Parameter filter strategy: 'GREATER-THAN'. Example value: '2020-12-31'
		- end: The course end date. Parameter filter strategy: 'EQUAL'. Example value: '2025-01-01'
		- endBefore: A date that occurs before the course end date. Parameter filter strategy: 'LESS-THAN'. Example value: '2020-01-01'
		- endAfter: A date that occurs after the course end date. Parameter filter strategy: 'GREATER-THAN'. Example value: '2020-12-31'
		- earliestApplication: The course 'earliest application' date. Parameter filter strategy: 'EQUAL'. Example value: '2025-01-01'
		- earliestApplicationBefore: A date that occurs before the course 'earliest application' date. Parameter filter strategy: 'LESS-THAN'. Example value: '2020-01-01'
		- earliestApplicationAfter: A date that occurs after the course 'earliest application' date. Parameter filter strategy: 'GREATER-THAN'. Example value: '2020-12-31'
		- latestApplication: The course 'latest application' date. Parameter filter strategy: 'EQUAL'. Example value: '2025-01-01'
		- latestApplicationBefore: A date that occurs before the course 'latest application' date. Parameter filter strategy: 'LESS-THAN'. Example value: '2020-01-01'
		- latestApplicationAfter: A date that occurs after the course 'latest application' date. Parameter filter strategy: 'GREATER-THAN'. Example value: '2020-12-31'
		- information: The course information. Parameter filter strategy: 'LIKE-IGNORE-CASE'. Example value: 'utbildning till affärsutvecklare'
		- searchString: Search for the parameter value in code, name and information attributes. Parameter filter strategy: 'LIKE-IGNORE-CASE'. Example value: 'Programmering'
		
		Parameter filter strategies:
		- IN: Accepts a list of values separated by comma. The parameter value must have an exact match, case sensitive.
		- EQUAL: The parameter must have an exact match, case sensitive.
		- EQUAL-IGNORE-CASE: The parameter must have an exact match, case insensitive.
		- LIKE: The parameter value must occur somewhere in the searched attribute, case sensitive.
		- LIKE-IGNORE-CASE: The parameter value must occur somewhere in the searched attribute, case insensitive.
		- GREATER-THAN: The parameter value must be greater than the searched attribute.
		- LESS-THAN: The parameter value must be less than the searched attribute.
		
		Important notes:
		- All date parameters are expected to be on the format: yyyy-MM-dd (example value: '2025-01-01')
		""";

	public static final String FIND_FILTER_VALUES_DESCRITPTION = """
		Find available filter values (for use in the "Find course"-resource).
		""";
}
