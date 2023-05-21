package io.github.nishadchayanakhawa.testcompanion.helper;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.util.Map;
import java.util.HashMap;
public class RestApiHelper {

	private RestApiHelper() {

	}

	public static Map<String,Object> submitGetRequest(String strUrl, String username, String password)
			throws IOException {
		Map<String,Object> responseMap=new HashMap<>();
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		URL url = new URL(strUrl);
		AuthScope authScope = new AuthScope(url.getProtocol(), url.getHost(), url.getPort(), null, null);
		credentialsProvider.setCredentials(authScope,
				new UsernamePasswordCredentials(username, password.toCharArray()));

		// Combine the user and password pair into the right format
		String auth = username + ":" + password;

		// Encode the user-password pair string in Base64
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

		// Build the header String "Basic [Base64 encoded String]"
		String authHeader = "Basic " + encodedAuth;

		try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
			final HttpGet httpget = new HttpGet(strUrl);
			httpget.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			return httpclient.execute(httpget, response -> {
				responseMap.put("StatusCode", response.getCode());
				responseMap.put("Reason", response.getReasonPhrase());
				Map<String,String> headers=new HashMap<>();
				Arrays.asList(response.getHeaders()).stream()
				.forEach(header->headers.put(header.getName(), header.getValue()));
				responseMap.put("Headers", headers);
				responseMap.put("ResponseBody", EntityUtils.toString(response.getEntity()));
				return responseMap;
			});
		}
	}
}
