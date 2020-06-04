package com.mindtree.yoyogift.service.serviceimpl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.MyGifts;
import com.mindtree.yoyogift.dto.MyOrder;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Gift;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.serviceexception.YoYoGiftServiceException;
import com.mindtree.yoyogift.exception.utility.ErrorConstant;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.GiftRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	GiftRepository giftRepository;
	@Autowired
	FeedBackRepository feedbackRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public UserDto getUser(String mailId) {
		User user = userRepository.findByMailId(mailId);
		return convertEntityToDto(user);
	}


	public UserDto updateYoyoBalance(String mailId, int balance) {
		User user = userRepository.findByMailId(mailId);
		System.out.println("user in service "+user);
		int currentBalance = user.getYoyoCash() + balance;
		user.setYoyoCash(currentBalance);
		UserDto userDto =  convertEntityToDto(userRepository.save(user));
		System.out.println("Converted DTO "+userDto);
		return userDto;

	}

	@Override
	public List<MyOrder> getOrders(String mailId) {
		List<Gift> gift = giftRepository.getAllSentGiftsForUser(mailId);
		List<MyOrder> myOrders = new ArrayList<MyOrder>();
//		FeedBackDto feedback1 = new FeedBackDto();
		for (Gift g : gift) {
			Optional<FeedBack> feedback = feedbackRepository.getUserFeedback(mailId, g.getProduct().getProductId());

			MyOrder myOrder = new MyOrder();
			myOrder.setOrderId(g.getGiftId());
			myOrder.setOrderDate(g.getGiftDate());
			myOrder.setProductId(g.getProduct().getProductId());
			myOrder.setProductName(g.getProduct().getProductName());
			myOrder.setProductImage(g.getProduct().getProductImage());
			myOrder.setProductPrice(g.getProduct().getYoyoPrice());
			if (g.getReceiveGiftUser() == null) {
				myOrder.setSentTo("Unclaimed!");
			} else {
				myOrder.setSentTo(g.getReceiveGiftUser().getUserName());
			}
			if (feedback.isPresent()) {
				feedback.get();
				myOrder.setFeedback(convertEntityToDto(feedback.get()));
			}
			myOrders.add(myOrder);

		}

		return myOrders;
	}

	@Override
	public List<MyGifts> getGifts(String mailId) {
		List<Gift> gift = giftRepository.getAllGiftsForUser(mailId);
		List<MyGifts> myGifts = new ArrayList<MyGifts>();
		for (Gift g : gift) {
			Optional<FeedBack> feedback = feedbackRepository.getUserFeedback(mailId, g.getProduct().getProductId());

			MyGifts myGift = new MyGifts();
			myGift.setGiftId(g.getGiftId());
			myGift.setGiftDate(g.getGiftDate());
			myGift.setProductId(g.getProduct().getProductId());
			myGift.setProductName(g.getProduct().getProductName());
			myGift.setProductImage(g.getProduct().getProductImage());
			myGift.setRecievedFrom(g.getSentGiftUser().getUserName());
			if (feedback.isPresent()) {
				myGift.setFeedback(convertEntityToDto(feedback.get()));
			}
			myGifts.add(myGift);

		}
		return myGifts;
	}

	private UserDto convertEntityToDto(User user) {
		return modelMapper.map(user, UserDto.class);

	}
	private FeedBackDto convertEntityToDto(FeedBack feedback) {
		return modelMapper.map(feedback, FeedBackDto.class);

	}

	private FeedBack convertDtoToEntity(FeedBackDto feedback) {
		return modelMapper.map(feedback, FeedBack.class);
	}

	@Override
	public boolean insertUserToDB(UserDto userDto) throws YoYoGiftServiceException {

		boolean isLoggedIn = true;
		User user = new User();
		List<User> tempUsers = userRepository.findAll();
		if (tempUsers.isEmpty()) {
			user.setUserName(userDto.getUserName());
			user.setMailId(userDto.getMailId());
			user.setYoyoCash(0);
			String tempPassword = userDto.getPassWord();
			String encryptedString = encryptThisString(tempPassword);
			user.setPassWord(encryptedString);
			userRepository.save(user);
		} else {
			int countUser = tempUsers.size();
			for (User tempUser : tempUsers) {
				if (userDto.getMailId().equals(tempUser.getMailId())) {
					throw new YoYoGiftServiceException(ErrorConstant.EMAILIDALREADYEXISTS);
				} else {
					countUser--;
					if (countUser == 0) {
						user.setUserName(userDto.getUserName());
						user.setMailId(userDto.getMailId());
						user.setYoyoCash(0);
						String tempPassword = userDto.getPassWord();
						String encryptedString = encryptThisString(tempPassword);
						user.setPassWord(encryptedString);
						userRepository.save(user);
					}
				}
			}
		}
		return isLoggedIn;
	}

	@Override
	public boolean loginUserFromDB(String mailId, String passWord) throws YoYoGiftServiceException {
		boolean isLoggedIn = false;
		String encrypted = encryptThisString(passWord);
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getMailId().equals(mailId) && user.getPassWord().equals(encrypted)) {
				isLoggedIn = true;
				break;
			} else if ((!user.getMailId().equals(mailId)) && user.getPassWord().equals(encrypted)) {
				isLoggedIn = false;
				throw new YoYoGiftServiceException(ErrorConstant.INVALIDEMAILID);
			} else if (user.getMailId().equals(mailId) && (!user.getPassWord().equals(encrypted))) {
				isLoggedIn = false;
				throw new YoYoGiftServiceException(ErrorConstant.INVALIDPASSWORD);
			} else {
				isLoggedIn = false; 
			}
		}
		return isLoggedIn;
	}

	public static String encryptThisString(String input) {
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-1");

			byte[] messageDigest = md.digest(input.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		}

		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getYoyoBalance(String mailId) {
		User user = userRepository.findByMailId(mailId);
		return user.getYoyoCash();
	}


	@Override
	public String deleteUser(int userId) throws YoYoGiftServiceException {
		Optional<User> gift =userRepository.findById(userId);
		if(gift.isPresent()) {
	
			userRepository.deleteById(userId);	
			
		}
		else {
			throw new YoYoGiftServiceException(ErrorConstant.USERNOTEXISTS);
		}
		return "deleted";
	}

}
