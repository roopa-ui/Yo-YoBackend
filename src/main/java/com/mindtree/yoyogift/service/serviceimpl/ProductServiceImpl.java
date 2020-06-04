package com.mindtree.yoyogift.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.repository.CategoryRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ProductDto> getAllProduct() {

		return productRepository.findAll().stream().map(entity -> convertEntityToDto(entity))
				.collect(Collectors.toList());

	}

	private ProductDto convertEntityToDto(Product product) {
		return modelMapper.map(product, ProductDto.class);

	}

}
