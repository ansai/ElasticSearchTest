package com.example.demo.util;

import java.net.URI;

import org.apache.commons.codec.net.URLCodec;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
//import com.fasterxml.jackson.databind.exc.InvalidDefinitionException

@Component
public class HttpClient {
	int timeout = 30;

	public CloseableHttpResponse createClosableHttpRequest(HttpMethod method, String url, String param,
			Header[] headers) throws Exception {
		HttpUriRequest request = null;
		URI uri;
		HttpEntity entity = null;
		CloseableHttpResponse httpResponse = null;
		try {
			URLCodec codec = new URLCodec();
			StringBuffer strURL = new StringBuffer().append(url).append("?pretty=true&q=").append(codec.encode(param));
			uri = new URI(strURL.toString());
			if (param != null) {
				entity = new StringEntity(param, "UTF-8");
			}

			request = getHttpUriRequest(method, uri, entity);
			if (request != null && headers != null) {
				request.setHeaders(headers);
			}

			RequestConfig config;

			config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
					.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

			CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setDefaultRequestConfig(config)
					.build();
			httpResponse = closeableHttpClient.execute(request);
		} catch (Exception e) {
			throw e;
		}
		return httpResponse;

	}

	private HttpUriRequest getHttpUriRequest(HttpMethod method, URI uri, HttpEntity entity) {
		HttpUriRequest request = null;
		if (method.equals(HttpMethod.GET)) {
			HttpGet get = new HttpGet();
			get.setURI(uri);
			request = get;
		} else if (method.equals(HttpMethod.POST)) {
			HttpPost post = new HttpPost();
			post.setURI(uri);
			if (entity != null)
				post.setEntity(entity);
			request = post;
		} else if (method.equals(HttpMethod.PUT)) {
			HttpPut put = new HttpPut();
			put.setURI(uri);
			if (entity != null)
				put.setEntity(entity);
			request = put;
		} else if (method.equals(HttpMethod.DELETE)) {
			HttpDelete delete = new HttpDelete(uri);
			request = delete;
		}

		return request;
	}

}
