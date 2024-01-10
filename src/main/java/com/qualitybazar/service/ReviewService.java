package com.qualitybazar.service;

import java.util.List;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.model.Review;
import com.qualitybazar.model.User;
import com.qualitybazar.request.ReviewRequest;

public interface ReviewService {
	
	public Review createReview(ReviewRequest req,User user)throws ProductException;
	
	public List<Review>getAllReview(Long productId);

}
