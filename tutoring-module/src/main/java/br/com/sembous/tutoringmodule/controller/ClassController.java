package br.com.sembous.tutoringmodule.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/class")
public class ClassController {

	@GetMapping(path = "/{id}")
	public String get(@PathVariable(name = "id") Integer id, HttpSession session) {
		session.setAttribute("classId", id);
		return "class/class";
	}
}
