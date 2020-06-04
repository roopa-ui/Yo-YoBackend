package com.mindtree.yoyogift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.yoyogift.dto.GiftDto;
import com.mindtree.yoyogift.dto.MyGifts;
import com.mindtree.yoyogift.dto.MyOrder;
import com.mindtree.yoyogift.dto.ResponseDto;
import com.mindtree.yoyogift.dto.UserDto;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.YoYoGiftApplicationException;
import com.mindtree.yoyogift.exception.serviceexception.YoYoGiftServiceException;
import com.mindtree.yoyogift.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")

public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/get-user/{mailId}")
	public ResponseEntity<ResponseDto<User>> getUser(@PathVariable String mailId) {
		ResponseDto response = new ResponseDto(userService.getUser(mailId), null, "User", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PutMapping("/update-yoyo-balance/{mailId}/{balance}")
	public ResponseEntity<ResponseDto<UserDto>> updateYoyoBalance(@PathVariable String mailId,
			@PathVariable int balance) {

		ResponseDto<UserDto> response = new ResponseDto<UserDto>(userService.updateYoyoBalance(mailId, balance), null,
				"Fetch Orders", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("getorders/{mailId}")
	public ResponseEntity<ResponseDto<List<MyOrder>>> getOrders(@PathVariable String mailId) {
		ResponseDto<List<MyOrder>> response = new ResponseDto<List<MyOrder>>(userService.getOrders(mailId), null,
				"Fetch Orders", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("getgifts/{mailId}")
	public ResponseEntity<ResponseDto<List<MyGifts>>> getGifts(@PathVariable String mailId) {
		ResponseDto<List<MyGifts>> response = new ResponseDto<List<MyGifts>>(userService.getGifts(mailId), null,
				"Fetch Orders", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PostMapping("/register-user")
	public ResponseEntity<ResponseDto> insertUserToService(@RequestBody UserDto userDto)
			throws YoYoGiftServiceException {

		ResponseDto response = new ResponseDto(userService.insertUserToDB(userDto), null, "Registered", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/login/{mailId}/{passWord}")
	public ResponseEntity<ResponseDto> loginUserFromService(@PathVariable String mailId, @PathVariable String passWord)
			throws YoYoGiftServiceException {
		ResponseDto response = new ResponseDto(userService.loginUserFromDB(mailId, passWord), null, "LoggedIn", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get-yoyo-balance/{mailId}")
	public ResponseEntity<ResponseDto<Integer>> getYoyoBalance(@PathVariable String mailId) {
		ResponseDto response = new ResponseDto(userService.getYoyoBalance(mailId), null, "balance", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@DeleteMapping("/delete-user/{userId}")
	public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable int userId)
			throws YoYoGiftApplicationException {
		ResponseDto<String> response = new ResponseDto(userService.deleteUser(userId), null, "done", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
}
