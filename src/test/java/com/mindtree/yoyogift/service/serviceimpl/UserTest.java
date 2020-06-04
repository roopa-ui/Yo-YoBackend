package com.mindtree.yoyogift.service.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.MyGifts;
import com.mindtree.yoyogift.dto.MyOrder;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Gift;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.GiftRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.UserService;
import com.mindtree.yoyogift.service.serviceimpl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserTest {

	@TestConfiguration
	public static class Config {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	UserService userService;
	@MockBean
	UserRepository userRepository;
	@MockBean
	GiftRepository giftRepository;
	@MockBean
	FeedBackRepository feedBackRepository;

	@Test
	public void testUpdateYoyoBalance() {
		User user = new User(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 300);

		when(userRepository.findByMailId("gayathri@gmail.com")).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);

		assertEquals(userDto, userService.updateYoyoBalance("gayathri@gmail.com", 200));

	}

	@Test
	public void testGetYoyoBalance() {
		User user = new User(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);

		when(userRepository.findByMailId("gayathri@gmail.com")).thenReturn(user);

		assertEquals(100, userService.getYoyoBalance("gayathri@gmail.com"));
	}

	@Test
	public void testGetReceivedGifts() {
		List<Gift> gifts = new ArrayList<Gift>();
		List<MyGifts> giftDtos = new ArrayList<MyGifts>();
		Product product = new Product(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		
		ProductDto productDto = new ProductDto(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		Optional<FeedBack> feedBack = Optional.ofNullable(new FeedBack(1, 3, "good", product));
		
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);
		FeedBackDto feedback = new FeedBackDto(0,0,null,null,null);
		giftDtos.add(new MyGifts(1, null, 1, "abc.jpeg", "dress", userDto.getUserName(), feedback));
		User user = new User(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);

		gifts.add(new Gift(1, "abcd", true, null, product, user, user));

		when(giftRepository.getAllGiftsForUser("gayathri@gmail.com")).thenReturn(gifts);

		for (Gift g : gifts) {
			when(feedBackRepository.getUserFeedback("gayathri@gmail.com", g.getProduct().getProductId()))
					.thenReturn(feedBack);
		}
		System.out.println(giftDtos.toString());
		System.out.println(userService.getGifts("gayathri@gmail.com").toString());
		assertEquals(giftDtos.get(0), userService.getGifts("gayathri@gmail.com").get(0));
	}

	@Test
	public void testGetSentGifts() {
		List<Gift> gifts = new ArrayList<Gift>();

		List<MyOrder> sentGiftDto = new ArrayList<MyOrder>();
		Product product = new Product(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		ProductDto productDto = new ProductDto(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		Optional<FeedBack> feedBack = Optional.ofNullable(new FeedBack(0,0,null,null,null));
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);
		FeedBackDto feed = new FeedBackDto(0,0,null,null,null);

		sentGiftDto.add(new MyOrder(1, null, 1, "abc.jpeg", "dress", 100, feed, userDto.getUserName()));
		User user = new User(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);

		gifts.add(new Gift(1, "abcd", true, null, product, user, user));

		when(giftRepository.getAllSentGiftsForUser("gayathri@gmail.com")).thenReturn(gifts);

		for (Gift g : gifts) {
			when(feedBackRepository.getUserFeedback("gayathri@gmail.com", g.getProduct().getProductId()))
					.thenReturn(feedBack);
		}
		System.out.println(sentGiftDto.get(0));
		System.out.println( userService.getOrders("gayathri@gmail.com").get(0));
		assertEquals(sentGiftDto.get(0), userService.getOrders("gayathri@gmail.com").get(0));
	}

	@Test
	public void testGetSentGiftsUnclaimed() {
		List<Gift> gifts = new ArrayList<Gift>();

		List<MyOrder> sentGiftDto = new ArrayList<MyOrder>();
		Product product = new Product(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		ProductDto productDto = new ProductDto(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		Optional<FeedBack> feedBack = Optional.ofNullable(new FeedBack(1, 3, "good", product));
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);
		FeedBackDto feedback = new FeedBackDto(0,0,null,null,null);
		sentGiftDto.add(new MyOrder(1, null, 1, "abc.jpeg", "dress", 100, feedback, "Unclaimed!"));
		User user = new User(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);

		gifts.add(new Gift(1, "abcd", true, null, product, user, null));

		when(giftRepository.getAllSentGiftsForUser("gayathri@gmail.com")).thenReturn(gifts);

		for (Gift g : gifts) {
			when(feedBackRepository.getUserFeedback("gayathri@gmail.com", g.getProduct().getProductId()))
					.thenReturn(feedBack);
		}
		
		assertEquals(sentGiftDto.get(0), userService.getOrders("gayathri@gmail.com").get(0));
	}
	@Test
	public void testGetUser() {
		User user = new User(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);

		when(userRepository.findByMailId("gayathri@gmail.com")).thenReturn(user);
	

		assertEquals(userDto, userService.getUser("gayathri@gmail.com"));

	}	
}
