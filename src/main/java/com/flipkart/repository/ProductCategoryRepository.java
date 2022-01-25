package com.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flipkart.entity.ProductCategory;

//@RepositoryRestResource
//@RepositoryRestResource(collectionResourceRel = "xyz", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{
	
	@Query("SELECT c from ProductCategory c left join fetch c.listOfProducts where c.categoryId=:categoryId")
	public ProductCategory findCategoriesWithProducts(long categoryId);
	
	 
	

}
