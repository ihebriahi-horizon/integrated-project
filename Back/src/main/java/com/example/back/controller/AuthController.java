
package com.example.back.controller;

import com.example.back.entity.AuthenticationResponse;
import com.example.back.payload.LoginDto;
import com.example.back.payload.SignUpDto;
import com.example.back.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private AuthService authService;
	
	@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody SignUpDto request){
		AuthenticationResponse response = authService.register(request);
		if(response.getStatus()!=200) {
			return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.UNAUTHORIZED);
		}
		else {
			return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
		}
    }
	
	@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginDto request){
		return ResponseEntity.ok(authService.authenticate(request));
    }
    
}