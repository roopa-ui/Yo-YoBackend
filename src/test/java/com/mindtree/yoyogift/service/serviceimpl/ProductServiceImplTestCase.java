package com.mindtree.yoyogift.service.serviceimpl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.mindtree.yoyogift.dto.CategoryDto;
import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.Category;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductServiceImpl.class)
public class ProductServiceImplTestCase {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	@Before
	public void setUp() {

	}

	@Test
	public void testGetAllProduct() {

		List<Product> listProduct = new ArrayList<Product>();

		Category category = new Category(1, "garmentts", null);
		List<FeedBack> listFeedback = new ArrayList<FeedBack>();
		User user = new User(1, "raj", "abc", "cdcs", 2344);
		listFeedback.add(new FeedBack(1, 3, "asds", null, user));
		listProduct.add(new Product(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", category, listFeedback));
		when(productRepository.findAll()).thenReturn(listProduct);
		
		List<ProductDto> listProductDtos = new ArrayList<ProductDto>();
		CategoryDto categoryDto = new CategoryDto(1, "garmentts", null);
		List<FeedBackDto> listFeedbackDtos = new ArrayList<FeedBackDto>();
		UserDto userDto = new UserDto(1, "raj", "abc", "cdcs", 2344);
		listFeedbackDtos.add(new FeedBackDto(1, 3, "asds", null, userDto));
		listProductDtos.add(new ProductDto(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", categoryDto, listFeedbackDtos));
		assertEquals(listProductDtos.get(0), productServiceImpl.getAllProduct().get(0));
		



	}

}
