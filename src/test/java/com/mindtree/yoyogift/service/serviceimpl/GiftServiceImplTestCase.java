package com.mindtree.yoyogift.service.serviceimpl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
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

import com.mindtree.yoyogift.dto.GiftDto;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.Gift;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.serviceexception.YoYoGiftServiceException;
import com.mindtree.yoyogift.repository.GiftRepository;
import com.mindtree.yoyogift.repository.UserRepository;

import ch.qos.logback.core.joran.conditional.ThenAction;
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductServiceImpl.class)
public class GiftServiceImplTestCase {

	@InjectMocks
	GiftServiceImpl giftServiceImpl;

	@Mock
	GiftRepository giftRepository;

	@Mock
	UserRepository userRepository;
	
	@Before
	public void setUp() {

	}

	@Test
	public void testGenerateRandomCode() {
		
		assertEquals(giftServiceImpl.generateRandomCode().length(),24);
		
	}

	
	@Test
	public void testSendMailToRecipient() {
		String recipientMailId="abcd@gmail.com";
		String redeemCode="test448@gmail.com";
		assertEquals(giftServiceImpl.sendMailToRecipient(recipientMailId, redeemCode), true);
		assertNotEquals(giftServiceImpl.sendMailToRecipient(recipientMailId, redeemCode), false);
	}

	@Test
	public void testAddReceivedUser() throws YoYoGiftServiceException {
		String mailId="stark448@gmail.com";
		String code="abc";
		int status = 0;
		List<Gift> gifts=new ArrayList<Gift>();
		User user=new User(1,"Pradyumna","stark448@gmail.com","Abcd@1234",1000);
		UserDto userDto=new UserDto(1,"Pradyumna","stark448@gmail.com","Abcd@1234",1000);
		gifts.add(new Gift(1,"xyz",false,null,null,user,user));
		gifts.add(new Gift(2,"abc",false,null,null,user,user));
		when(giftRepository.findAll()).thenReturn(gifts);
		when(userRepository.findByMailId(mailId)).thenReturn(user);
		GiftDto giftActual=new GiftDto(2,"abc",true,null,null,userDto,userDto);
		
			for (Gift gift : gifts) {
		       		if (gift.getRedeemCode().equals(code)) {
					if (gift.isStatus() == false) {
					when(giftRepository.save(gift)).thenReturn(gift);
				}
					
					
			}
			
		}
		
		assertEquals(giftServiceImpl.addReceivedUser(mailId, code),giftActual);
	}
		

//	@Test
//	public void testPostGift() {
//		fail("Not yet implemented");
//	}

}
