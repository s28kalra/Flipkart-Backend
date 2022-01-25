package com.flipkart.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.flipkart.dto.AddProduct;
import com.flipkart.dto.ImageDto;
import com.flipkart.dto.PageInfo;
import com.flipkart.dto.ProductDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.Status;
import com.flipkart.entity.Image;
import com.flipkart.entity.Product;
import com.flipkart.entity.ProductCategory;
import com.flipkart.repository.ProductRepository;
import com.flipkart.service.GoogleDriveService;
import com.flipkart.service.ProductService;
import com.flipkart.utils.FlipkartUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private GoogleDriveService googleDriveService;

	private ResponseEntity<List<ProductDto>> getResponseOfProducts(Page<Product> page) {
		ResponseEntity<List<ProductDto>> response = new ResponseEntity<List<ProductDto>>();
		List<ProductDto> list = new ArrayList<ProductDto>();
		if (page.hasContent()) {
			List<Product> products = page.getContent();
			response.setStatus(Status.SUCCESS);
			for (Product product : products) {
				ProductDto dto = new ProductDto();
				BeanUtils.copyProperties(product, dto);
				list.add(dto);
			}
			response.setEntity(list);
			response.setPageInfo(FlipkartUtils.getPageInfoFromPage(page));

		} else {
			response.setMessage("Products not Fetched");
		}
		return response;

	}

	private ResponseEntity<ProductDto> getResponseOfPoduct(Product product) {
		ResponseEntity<ProductDto> response = new ResponseEntity<ProductDto>();
		if (product != null && product.getProductId() > 0) {
			response.setStatus(Status.SUCCESS);
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(product, dto);
			if (!CollectionUtils.isEmpty(product.getImages())) {
				List<ImageDto> list = new ArrayList<ImageDto>();
				for (Image image : product.getImages()) {
					ImageDto imageDto = new ImageDto();
					BeanUtils.copyProperties(image, imageDto);
					list.add(imageDto);
				}
				dto.setImages(list);
			}
			response.setEntity(dto);
		} else {
			response.setMessage("Product Not Found");
		}
		return response;
	}

	@Override
	public ResponseEntity<List<ProductDto>> getAllProducts(Pageable pageable) {
		return getResponseOfProducts(productRepository.findAll(pageable));
	}

	@Override
	public ResponseEntity<ProductDto> getProductWithId(long productId) {
		return getResponseOfPoduct(productRepository.getProductWithAllImages(productId));
	}

	@Override
	public ResponseEntity<List<ProductDto>> getProductsByCategoryId(long categoryId, Pageable pageable) {
		return getResponseOfProducts(productRepository.findByProductCategoryCategoryId(categoryId, pageable));

	}

	@Override
	public ResponseEntity<List<ProductDto>> getProductsBySearching(String name, Pageable pageable) {
		ResponseEntity<List<ProductDto>> response = getResponseOfProducts(
				productRepository.findByNameContaining(name, pageable));
		if (!Status.SUCCESS.equals(response.getStatus()))
			response.setMessage("No Product found with that particular keyword -- Search again--");
		return response;
	}

	@Override
	@Transactional
	public boolean addProduct(AddProduct addProduct, MultipartFile mainImage, List<MultipartFile> secondaryImages) {
		String imageBaseUrl = "https://drive.google.com/uc?export=view&id=";
		Product product = addProduct.getProduct();
		ProductCategory category = addProduct.getCategory();
		category.add(product);

		try {

			// upload main image
			String mainImageId = googleDriveService.uploadMainPhoto(FlipkartUtils.getFileFromMultipartFile(mainImage));

			// add main image url to product
			product.setImageUrl(imageBaseUrl + mainImageId);

			// create secondary folder
			String secondaryFolderPath = googleDriveService.createSecondaryFolder(product.getName());

			// upload secondary Photos
			for (MultipartFile file : secondaryImages) {
				File image = FlipkartUtils.getFileFromMultipartFile(file);
				String id = googleDriveService.uploadSecondaryPhoto(image, secondaryFolderPath);
				Image img = new Image();
				img.setImageUrl(imageBaseUrl + id);
				product.addImage(img);
			}

			// save product and images
			product = productRepository.save(product);

			return product.getProductId() > 0 ? true : false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
