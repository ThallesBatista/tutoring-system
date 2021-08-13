package br.com.sembous.tutoringmodule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.dto.newClassForm;
import br.com.sembous.tutoringmodule.service.TeacherService;

@Controller
@RequestMapping(path = "/newClass")
public class NewClassController {

	@Autowired
	TeacherService teacherService;
	
	@GetMapping
	public String getForm() {
		return "newClassForm";
	}
	
	@PostMapping
	public String post(@Valid newClassForm form) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		teacherService.createClass(form, user.getForeignId());
		return "redirect:dashboard";
	}
}
