package com.mindtree.yoyogift.service;

import java.util.List;

import com.mindtree.yoyogift.dto.FeedBackDto;

public interface FeedbackService {
	
	public List<FeedBackDto> getFeedback();
	public FeedBackDto addFeedback(FeedBackDto feedback, int productId, String mailId);

}
