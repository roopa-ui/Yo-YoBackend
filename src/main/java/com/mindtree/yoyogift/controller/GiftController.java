package com.mindtree.yoyogift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.yoyogift.dto.GiftDto;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.ResponseDto;
import com.mindtree.yoyogift.exception.YoYoGiftApplicationException;
import com.mindtree.yoyogift.service.GiftService;

@CrossOrigin
@RestController
@RequestMapping("gift")
public class GiftController {
	@Autowired
	GiftService giftService;

	@GetMapping("/redeem/{mailId}/{code}")
	public ResponseEntity<ResponseDto<GiftDto>> redeemGifts(@PathVariable String mailId, @PathVariable String code) throws YoYoGiftApplicationException {
		
		ResponseDto<GiftDto> response = new ResponseDto<GiftDto>( giftService.addReceivedUser(mailId, code), null, "Assigned", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PostMapping("/post-gift/{senderMailId}/{receiverMailId}")
	public ResponseEntity<ResponseDto<GiftDto>> postGift(@RequestBody ProductDto productDto, @PathVariable String senderMailId,
			@PathVariable String receiverMailId) {
		ResponseDto<GiftDto> response = new ResponseDto<GiftDto>( giftService.postGift(productDto, senderMailId, receiverMailId), null, "Assigned", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
		

	}

}
