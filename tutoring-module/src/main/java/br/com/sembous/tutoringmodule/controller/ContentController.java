package br.com.sembous.tutoringmodule.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class ContentController {

	@GetMapping
	public String get(Principal principal) {
		//System.out.println(principal.getName());
		return "content";
	}
}
