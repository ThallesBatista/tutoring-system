package br.com.sembous.tutoringmodule.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserInformationsController {

	@GetMapping("/isLogged")
	public Boolean isLogged(Principal principal) {
		return (principal!=null);
	}
}
