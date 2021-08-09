package br.com.sembous.expertmodule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class quickTests {

	public static void test(ObjectMapper mapper) throws JsonMappingException, JsonProcessingException {
		
		String test = "{ "
				+ "\"key\":\"value\", "
				+ "\"key2\":\"value2\", "
				+ "\"exercício\":{"
					+ "\"key\":\"value\""
					+ "}"
				+ "}";
		System.out.println(test);
		System.out.println();
		JsonNode json = mapper.readTree(test);
		System.out.println(json.toPrettyString());
		
		
	}

}
