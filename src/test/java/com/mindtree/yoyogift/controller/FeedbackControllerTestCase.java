package com.mindtree.yoyogift.controller;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.service.FeedbackService;
@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(FeedBackController.class)
public class FeedbackControllerTestCase {
	
	@InjectMocks
	private FeedBackController feedbackController;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private FeedbackService feedbackService;
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}

	}
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
	}
	


	@Test
	public void testGetFeedback() throws Exception {
		List<FeedBackDto> feedback = new ArrayList<FeedBackDto>();
		feedback.add(new FeedBackDto(1,5,"good",null,null));
		
		when(feedbackService.getFeedback()).thenReturn(feedback);
		mockMvc.perform(MockMvcRequestBuilders.get("/feedback/getfeedback")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
		
	}

	@Test
	public void testAddFeedback() throws Exception {
		
		String userEmail = "gayathri@gmail.com";
		
		UserDto userDto = new UserDto(1, "gayathri", "gayathri@gmail.com", "gayathri1234", 300);
		ProductDto product = new ProductDto(1, "dress", 100, "jeans", "wrangler", "abc.jpeg", null, null);
		FeedBackDto feedback = new FeedBackDto(1,2,"good",product,userDto);
		
		when(feedbackService.addFeedback(feedback,product.getProductId(),userEmail)).thenReturn(feedback);
		mockMvc.perform(MockMvcRequestBuilders.post("/feedback/addfeedback/1/userEmail")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(feedback)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
