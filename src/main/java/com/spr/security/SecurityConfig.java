package com.spr.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/api/**").hasRole("USER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and()
			.csrf().disable()
			.headers().frameOptions().disable();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("{noop}12345")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("{noop}123456")
			.roles("USER", "ADMIN");
	}
}