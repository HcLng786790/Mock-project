package com.mockproject.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.VariantProductDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.model.Product;
import com.mockproject.demo.model.VariantProduct;
import com.mockproject.demo.repository.ProductRepository;
import com.mockproject.demo.repository.VariantProductRepository;
import com.mockproject.demo.service.VariantProductService;

@RestController
@RequestMapping("/variant_product")
public class VariantProductController extends BaseRestController {
	@Autowired
	private VariantProductRepository variantProductRepository;
	@Autowired
	private VariantProductService variantProductService;
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("")
	public ResponseEntity<?> addNewVariant(@RequestBody(required = false) Map<String, Object> newVariant) {
		try {
			if (ObjectUtils.isEmpty(newVariant) || ObjectUtils.isEmpty(newVariant.get("productId"))
					|| ObjectUtils.isEmpty(newVariant.get("model")) || ObjectUtils.isEmpty(newVariant.get("color"))
					||ObjectUtils.isEmpty(newVariant.get("name"))
					|| ObjectUtils.isEmpty(newVariant.get("size")) || ObjectUtils.isEmpty(newVariant.get("price"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			long productId = Long.parseLong(newVariant.get("productId").toString());
			Product foundProduct = this.productRepository.findById(productId).orElse(null);
			if (!ObjectUtils.isEmpty(foundProduct)) {
				VariantProduct variantProduct = this.variantProductService.addNewVariant(newVariant, foundProduct);
				return super.sucess(new VariantProductDTOResponse(variantProduct));
			}
			return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			List<VariantProduct> variantProducts = this.variantProductRepository.findAll();
			List<VariantProductDTOResponse> response = variantProducts.stream().map(VariantProductDTOResponse::new).toList();
			return super.sucess(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateVariantProduct(@PathVariable long id,@RequestBody(required = false) Map<String, Object> newVariant){
		try {
			if (ObjectUtils.isEmpty(newVariant) || ObjectUtils.isEmpty(id)
					|| ObjectUtils.isEmpty(newVariant.get("model")) || ObjectUtils.isEmpty(newVariant.get("color"))
					|| ObjectUtils.isEmpty(newVariant.get("size")) || ObjectUtils.isEmpty(newVariant.get("price"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			VariantProduct variantProduct = this.variantProductRepository.findById(id).orElse(null);
			if (!ObjectUtils.isEmpty(variantProduct)) {
				variantProduct=this.variantProductService.updateVariant(id, newVariant, variantProduct);
				return super.sucess(new VariantProductDTOResponse(variantProduct));
			}
			return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
}
