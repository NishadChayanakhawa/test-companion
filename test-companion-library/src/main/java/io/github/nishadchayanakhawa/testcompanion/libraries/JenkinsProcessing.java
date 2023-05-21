package io.github.nishadchayanakhawa.testcompanion.libraries;

import java.io.IOException;
import java.util.Map;

import org.apache.hc.client5.http.HttpHostConnectException;

import io.github.nishadchayanakhawa.testcompanion.helper.RestApiHelper;
import io.github.nishadchayanakhawa.testcompanion.libraries.exceptions.JenkinsConnectionException;

public class JenkinsProcessing {
	
	private JenkinsProcessing() {
		
	}
	
	public static void testJenkinsConnection(String url, String username,String password) throws IOException, JenkinsConnectionException {
		try {
			Map<String,Object> response=RestApiHelper.submitGetRequest(url, username, password);
			if((int)response.get("StatusCode")!=200) {
				String errorMessage=String.format("Jenkins Connection failed. Reason: %s", response.get("Reason"));
				throw new JenkinsConnectionException(errorMessage);
			}
		}catch(HttpHostConnectException e) {
			throw (JenkinsConnectionException)new JenkinsConnectionException("Could not connect to url " + url).initCause(e);
		}
	}
}
