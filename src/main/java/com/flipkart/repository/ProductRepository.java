package com.flipkart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flipkart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select p from Product p left join fetch p.images where p.productId=:productId")
	public Product getProductWithAllImages(long productId);
	
	public Page<Product> findByProductCategoryCategoryId(long categoryId, Pageable pageable);
	
	public Page<Product> findByNameContaining(String name, Pageable pageable);	

}
