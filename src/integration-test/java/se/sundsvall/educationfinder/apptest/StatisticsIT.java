package se.sundsvall.educationfinder.apptest;

import org.junit.jupiter.api.Test;
import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.educationfinder.Application;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

@WireMockAppTestSuite(files = "classpath:/StatisticsIT/", classes = Application.class)
class StatisticsIT extends AbstractAppTest {

	private static final String RESPONSE_FILE = "response.json";

	private static final String PATH = "/2281/statistics";

	@Test
	void test01_getStatisticsByCategory() {
		setupCall()
			.withServicePath(PATH + "?categories=FÖRBEREDANDE UTBILDNINGAR&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test02_getStatisticsBySubCategory() {
		setupCall()
			.withServicePath(PATH + "?subCategories=HÄLSO- OCH SJUKVÅRDADMINISTRATION&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test03_getStatisticsByScope() {
		setupCall()
			.withServicePath(PATH + "?scopes=75&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test04_getStatisticsByLevel() {
		setupCall()
			.withServicePath(PATH + "?levels=GYMNASIAL VUXENUTBILDNING&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

	@Test
	void test05_getStatisticsByStudyLocation() {
		setupCall()
			.withServicePath(PATH + "?studyLocations=ÖRNSKÖLDSVIK&startDate=2023-01-01&endDate=2023-12-30&limit=3")
			.withHttpMethod(GET)
			.withExpectedResponseStatus(OK)
			.withExpectedResponse(RESPONSE_FILE)
			.sendRequestAndVerifyResponse();
	}

}
