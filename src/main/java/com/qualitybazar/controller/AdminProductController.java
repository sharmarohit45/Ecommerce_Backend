package com.qualitybazar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.model.Product;
import com.qualitybazar.request.CreateProductRequest;
import com.qualitybazar.responce.ApiResponse;
import com.qualitybazar.service.ProductService;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req)
	{
		Product product=productService.createProduct(req);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId)throws ProductException{
		
		productService.deleteProduct(productId);
		ApiResponse res=new ApiResponse();
		res.setMessage("product delete successfully");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProduct()
	{
		List<Product> product=productService.findAllProduct();
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product req,@PathVariable Long productId)throws ProductException{
		
		Product product=productService.updateProduct(productId, req);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req)
	{
		for(CreateProductRequest product:req)
		{
			productService.createProduct(product);
		}
		ApiResponse res=new ApiResponse();
		res.setMessage("product created Successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

}
