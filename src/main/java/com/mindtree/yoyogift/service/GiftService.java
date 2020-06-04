package com.mindtree.yoyogift.service;

import com.mindtree.yoyogift.dto.GiftDto;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.exception.serviceexception.YoYoGiftServiceException;

public interface GiftService {

	public GiftDto addReceivedUser(String mailId, String code) throws YoYoGiftServiceException;


	GiftDto postGift(ProductDto productDto, String senderMailId, String receiverMailId);

	public String generateRandomCode();
	
	public boolean sendMailToRecipient(String recipientMailId,String redeemCode);
}
