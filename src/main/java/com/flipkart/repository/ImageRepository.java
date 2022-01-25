package com.flipkart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flipkart.entity.Image;
import com.flipkart.entity.Product;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	
	List<Image> findByProductProductId(long productId);

}
