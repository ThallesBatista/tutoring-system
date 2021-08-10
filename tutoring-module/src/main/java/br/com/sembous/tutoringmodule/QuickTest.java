package br.com.sembous.tutoringmodule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuickTest {
	
	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
//		ClassLoader classLoader = QuickTest.class.getClass().getClassLoader();
        InputStream fis = QuickTest.class.getResourceAsStream("/static/noMoreActivitiesToDo.json");
		
//		InputStream fis = new FileInputStream("static/noMoreActivitiesToDo.json");
		Reader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String jsonString = "";
		String line = br.readLine();
		while (line != null && !line.isEmpty()) {
			jsonString += line;
			line = br.readLine();
		}
		br.close();
		
		JsonNode json = mapper.readTree(jsonString);
		System.out.println(json.toPrettyString());
	}
}
