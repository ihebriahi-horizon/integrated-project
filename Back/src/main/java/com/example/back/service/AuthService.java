package com.example.back.service;

import org.horizon.config.JwtService;
import org.horizon.entity.AuthenticationResponse;
import org.horizon.entity.Role;
import org.horizon.entity.User;
import org.horizon.payload.LoginDto;
import org.horizon.payload.SignUpDto;
import org.horizon.repository.RoleRepository;
import org.horizon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(SignUpDto request) {
		if (!userRepository.existsByUserEmail(request.getUserEmail())) {
			User user = new User();
			user.setUserFirstname(request.getUserFirstname());
			user.setUserLastname(request.getUserLastname());
			user.setUserEmail(request.getUserEmail());
			user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
			user.setUserAddress(request.getUserAddress());
			user.setUserGender(request.getUserGender());

			Role roles = roleRepository.findByRoleName("User").get();
			user.setRole(roles);
			userRepository.save(user);

			var jwtToken = jwtService.generateToken(user);
			return AuthenticationResponse.builder().token(jwtToken).status(200).message("Successfully registered")
					.build();
		} else {
			return AuthenticationResponse.builder().status(401).message("You already have an account with this email!")
					.token("").build();
		}
		// return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

	public AuthenticationResponse authenticate(LoginDto request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword()));
		var user = userRepository.findByUserEmail(request.getUserEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().status(200).message("User logged in").token(jwtToken).build();
	}
}
