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
			.withServicePath(PATH)
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_findAllEmptyResult() {
		setupCall()
			.withServicePath(PATH + "?name=does-not-exist")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test03_findAllByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?studyLocation=Härnösand")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test04_findAllByCode() {
		setupCall()
			.withServicePath(PATH + "?code=JURAFF0")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test05_findAllByProvider() {
		setupCall()
			.withServicePath(PATH + "?provider=härnösand")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test06_findAllByLevel() {
		setupCall()
			.withServicePath(PATH + "?level=yrkeshögskoleutbildning")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test07_findAllByCredits() {
		setupCall()
			.withServicePath(PATH + "?credits=325")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test08_findAllByScope() {
		setupCall()
			.withServicePath(PATH + "?scope=25")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test09_findAllByScopeAndEnd() {
		setupCall()
			.withServicePath(PATH + "?scope=50&end=2023-11-17")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test10_findAllByLevelAndStartSortByEnd() {
		setupCall()
			.withServicePath(PATH + "?level=yrkeshögskoleutbildning&start=2024-09-01&sort=end,asc")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test11_findAllByEarliestApplicationSortByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?earliestApplication=2023-05-24&sort=studyLocation")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test12_findAllByLatestApplicationSortByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?latestApplication=2023-08-27&sort=latestApplication,asc")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test13_findAllByInformation() {
		setupCall()
			.withServicePath(PATH + "?information=storkök")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test14_findAllByStartAfterAndStartBefore() {
		setupCall()
			.withServicePath(PATH + "?startAfter=2024-12-31&startBefore=2025-01-02")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test15_findAllByEndAfterAndEndBefore() {
		setupCall()
			.withServicePath(PATH + "?endAfter=2026-05-30&endBefore=2026-06-15")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test16_findAllByEarliestApplicationAfterAndEarliestApplicationBefore() {
		setupCall()
			.withServicePath(PATH + "?earliestApplicationAfter=2023-03-20&earliestApplicationBefore=2023-03-25")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test17_findAllByLatestApplicationAfterAndLatestApplicationBefore() {
		setupCall()
			.withServicePath(PATH + "?latestApplicationAfter=2023-09-05&latestApplicationBefore=2023-09-15")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test18_findAllBySearchStringMatchingCode() {
		setupCall()
			.withServicePath(PATH + "?searchString=SgrH")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test19_findAllBySearchStringMatchingName() {
		setupCall()
			.withServicePath(PATH + "?searchString=Läsa och skriva")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test20_findAllBySearchStringMatchingInformation() {
		setupCall()
			.withServicePath(PATH + "?searchString=för dig som har en intellektuell funktionsnedsättning eller förvärvad hjärnskada")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test21_findAllByStudyLocationAndSearchStringMatchingName() {
		setupCall()
			.withServicePath(PATH + "?studyLocation=Härnösand&searchString=Drifttekniker")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

}
