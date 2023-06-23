package com.mockproject.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.CategoryDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.model.Category;
import com.mockproject.demo.repository.CategoryRepository;
import com.mockproject.demo.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseRestController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("")
	public ResponseEntity<?> getAllCategory() {
		try {
			List<Category> categories = this.categoryRepository.findAll();
			List<CategoryDTOResponse> response = categories.stream().map(CategoryDTOResponse::new).toList();
			return super.sucess(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}

	@PostMapping("")
	public ResponseEntity<?> addNewCategory(@RequestBody(required = false) Map<String, Object> newCategory) {
		try {
			if (ObjectUtils.isEmpty(newCategory) || ObjectUtils.isEmpty(newCategory.get("name"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			Category category = this.categoryService.addNewCategory(newCategory);
			return super.sucess(new CategoryDTOResponse(category));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}

}
