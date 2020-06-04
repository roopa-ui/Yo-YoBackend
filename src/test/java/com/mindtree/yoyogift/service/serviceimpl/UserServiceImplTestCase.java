package com.mindtree.yoyogift.service.serviceimpl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.serviceexception.YoYoGiftServiceException;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.GiftRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.UserService;

@RunWith(SpringRunner.class)
public class UserServiceImplTestCase {

	@org.springframework.boot.test.context.TestConfiguration
	public static class TestConfiguration {

		@Bean
		public UserService service() {
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
	FeedBackRepository feedbackRepository;

//	@Before
//	public void setUp() {
//
//	}

//	@Test
//	void testGetUser() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testGetReceivedGifts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetSentGifts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdateYoyoBalance() {
//	}
//
//	@Test
//	void testGetOrders() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetGifts() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testInsertUserToDB() throws YoYoGiftServiceException {
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 4; i++) {
			User user = new User(45 + i, "Jay" + i, "jay@gmail.com" + i, "Jay12345" + i, i);
			users.add(user);
		}
		User user = new User(39, "Kim", "kim@gmail,com", "Kim12345", 0);
		UserDto userDto = new UserDto(39, "Kim", "kim@gmail,com", "Kim12345", 0);
		System.out.println(users.size());
		Mockito.when(userRepository.findAll()).thenReturn(users);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertEquals(true, userService.insertUserToDB(userDto));
	}

	@Test
	public void testLoginUserFromDB() throws YoYoGiftServiceException {
		String mailId = "jay@gmail.com";
		String passWord = "Jay12345";
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 4; i++) {
			User user = new User();
			user.setMailId(mailId);
			user.setPassWord("81531e0febfb870ed5f7378c9a1a73de2c207322");
			users.add(user);
		}
		System.out.println(users.size());
		Mockito.when(userRepository.findAll()).thenReturn(users);
		assertEquals(true, userService.loginUserFromDB(mailId,passWord));

	}
////
//	@Test
//	void testEncryptThisString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetYoyoBalance() {
//		fail("Not yet implemented");
//	}

}
