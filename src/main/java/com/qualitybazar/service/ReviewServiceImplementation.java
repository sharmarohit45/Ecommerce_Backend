package com.qualitybazar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.model.Product;
import com.qualitybazar.model.Review;
import com.qualitybazar.model.User;
import com.qualitybazar.repository.ProductRepository;
import com.qualitybazar.repository.ReviewRepository;
import com.qualitybazar.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	

	public ReviewServiceImplementation(ReviewRepository reviewRepository,ProductService productService,ProductRepository productRepository) {
		
		this.reviewRepository=reviewRepository;
		this.productService=productService;
		this.productRepository=productRepository;
		
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		
		Product product=productService.findProductById(req.getProductId());
		
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
	
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReview(productId);
	}

}
