package com.mindtree.yoyogift.dto;



import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.yoyogift.entity.User;

public class FeedBackDto {
	private int feedbackId;
	@Range(min=1,max=5)
	private int feedbackStarRating;
	private String feedbackComment;
	@JsonIgnore
	private ProductDto product;
	
	private UserDto user;
	public FeedBackDto(int feedbackId, int feedbackStarRating, String feedbackComment, ProductDto product, UserDto user) {
		super();
		this.feedbackId = feedbackId;
		this.feedbackStarRating = feedbackStarRating;
		this.feedbackComment = feedbackComment;
		this.product = product;
		this.user = user;
	}
	public FeedBackDto() {
		super();
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getFeedbackStarRating() {
		return feedbackStarRating;
	}
	public void setFeedbackStarRating(int feedbackStarRating) {
		this.feedbackStarRating = feedbackStarRating;
	}
	public String getFeedbackComment() {
		return feedbackComment;
	}
	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "FeedBackDto [feedbackId=" + feedbackId + ", feedbackStarRating=" + feedbackStarRating
				+ ", feedbackComment=" + feedbackComment + ", product=" + product + ", user=" + user + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedbackComment == null) ? 0 : feedbackComment.hashCode());
		result = prime * result + feedbackId;
		result = prime * result + feedbackStarRating;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedBackDto other = (FeedBackDto) obj;
		if (feedbackComment == null) {
			if (other.feedbackComment != null)
				return false;
		} else if (!feedbackComment.equals(other.feedbackComment))
			return false;
		if (feedbackId != other.feedbackId)
			return false;
		if (feedbackStarRating != other.feedbackStarRating)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
