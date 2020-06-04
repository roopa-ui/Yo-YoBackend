package com.mindtree.yoyogift.service;

import java.util.List;

import com.mindtree.yoyogift.dto.GiftDto;
import com.mindtree.yoyogift.dto.MyGifts;
import com.mindtree.yoyogift.dto.MyOrder;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.serviceexception.YoYoGiftServiceException;

public interface UserService {

	UserDto getUser(String mailId);


	UserDto updateYoyoBalance(String mailId, int balance);

	public List<MyOrder> getOrders(String mailId);

	public List<MyGifts> getGifts(String mailId);

	boolean insertUserToDB(UserDto userDto) throws YoYoGiftServiceException;

	boolean loginUserFromDB(String mailId, String passWord) throws YoYoGiftServiceException;

	int getYoyoBalance(String mailId);


	String deleteUser(int giftId) throws YoYoGiftServiceException;

}
