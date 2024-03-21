package se.sundsvall.educationfinder.apptest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;

import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.educationfinder.Application;

@WireMockAppTestSuite(files = "classpath:/CoursesFiltersIT/", classes = Application.class)
class CoursesFiltersIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	@Test
	void test01_findCreditsFilterValues() {
		setupCall()
			.withServicePath("/courses/filters/credits/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_findProviderFilterValues() {
		setupCall()
			.withServicePath("/courses/filters/provider/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test03_findLevelFilterValues() {
		setupCall()
			.withServicePath("/courses/filters/level/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test04_findScopeFilterValues() {
		setupCall()
			.withServicePath("/courses/filters/scope/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test05_findStudyLocationFilterValues() {
		setupCall()
			.withServicePath("/courses/filters/studyLocation/values")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}
}
