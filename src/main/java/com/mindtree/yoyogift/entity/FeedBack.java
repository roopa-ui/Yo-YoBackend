package com.mindtree.yoyogift.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class FeedBack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedbackId;
	private int feedbackStarRating;
	private String feedbackComment;
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public FeedBack(int feedbackId, int feedbackStarRating, String feedbackComment, Product product) {
		super();
	}

	public FeedBack(int feedbackId, int feedbackStarRating, String feedbackComment, Product product, User user) {
		this.feedbackId = feedbackId;
		this.feedbackStarRating = feedbackStarRating;
		this.feedbackComment = feedbackComment;
		this.product = product;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FeedBack() {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
		FeedBack other = (FeedBack) obj;
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
