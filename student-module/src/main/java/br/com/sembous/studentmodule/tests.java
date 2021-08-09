package br.com.sembous.studentmodule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tests {
	
	public static void main(String[] args) {
		List<Map<Integer, String>> list1 = new ArrayList<>();
		Map<Integer, String> m1 = new HashMap<>();
		m1.put(Integer.valueOf(1), "um original"); m1.put(Integer.valueOf(2), "dois original"); list1.add(m1);
		
		List<Map<Integer, String>> unmodifiableList = Collections.unmodifiableList(list1);
		Map<Integer, String> m2 = unmodifiableList.get(0);
		
		m1.put(1, "um modificado"); m1.put(2, "dois modificado");
		
		
		
		System.out.println(m2.get(1)); System.out.println(m2.get(2));
	}
}
