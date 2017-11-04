package com.lrs.common.jackson;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class JsonSerializerTest {

	@Test
	public void testSerializer() {
		ObjectMapper mapper = new ObjectMapper();

		Model model = new Model();
		model.setDate(new Date());

		model.setDecimal(new BigDecimal(".2"));
		model.setMap(Collections.singletonMap("key1", "value1"));

		model.setList(Arrays.asList("list1", "list2", "list3", "list4"));

		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);

			System.out.println(json);

			assertThat(json, isJson());
			assertThat(json, hasJsonPath("$.date_now"));

			assertThat(json, hasNoJsonPath("$.message"));
			assertThat(json, hasJsonPath("$.decimal", equalTo("0.20")));

			assertThat(json, isJson(withJsonPath("$.map.key1", any(String.class))));

			assertThat(json, isJson(withJsonPath("$.list[3]", equalTo("list4"))));

			assertThat(json, isJson(withoutJsonPath("$.list[4]")));

			assertThat(json, hasJsonPath("$.name", nullValue()));

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
