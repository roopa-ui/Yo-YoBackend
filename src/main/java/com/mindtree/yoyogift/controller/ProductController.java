package com.mindtree.yoyogift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.ResponseDto;
import com.mindtree.yoyogift.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;


	@GetMapping("/getallproducts")
	public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProduct() {
		ResponseDto<List<ProductDto>> response = new ResponseDto<List<ProductDto>>(productService.getAllProduct(), null, "Fetch Products", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	
}
