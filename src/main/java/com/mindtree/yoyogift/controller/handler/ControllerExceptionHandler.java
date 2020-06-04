package com.mindtree.yoyogift.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.yoyogift.controller.FeedBackController;
import com.mindtree.yoyogift.controller.GiftController;
import com.mindtree.yoyogift.controller.ProductController;
import com.mindtree.yoyogift.controller.UserController;
import com.mindtree.yoyogift.dto.ErrorDto;
import com.mindtree.yoyogift.dto.ResponseDto;
import com.mindtree.yoyogift.exception.YoYoGiftApplicationException;


@RestControllerAdvice(assignableTypes= {ProductController.class,FeedBackController.class,GiftController.class,UserController.class})
public class ControllerExceptionHandler {
	@ExceptionHandler(YoYoGiftApplicationException.class)
	public ResponseEntity<ResponseDto> YoYoGiftApplicationExceptionHandler(Exception e,Throwable cause) {
		ResponseDto response = new ResponseDto(null,new ErrorDto(e.getMessage(),e.getCause()),"Exception Occured",false);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}


}
