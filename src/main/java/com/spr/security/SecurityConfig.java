package com.spr.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spr.util.Constants;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/api/**").hasRole(Constants.ROLE_USER)
			.antMatchers("/admin/**").hasRole(Constants.ROLE_ADMIN)
			.and()
			.csrf().disable()
			.headers().frameOptions().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   auth.jdbcAuthentication()
	   	  .passwordEncoder(passwordEncoder())
	      .dataSource(dataSource)
	      .authoritiesByUsernameQuery("select username,authority from authorities where username = ?")
	      .usersByUsernameQuery("select username,password,enabled from users where username = ?");
	}
	
	/*
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);	
		
		 // By default spring security assumes `users` table for storing users 
		 // and `authorities` table for storing roles
	}
	*/
	
	// This is in memory authentication
	// {noop} in {noop}12345 represents NoOpPasswordEncoder meaning password will be considered in plain text
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("$2a$10$MIIMAMxYUfXcfJyQaGtq/.6fXC4u0a3hdXHViLfvVkl2vpYyoUcE6")
			.roles("USER", "ADMIN")
			.and()
			.withUser("admin")
			.password("$2a$10$MIIMAMxYUfXcfJyQaGtq/.6fXC4u0a3hdXHViLfvVkl2vpYyoUcE6")
			.roles("ADMIN");
	}
	*/
	
	/*
	 * Spring security will automatically use this for decoding password while authentication
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}

/*
 * There is bug in spring security when checking password against bcrypt
 * The password should not start with $2b or $2y
 */