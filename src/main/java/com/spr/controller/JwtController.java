package com.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spr.entity.User;
import com.spr.pojo.JwtRequest;
import com.spr.pojo.JwtResponse;
import com.spr.service.impl.JwtUserDetailsService;
import com.spr.util.JwtUtil;

@RestController
@CrossOrigin		// Allow cross origin requests for this controller
public class JwtController {
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtUtil jwtUtil;
//
//	@Autowired
//	private JwtUserDetailsService userDetailsService;
//
//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
//
//		authenticate(user.getUsername(), user.getPassword());
//
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
//
//		final String token = jwtUtil.generateToken(userDetails);
//
//		return ResponseEntity.ok(new JwtResponse(token));
//	}
//
//	private void authenticate(String username, String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}

}
