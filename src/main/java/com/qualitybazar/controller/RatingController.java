package com.qualitybazar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.exception.UserException;
import com.qualitybazar.model.Rating;
import com.qualitybazar.model.User;
import com.qualitybazar.request.RatingRequest;
import com.qualitybazar.service.RatingService;
import com.qualitybazar.service.UserService;

@RestController
@RequestMapping()
public class RatingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
	
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Rating rating=ratingService.createRating(req, user);
		
		return new ResponseEntity<Rating>(rating,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>>getProductRating(@PathVariable Long productId,
			@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		List<Rating> ratings=ratingService.getProductsRating(productId);
		
		return new ResponseEntity<>(ratings,HttpStatus.CREATED);
		
	}

}
