package com.mindtree.yoyogift.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.MyGifts;
import com.mindtree.yoyogift.dto.MyOrder;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.service.UserService;
@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testUser() throws Exception {
		String userEmail = "gayathri@gmail.com";
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 300);
		when(userService.getUser(userEmail)).thenReturn(userDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/get-user/userEmail").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	@Test
	public void testGetBalance() throws Exception {
		String userEmail = "gayathri@gmail.com";
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 300);
		when(userService.getYoyoBalance(userEmail)).thenReturn(300);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/get-yoyo-balance/userEmail").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	@Test
	public void testUpdateYoyoBalance() throws Exception {
		String userEmail = "gayathri@gmail.com";
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 200);
		when(userService.updateYoyoBalance(userEmail, 200)).thenReturn(userDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/user/update-yoyo-balance/userEmail/200")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testGetGifts() throws Exception {
		String userEmail = "gayathri@gmail.com";
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 300);
		List<MyGifts> giftDtos = new ArrayList<MyGifts>();
		Product product = new Product(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		ProductDto product1 = new ProductDto(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);

		Optional<FeedBack> feedBack = Optional.ofNullable(new FeedBack(1, 3, "good", product));
		FeedBackDto feed = new FeedBackDto(1,3,"good",product1,userDto);

		giftDtos.add(new MyGifts(1, null, 1, "abc.jpeg", "dress", userDto.getUserName(),feed));
		when(userService.getGifts(userEmail)).thenReturn(giftDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getgifts/userEmail")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}
	@Test
	public void testGetorders() throws Exception {
		String userEmail = "gayathri@gmail.com";
		List<MyOrder> sentGiftDto = new ArrayList<MyOrder>();
		Product product = new Product(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		ProductDto product1 = new ProductDto(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		Optional<FeedBack> feedBack = Optional.ofNullable(new FeedBack(1, 3, "good", product));
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 100);
		FeedBackDto feed = new FeedBackDto(1,3,"good",product1,userDto);

		sentGiftDto.add(new MyOrder(1, null, 1, "abc.jpeg", "dress", 100, feed, userDto.getUserName()));
		when(userService.getOrders(userEmail)).thenReturn(sentGiftDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/getorders/userEmail")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}

	}

	
	@Test
	public void insertUserToServiceTest() throws Exception {

		UserDto user = new UserDto();
		user.setUserName("john");
		user.setMailId("john123@gmail.com");
		user.setPassWord("john123");
		Mockito.when(userService.insertUserToDB(Mockito.refEq(user))).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/user/register-user").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void loginUserToServiceTest() throws Exception {

		String mailId = "john123@gmail.com";
		String passWord = "john123";
		Mockito.when(userService.loginUserFromDB(mailId, passWord)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/login/john123@gmail.com/john123")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
