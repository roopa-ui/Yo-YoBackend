package com.mindtree.yoyogift.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.ResponseDto;
import com.mindtree.yoyogift.service.FeedbackService;

@CrossOrigin
@RestController
@RequestMapping("feedback")
public class FeedBackController {
	
	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping("getfeedback")
	public ResponseEntity<ResponseDto<List<FeedBackDto>>> getFeedback()
	{
		ResponseDto<List<FeedBackDto>> response = new ResponseDto<List<FeedBackDto>>(feedbackService.getFeedback(), null, "Assigned", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("addfeedback/{productId}/{mailId}")
	public ResponseEntity<ResponseDto<FeedBackDto>> addFeedback(@Valid @RequestBody FeedBackDto feedback,@PathVariable int productId,@PathVariable String mailId)
	{
		ResponseDto<FeedBackDto> response = new ResponseDto<FeedBackDto>(feedbackService.addFeedback(feedback,productId,mailId), null, "Assigned", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

}
