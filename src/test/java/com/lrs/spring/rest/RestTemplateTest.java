package com.lrs.spring.rest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class RestTemplateTest {

	private final static String YAHOO_FINANCE_URL_PATTERN = "http://query.yahooapis.com/v1/public/yql"
			+ "?q={q}&format={format}&env={env}";

	private final static String[] CURRENCIES = { "EUR", "USD", "CNY", "JPY" };

	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	public ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setBufferRequestBody(true);
		factory.setConnectionRequestTimeout(10000);
		factory.setReadTimeout(1000);
		factory.setConnectionRequestTimeout(1000);
		return factory;
	}

	private Map<String, String> getRequestParam() {
		Map<String, String> param = new HashMap<>();
		param.put("q", "select * from yahoo.finance.xchange where pair in (\"PAIRS\")".replace("PAIRS", "USD"
				+ StringUtils.join(CURRENCIES, "\",\"USD")));
		param.put("format", "json");
		param.put("env", "store://datatables.org/alltableswithkeys");
		return param;
	}
	// 2017-07-11T02:04:41Z
	@Test
	public void httpRequestTest() {
		RestTemplate restTemplate = restTemplate();

		ResponseEntity<String> res = restTemplate.getForEntity(YAHOO_FINANCE_URL_PATTERN, String.class,
				getRequestParam());

		DocumentContext ctx = JsonPath.parse(res.getBody());
		assertThat(res.getStatusCodeValue(), is(200));

		assertThat(ctx.read("$.query.lang", String.class), equalTo("en-US"));
		assertThat(ctx.read("$.query.count", Integer.class), is(4));

	}
}
