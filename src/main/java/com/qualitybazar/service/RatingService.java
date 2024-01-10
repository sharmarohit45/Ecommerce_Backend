package com.qualitybazar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.model.Rating;
import com.qualitybazar.model.User;
import com.qualitybazar.request.RatingRequest;

@Service
public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user)throws ProductException;
	
	public List<Rating>getProductsRating(Long productId);
	
	
	
	
	

}
