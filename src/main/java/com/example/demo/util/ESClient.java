package com.example.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.example.demo.model.ESResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ESClient {
	Header[] headers = { new BasicHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()) };

	private static Logger LOG = LoggerFactory.getLogger(ESClient.class);

	@Autowired
	ObjectMapper defaultObjectMapper;
	@Autowired
	HttpClient httpClient;

	public ESResponse search(String request) throws Exception {
		return search(request, null);
	}

	public ESResponse search(String request, String elasticUrl) throws Exception {
		CloseableHttpResponse httpResponse = null;
		ESResponse esResponse = null;
		try {
			httpResponse = httpClient.createClosableHttpRequest(HttpMethod.GET, elasticUrl, request, headers);

			// Index not found or index not created in Elastic Search
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				return null;
			} else {
				String response = getStringBody(httpResponse.getEntity().getContent());
				LOG.debug("HTTP Response: " + response);
				esResponse = defaultObjectMapper.readValue(response, ESResponse.class);
				LOG.debug("ES Response: " + esResponse);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (httpResponse != null)
					httpResponse.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return esResponse;
	}

	private String getStringBody(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

		String inputLine;
		StringBuilder response = new StringBuilder();
		try {
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			throw new Exception(e);

		}
		return response.toString();

	}

}
