package com.qualitybazar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qualitybazar.config.JwtProvider;
import com.qualitybazar.exception.UserException;
import com.qualitybazar.model.Cart;
import com.qualitybazar.model.User;
import com.qualitybazar.repository.UserRepository;
import com.qualitybazar.request.LoginRequest;
import com.qualitybazar.responce.AuthResponse;
import com.qualitybazar.service.CartService;
import com.qualitybazar.service.CostomerUserServiceImplementation;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="http://localhost:3000")
public class AuthController {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CostomerUserServiceImplementation customerUserService;
	private CartService cartService;
	
	
	public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder, CostomerUserServiceImplementation customerUserService, JwtProvider jwtProvider,CartService cartService) {
	this.userRepository=userRepository;
	this.passwordEncoder=passwordEncoder;
	this.customerUserService=customerUserService;
	this.jwtProvider=jwtProvider;
	this.cartService=cartService;
	
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user)throws UserException{


		String email=user.getEmail();
		String password=user.getPassword();
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		
		User isEmailExist=userRepository.findByEmail(email);
		
		
		if(isEmailExist!=null) {
			throw new UserException ("Email Is Already Used With Another Account");
			}
			User createdUser=new User();
			createdUser.setEmail(email);
			createdUser.setPassword(passwordEncoder.encode(password));
			createdUser.setFirstName(firstName);
			createdUser.setLastName(lastName);
			
			User savedUser=userRepository.save(createdUser);
			
			Cart cart=cartService.createCart(savedUser);
			
			Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
			SecurityContextHolder.getContext().setAuthentication (authentication);
			
			String token = jwtProvider.generateToken(authentication);
			
			AuthResponse authResponse=new AuthResponse();
			authResponse.setJwt(token);
			authResponse.setMessage("Signup Success");
			
			return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
			}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signin Success");
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
		
		
		
		}
		private Authentication authenticate(String username, String password) {
			UserDetails userDetails = customerUserService.loadUserByUsername(username);
			if(userDetails==null){
			throw new BadCredentialsException("Invalid Username...");
			}
			
			if(!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("Invalid Password...");
				}
				
				return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				}
		}
