package se.sundsvall.educationfinder.apptest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;

import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.educationfinder.Application;

@WireMockAppTestSuite(files = "classpath:/StatisticsIT/", classes = Application.class)
class StatisticsIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	private static final String PATH = "/2281/statistics";

	@Test
	void test01_findLevelValues() {
		setupCall()
			.withServicePath(PATH + "/filters/level/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_findScopeValues() {
		setupCall()
			.withServicePath(PATH + "/filters/scope/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test03_findCategoryValues() {
		setupCall()
			.withServicePath(PATH + "/filters/category/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test04_findStudyLocationValues() {
		setupCall()
			.withServicePath(PATH + "/filters/study_location/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test05_findStartDateValues() {
		setupCall()
			.withServicePath(PATH + "/filters/start_date/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test06_findEndDateValues() {
		setupCall()
			.withServicePath(PATH + "/filters/end_date/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test07_findSubCategoryValues() {
		setupCall()
			.withServicePath(PATH + "/filters/subcategory/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test08_getStatisticsByCategory() {
		setupCall()
			.withServicePath(PATH + "?categories=FÖRBEREDANDE UTBILDNINGAR&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test09_getStatisticsBySubCategory() {
		setupCall()
			.withServicePath(PATH + "?subCategories=HÄLSO- OCH SJUKVÅRDADMINISTRATION&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test10_getStatisticsByScope() {
		setupCall()
			.withServicePath(PATH + "?scopes=75.0&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test11_getStatisticsByLevel() {
		setupCall()
			.withServicePath(PATH + "?levels=GYMNASIAL VUXENUTBILDNING&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test12_getStatisticsByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?studyLocations=ÖRNSKÖLDSVIK&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

}
