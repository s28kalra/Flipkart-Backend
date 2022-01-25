package com.flipkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flipkart.dto.AddProduct;
import com.flipkart.dto.AddressDto;
import com.flipkart.dto.CountryDto;
import com.flipkart.dto.PaymentDto;
import com.flipkart.dto.ProductCategoryDto;
import com.flipkart.dto.ProductDto;
import com.flipkart.dto.Purchase;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.StateDto;
import com.flipkart.dto.TransactionDto;
import com.flipkart.entity.Address;
import com.flipkart.service.AddressService;
import com.flipkart.service.CheckoutService;
import com.flipkart.service.CountryService;
import com.flipkart.service.ProductCategoryService;
import com.flipkart.service.ProductService;
import com.flipkart.service.StateService;
import com.flipkart.service.TransactionService;
import com.google.gson.Gson;
import com.stripe.exception.StripeException;

@RestController
@CrossOrigin
public class MainController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService categoryService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private StateService stateService;
	@Autowired
	private CheckoutService checkoutService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private AddressService addressSerive;

	private Gson gson = new Gson();

	@GetMapping(value = { "/products", "/search" })
	public ResponseEntity<List<ProductDto>> getAllProducts(Pageable pageable) {
		return productService.getAllProducts(pageable);
	}

	@GetMapping("/categories")
	public ResponseEntity<List<ProductCategoryDto>> getAllCategories() {
		return categoryService.getAllcategories();
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getProductWithId(@PathVariable long productId) {
		return productService.getProductWithId(productId);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable long categoryId, Pageable pageable) {
		return productService.getProductsByCategoryId(categoryId, pageable);
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getProductsBySearching(@PathVariable String name, Pageable pageable) {
		return productService.getProductsBySearching(name, pageable);
	}

	@GetMapping("/countries")
	public ResponseEntity<List<CountryDto>> getAllCountries() {
		return countryService.getAllCountries();
	}

	@GetMapping("/country/{countryId}")
	public ResponseEntity<List<StateDto>> getStatesByCountryId(@PathVariable int countryId) {
		return stateService.getStatesByCountryId(countryId);
	}

	@GetMapping("/countryCode/{countryName}")
	public ResponseEntity<String> getCountryCode(@PathVariable String countryName) {
		return countryService.getCountryCode(countryName);
	}

	@GetMapping("/addresses/{email}")
	public ResponseEntity<List<AddressDto>> getListOfAddresses(@PathVariable String email) {
		return addressSerive.getListOfAddresses(email);
	}

	@PostMapping("/address/{email}")
	public boolean addAddress(@PathVariable String email, @RequestBody Address address) {
		return addressSerive.AddAddress(address, email);
	}

	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionDto>> getTransactionsOfACustomer(@RequestParam("email") String email,
			Pageable pageable) {
		return transactionService.getTransactionsOfACustomer(email, pageable);
	}

	@GetMapping("/transaction/{trackingNumber}")
	public ResponseEntity<TransactionDto> getTransactionDetail(@PathVariable String trackingNumber) {
		return transactionService.getTransactionDetail(trackingNumber);
	}

	@PostMapping("/checkout/purchase")
	public ResponseEntity<String> purchase(@RequestBody Purchase purchase) {
		return checkoutService.placeOrder(purchase);
	}

	@PostMapping("/checkout/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentDto paymentDto) throws StripeException {
		return checkoutService.createPaymentIntent(paymentDto);
	}

	@PostMapping("/addProduct")
	public boolean addProduct(@RequestParam("mainImage") MultipartFile mainImage,
			@RequestParam("secondaryImages") List<MultipartFile> secondaryImages,
			@RequestParam("productDetail") String productDetail) {
		AddProduct addProduct = new AddProduct();
		addProduct = gson.fromJson(productDetail, AddProduct.class);
		return productService.addProduct(addProduct, mainImage, secondaryImages);
	}

}
