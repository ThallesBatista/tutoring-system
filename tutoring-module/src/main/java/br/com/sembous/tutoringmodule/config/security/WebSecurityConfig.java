package br.com.sembous.tutoringmodule.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/content", "/signup", "/api/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard")
				.failureForwardUrl("/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.and()
			.csrf()
				.ignoringRequestMatchers(request -> {
					return request.getRequestURL().toString().startsWith("http://localhost:8083/");
				});
//				.and()
//			.csrf().disable(); //depois entender o que é isso e então ver isso: 
//								//https://stackoverflow.com/questions/51026694/spring-security-blocks-post-requests-despite-securityconfig
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth
			.userDetailsService(this.userDetailsService)
			.passwordEncoder(encoder);
	}
}