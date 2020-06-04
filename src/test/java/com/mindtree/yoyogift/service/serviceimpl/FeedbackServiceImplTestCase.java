package com.mindtree.yoyogift.service.serviceimpl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.FeedbackService;

@RunWith(SpringRunner.class)
@WebMvcTest(FeedbackServiceImpl.class)
public class FeedbackServiceImplTestCase {

	@TestConfiguration
	public static class Config {
		@Bean
		public FeedbackService feedbackService() {
			return new FeedbackServiceImpl();
		}
	}

	@Autowired
	FeedbackService feedbackService;

//	@Autowired
//	FeedbackService feedbackService;
//	
	@MockBean
	FeedBackRepository feedbackRepository;

	@MockBean
	ProductRepository productRepository;

	@MockBean
	UserRepository userRepository;

	@Test
	public void testGetFeedback() {

		List<FeedBack> feedbackList = new ArrayList<FeedBack>();
		List<FeedBackDto> feedbackDtoList = new ArrayList<FeedBackDto>();
		User user = new User(1, "raj", "abc", "cdcs", 2344);
		UserDto userDto = new UserDto(1, "raj", "abc", "cdcs", 2344);
		Product product = new Product(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", null, null);
		ProductDto productDto = new ProductDto(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", null, null);
		feedbackList.add(new FeedBack(1, 5, "good", product, user));
		feedbackDtoList.add(new FeedBackDto(1, 5, "good", productDto, userDto));
		when(feedbackRepository.findAll()).thenReturn(feedbackList);
		assertThat(feedbackService.getFeedback(), contains(hasProperty("feedbackStarRating", is(5))));
	}

	@Test
	public void testAddFeedbackIfExists() {

		User user = new User(1, "raj", "abc", "cdcs", 2344);
		UserDto userDto = new UserDto(1, "raj", "abc", "cdcs", 2344);
		Product product = new Product(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", null, null);
		ProductDto productDto = new ProductDto(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", null, null);
		FeedBack feedback = new FeedBack(1, 5, "good", product, user);
		FeedBackDto newfeedback = new FeedBackDto(1, 4, "not bad", productDto, userDto);
		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		when(userRepository.findByMailId("abc")).thenReturn(user);
		when(feedbackRepository.getUserFeedback("abc", 1)).thenReturn(Optional.of(feedback));
		when((feedbackRepository.saveAndFlush(feedback))).thenReturn(feedback);
	
		assertEquals(newfeedback, (feedbackService.addFeedback(newfeedback, 1, "abc")));
	}

	@Test
	public void testAddFeedbackIfNotExists() {
		User user = new User(1, "raj", "abc", "cdcs", 2344);
		UserDto userDto = new UserDto(1, "raj", "abc", "cdcs", 2344);
		Product product = new Product(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", null, null);
		ProductDto productDto = new ProductDto(1, "soap", 200, "fddsaf", "sdfsaf", "dsafdg", null, null);
		FeedBack feedback = new FeedBack(1, 5, "good", product, user);
		FeedBackDto newfeedback = new FeedBackDto(1, 5, "good", productDto, userDto);

		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		when(userRepository.findByMailId("abc")).thenReturn(user);
		when(feedbackRepository.getUserFeedback("abc", 1)).thenReturn(Optional.empty());
		

		when(feedbackRepository.save(feedback)).thenReturn(feedback);
//		System.out.println(newfeedback);
//		System.out.println(feedbackServiceImpl.addFeedback(newfeedback, 1, "abc"));
		assertEquals(newfeedback, (feedbackService.addFeedback(newfeedback, 1, "abc")));
	}

}
