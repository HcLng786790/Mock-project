package com.mockproject.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.ProductDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.model.Category;
import com.mockproject.demo.model.Product;
import com.mockproject.demo.repository.CategoryRepository;
import com.mockproject.demo.repository.ProductRepository;
import com.mockproject.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseRestController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewProduct(@RequestParam(name="Category_id",required = false) long id,
			@RequestBody(required = false) Map<String, Object> newProduct ){
		try {
			if(ObjectUtils.isEmpty(id)||ObjectUtils.isEmpty(newProduct)||ObjectUtils.isEmpty(newProduct.get("name"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			Category foundCategory = this.categoryRepository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(foundCategory)) {
				return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
			}
			Product product = this.productService.addNewProduct(id, newProduct, foundCategory);
			return super.sucess(new ProductDTOResponse(product));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
	@GetMapping("/getByCategory")
	public ResponseEntity<?> getByCategory(@RequestParam(required = false,name="category_id") Long id,
			Pageable pageable){
		try {
			if(ObjectUtils.isEmpty(id)) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			Category category = this.categoryRepository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(category)) {
				return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
			}
			List<Product> products = this.productService.getByCategory(category,pageable).stream().map(x->x).toList();
			List<ProductDTOResponse> response = products.stream().map(ProductDTOResponse::new).toList();
			return super.sucess(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());

	}
	
	@GetMapping("/getById")
	public ResponseEntity<?> getById(@RequestParam(required = false,name="product_id",defaultValue = "1") long id){
		try {
			if(ObjectUtils.isEmpty(id)) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			Product foundProduct = this.productRepository.findById(id).orElse(null);
			if(!ObjectUtils.isEmpty(foundProduct)) {
				return super.sucess(new ProductDTOResponse(foundProduct));
			}
			return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id,@RequestBody(required = false) Map<String, Object> newProduct){
		try {
			if(ObjectUtils.isEmpty(id)||ObjectUtils.isEmpty(newProduct)||ObjectUtils.isEmpty(newProduct.get("name"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			Product foundProduct = this.productRepository.findById(id).orElse(null);
			if(!ObjectUtils.isEmpty(foundProduct)) {
				foundProduct=this.productService.updateProduct(id, newProduct, foundProduct);
				return super.sucess(new ProductDTOResponse(foundProduct));
			}
			return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
}
