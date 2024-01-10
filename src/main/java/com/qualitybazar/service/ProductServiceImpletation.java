package com.qualitybazar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.model.Category;
import com.qualitybazar.model.Product;
import com.qualitybazar.repository.CategoryRepository;
import com.qualitybazar.repository.ProductRepository;
import com.qualitybazar.request.CreateProductRequest;


@Service
public class ProductServiceImpletation implements ProductService{
	
	private ProductRepository productRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;
	
	public ProductServiceImpletation(ProductRepository productRepository,UserService userService,CategoryRepository categoryRepository)
	{
		this.productRepository=productRepository;
		this.userService=userService;
		this.categoryRepository=categoryRepository;
	}

	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Category topLevel=categoryRepository.findByName(req.getTopLavelCategory());
		
		if(topLevel==null) {
			Category topLevelCategory=new Category();
			topLevelCategory.setName(req.getTopLavelCategory());
			topLevelCategory.setLevel(1);
			
			topLevel=categoryRepository.save(topLevelCategory);
			
			
			
		}
		
     Category secondLevel=categoryRepository.findByNameAndParent(req.getSecondLavelCategory(),topLevel.getName());
		
		if(secondLevel==null) {
			Category secondLevelCategory=new Category();
			secondLevelCategory.setName(req.getSecondLavelCategory());
			secondLevelCategory.setParentCategory(topLevel);
			secondLevelCategory.setLevel(2);
			
			secondLevel=categoryRepository.save(secondLevelCategory);
			
			
		}
		
		 Category thirdlavel=categoryRepository.findByNameAndParent(req.getThirdLavelCategory(),secondLevel.getName());
			
			if(thirdlavel==null) {
				Category thirdLevelCategory=new Category();
				thirdLevelCategory.setName(req.getThirdLavelCategory());
				thirdLevelCategory.setParentCategory(secondLevel);
				thirdLevelCategory.setLevel(3);
				
				thirdlavel=categoryRepository.save(thirdLevelCategory);
				
				
			}
			
			Product product=new Product();
			product.setTitle(req.getTitle());
			product.setColor(req.getColor());
			product.setDescription(req.getDescription());
			product.setDiscountedPrice(req.getDiscountPrice());
			product.setDiscountedPersent(req.getDiscountPersent());
			product.setImageUrl(req.getImageUrl());
			product.setBrand(req.getBrand());
			product.setPrice(req.getPrice());
			product.setSizes(req.getSize());
			product.setQuantity(req.getQuantity());
			product.setCategory(thirdlavel);
			product.setCreatedAt(LocalDateTime.now());
			
			Product savedProduct=productRepository.save(product);
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product=findProductById(productId);
		product.getSizes().clear();
		productRepository.delete(product);
		return "Product deleted Successfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product=findProductById(productId);
		if(req.getQuantity()!=0)
		{
			product.setQuantity(req.getQuantity());
		}
		
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
	
		Optional<Product> opt=productRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		
		throw new ProductException("Product not found with id"+id);
		
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> color, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		
		Pageable pageble=PageRequest.of(pageNumber, pageSize);
		
		List<Product> products=productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		
		if(!color.isEmpty())
		{
			products=products.stream().filter(p->color.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}
		
		if(stock!=null)
		{
			if(stock.equals("in_stock"))
			{
				products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
			}
			else if(stock.equals("out_of_stock")) {
				products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
			}
			
		}
		
		int startIndex=(int)pageble.getOffset();
		int endIndex=Math.min(startIndex+pageble.getPageSize(),products.size());
		
		List<Product> pageContent=products.subList(startIndex, endIndex);
		Page<Product> fiteredProducts=new PageImpl<>(pageContent,pageble,products.size());
		
		
		return fiteredProducts;
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
