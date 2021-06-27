package com.vidhan.oktatokenservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vidhan.oktatokenservice.model.OktaToken;
import com.vidhan.oktatokenservice.service.OktaTokenService;

@RestController
@RequestMapping("/api/v1/")
public class TokenController {
	
	@Autowired
	private OktaTokenService tokenService;
	
	@GetMapping("/helloToken")
	public String helloWorld() {
		return "Hello From Token Service::";
	}
	
	@PostMapping("/getToken/{authToken}")
	public ResponseEntity<OktaToken> getToken(@PathVariable("authToken") final String authToken ) {
		OktaToken token = tokenService.getToken(authToken);
		if (token == null) {
			return ResponseEntity.badRequest().build();
		} else return ResponseEntity.ok().body(token);
	}
}
