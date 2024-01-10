package com.qualitybazar.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.model.Product;
import com.qualitybazar.request.CreateProductRequest;

public interface ProductService {
	
	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long productId)throws ProductException;
	
	public Product updateProduct(Long productId,Product req)throws ProductException;
	
	public Product findProductById(Long id)throws ProductException;
	
	public List<Product>findProductByCategory(String category);
	
	public Page<Product>getAllProduct(String category,List<String>color,List<String>sizes,Integer minPrice,Integer maxPrice,
			Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pageSize);

	public List<Product> findAllProduct();

}
