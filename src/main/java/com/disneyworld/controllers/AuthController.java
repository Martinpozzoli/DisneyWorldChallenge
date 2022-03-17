package com.disneyworld.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disneyworld.dto.LoginDTO;
import com.disneyworld.dto.RegistrationDTO;
import com.disneyworld.entities.Role;
import com.disneyworld.entities.User;
import com.disneyworld.repositories.RoleRepository;
import com.disneyworld.repositories.UserRepository;
import com.disneyworld.security.JwtAuthResponseDTO;
import com.disneyworld.security.JwtTokenProvider;
import com.disneyworld.services.EmailService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO){
		if(userRepo.existsByUsername(registrationDTO.getUsername())) {
			return new ResponseEntity<>("This username is already in use", HttpStatus.BAD_REQUEST);
		}
		if(userRepo.existsByEmail(registrationDTO.getEmail())) {
			return new ResponseEntity<>("There is already an account with this email", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setName(registrationDTO.getName());
		user.setUsername(registrationDTO.getUsername());
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
		
		Role role = roleRepo.findByName("ROLE_USER").get();
		user.setRoles(Collections.singleton(role));
		
		userRepo.save(user);
		
		String emailResponse = emailService.sendText(user.getEmail());
		
		return new ResponseEntity<>("User registered successfully! " + emailResponse, HttpStatus.OK);
	}
	
}
