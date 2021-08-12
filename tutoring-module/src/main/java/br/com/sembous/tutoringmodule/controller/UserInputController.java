package br.com.sembous.tutoringmodule.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import br.com.sembous.smconsumerapi.gateway.StudentGateway;
import br.com.sembous.smconsumerapi.gateway.StudentModuleGateway;
import br.com.sembous.smconsumerapi.model.Student;
import br.com.sembous.tutoringmodule.config.security.Role;
import br.com.sembous.tutoringmodule.config.security.RoleRepository;
import br.com.sembous.tutoringmodule.config.security.RoleValue;
import br.com.sembous.tutoringmodule.config.security.User;
import br.com.sembous.tutoringmodule.config.security.UserRepository;
import br.com.sembous.tutoringmodule.dto.SignupForm;

@Controller
public class UserInputController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping(path="/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping(path="/signup")
	public String getSignup() {
		return "signup";
	}
	
	@PostMapping(path="/signup")
	public String postSignup(@Valid SignupForm form) {
		Student student = form.convertToStudent();
		StudentGateway smg = StudentModuleGateway.getStudentGateway(new RestTemplate());
		Optional<Student> optional = smg.create(student);
		if (optional.isEmpty()) return "redirect:/signup";
		
		Optional<Role> optionalRole = roleRepository.findByName(RoleValue.ROLE_STUDENT);
		User user = form.convert(optional.get().getId(), Set.of(optionalRole.get()));
		userRepository.save(user);
		
		return "redirect:/login";
	}
}
