package io.github.nishadchayanakhawa.testcompanion.libraries.unittests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import io.github.nishadchayanakhawa.testcompanion.libraries.JenkinsProcessing;
import io.github.nishadchayanakhawa.testcompanion.libraries.exceptions.JenkinsConnectionException;

class JenkinsProcessingTests {
	@Test
	void validTest() throws IOException, URISyntaxException, ParseException, JenkinsConnectionException {
		JenkinsProcessing.testJenkinsConnection("http://103.160.144.199:8080", "nishad", "11ee84e97dbc4da1ddc4f3bdde05d3bfc8");
	}
	
	@Test
	void test404() throws IOException, URISyntaxException, ParseException {
		try {
			JenkinsProcessing.testJenkinsConnection("http://101.160.144.199:8080", "nishad", "11ee84e97dbc4da1ddc4f3bdde05d3bfc8");
		} catch (JenkinsConnectionException e) {
			Assert.isInstanceOf(JenkinsConnectionException.class, e);
			Assert.isTrue("Could not connect to url http://101.160.144.199:8080".equals(e.getMessage()),"Exception message validation");
		}
	}
	
	@Test
	void test401() throws IOException, URISyntaxException, ParseException {
		try {
			JenkinsProcessing.testJenkinsConnection("http://103.160.144.199:8080", "invalid", "invalid");
		} catch (JenkinsConnectionException e) {
			Assert.isInstanceOf(JenkinsConnectionException.class, e);
			Assert.isTrue("Jenkins Connection failed. Reason: Unauthorized".equals(e.getMessage()),"Exception message validation");
		}
	}
}
