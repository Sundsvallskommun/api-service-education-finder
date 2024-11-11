package se.sundsvall.educationfinder.apptest;

import org.junit.jupiter.api.Test;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.educationfinder.Application;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

@WireMockAppTestSuite(files = "classpath:/StatisticsFiltersIT/", classes = Application.class)
public class StatisticsFiltersIT extends AbstractAppTest {

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
			.withServicePath(PATH + "/filters/studyLocation/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test05_findStartDateValues() {
		setupCall()
			.withServicePath(PATH + "/filters/startDate/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test06_findEndDateValues() {
		setupCall()
			.withServicePath(PATH + "/filters/endDate/values")
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
}
