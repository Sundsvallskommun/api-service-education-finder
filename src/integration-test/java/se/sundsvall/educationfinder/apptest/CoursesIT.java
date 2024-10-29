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

	private static final String PATH = "/2281/courses";

	@Test
	void test01_findAll() {
		setupCall()
			.withServicePath(PATH + "?limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_findAllEmptyResult() {
		setupCall()
			.withServicePath(PATH + "?name=does-not-exist&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test03_findAllByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?studyLocation=Sundsvall&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test04_findAllByCode() {
		setupCall()
			.withServicePath(PATH + "?code=SVASVA01")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test05_findAllByProvider() {
		setupCall()
			.withServicePath(PATH + "?provider=Härnösands folkhögskola&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test06_findAllByLevel() {
		setupCall()
			.withServicePath(PATH + "?level=yrkeshögskoleutbildning&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test07_findAllByCredits() {
		setupCall()
			.withServicePath(PATH + "?credits=325&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test08_findAllByScope() {
		setupCall()
			.withServicePath(PATH + "?scope=25&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test09_findAllByScopeAndEnd() {
		setupCall()
			.withServicePath(PATH + "?scope=50&end=2023-11-17&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test10_findAllByLevelAndStartSortByEnd() {
		setupCall()
			.withServicePath(PATH + "?level=gymnasial vuxenutbildning&start=2024-01-08&sort=end,asc&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test11_findAllByEarliestApplicationSortByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?earliestApplication=2023-05-24&sort=studyLocation&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test12_findAllByLatestApplicationSortByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?latestApplication=2023-08-27&sort=latestApplication,asc&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test13_findAllByInformation() {
		setupCall()
			.withServicePath(PATH + "?information=storkök&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test14_findAllByStartAfterAndStartBefore() {
		setupCall()
			.withServicePath(PATH + "?startAfter=2023-12-31&startBefore=2025-01-02&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test15_findAllByEndAfterAndEndBefore() {
		setupCall()
			.withServicePath(PATH + "?endAfter=2026-05-30&endBefore=2026-06-15&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test16_findAllByEarliestApplicationAfterAndEarliestApplicationBefore() {
		setupCall()
			.withServicePath(PATH + "?earliestApplicationAfter=2023-03-20&earliestApplicationBefore=2023-03-25&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test17_findAllByLatestApplicationAfterAndLatestApplicationBefore() {
		setupCall()
			.withServicePath(PATH + "?latestApplicationAfter=2023-09-05&latestApplicationBefore=2023-09-15&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test18_findAllBySearchStringMatchingCode() {
		setupCall()
			.withServicePath(PATH + "?searchString=SgrH&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test19_findAllBySearchStringMatchingName() {
		setupCall()
			.withServicePath(PATH + "?searchString=Läsa och skriva&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test20_findAllBySearchStringMatchingInformation() {
		setupCall()
			.withServicePath(PATH + "?searchString=för dig som har en intellektuell funktionsnedsättning eller förvärvad hjärnskada&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test21_findAllByStudyLocationAndSearchStringMatchingName() {
		setupCall()
			.withServicePath(PATH + "?studyLocation=Härnösand&searchString=Drifttekniker&limit=2")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test22_findAllBySubCategory() {
		setupCall()
			.withServicePath(PATH + "?subcategory=Fysik&limit=5")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

}
