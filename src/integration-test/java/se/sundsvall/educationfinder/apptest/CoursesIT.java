package se.sundsvall.educationfinder.apptest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;

import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.educationfinder.Application;

@WireMockAppTestSuite(files = "classpath:/CoursesIT/", classes = Application.class)
class CoursesIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	@Test
	void test01_findAll() {
		setupCall()
			.withServicePath("/courses")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_findAllEmptyResult() {
		setupCall()
			.withServicePath("/courses?name=does-not-exist")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test03_findAllByStudyLocation() {
		setupCall()
			.withServicePath("/courses?studyLocation=härnö")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test04_findAllByCode() {
		setupCall()
			.withServicePath("/courses?code=JURAFF0")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test05_findAllByProvider() {
		setupCall()
			.withServicePath("/courses?provider=härnösand")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test06_findAllByLevel() {
		setupCall()
			.withServicePath("/courses?level=yrkeshögskole")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test07_findAllByCredits() {
		setupCall()
			.withServicePath("/courses?credits=325")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test08_findAllByScope() {
		setupCall()
			.withServicePath("/courses?scope=25")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test09_findAllByScopeAndEnd() {
		setupCall()
			.withServicePath("/courses?scope=50&end=2023-11-17")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test10_findAllByLevelAndStartSortByEnd() {
		setupCall()
			.withServicePath("/courses?level=yrkeshögskoleutbildning&start=2024-09-01&sort=end,asc")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test11_findAllByEarliestApplicationSortByStudyLocation() {
		setupCall()
			.withServicePath("/courses?earliestApplication=2023-05-24&sort=studyLocation")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test12_findAllByLatestApplicationSortByStudyLocation() {
		setupCall()
			.withServicePath("/courses?latestApplication=2023-08-27&sort=latestApplication,asc")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test13_findAllByRecommendedInformation() {
		setupCall()
			.withServicePath("/courses?recommendedInformation=storkök")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test14_findAllByStartAfterAndStartBefore() {
		setupCall()
			.withServicePath("/courses?startAfter=2024-12-31&startBefore=2025-01-02")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test15_findAllByEndAfterAndEndBefore() {
		setupCall()
			.withServicePath("/courses?endAfter=2026-05-30&endBefore=2026-06-15")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test16_findAllByEarliestApplicationAfterAndEarliestApplicationBefore() {
		setupCall()
			.withServicePath("/courses?earliestApplicationAfter=2023-03-20&earliestApplicationBefore=2023-03-25")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test17_findAllByLatestApplicationAfterAndLatestApplicationBefore() {
		setupCall()
			.withServicePath("/courses?latestApplicationAfter=2023-09-05&latestApplicationBefore=2023-09-15")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}
}
