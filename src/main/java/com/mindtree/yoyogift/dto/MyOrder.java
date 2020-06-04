package com.mindtree.yoyogift.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.yoyogift.entity.FeedBack;

public class MyOrder {

	private int orderId;
	private LocalDate orderDate;

	private int productId;
	private String productImage;
	private String productName;
	private int productPrice;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	
	private FeedBackDto feedback;
	private String sentTo;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate Date) {
		this.orderDate = Date;
	}

	public FeedBackDto getFeedback() {
		return feedback;
	}

	public void setFeedback(FeedBackDto feedback) {
		this.feedback = feedback;
	}

	public MyOrder(int orderId, LocalDate orderDate, int productId, String productImage, String productName,
			int productPrice, FeedBackDto feedback, String sentTo) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.productId = productId;
		this.productImage = productImage;
		this.productName = productName;
		this.productPrice = productPrice;
		this.feedback = feedback;
		this.sentTo = sentTo;
	}

	public MyOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderId;
		result = prime * result + productId;
		result = prime * result + ((productImage == null) ? 0 : productImage.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + productPrice;
		result = prime * result + ((sentTo == null) ? 0 : sentTo.hashCode());
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
		MyOrder other = (MyOrder) obj;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId != other.orderId)
			return false;
		if (productId != other.productId)
			return false;
		if (productImage == null) {
			if (other.productImage != null)
				return false;
		} else if (!productImage.equals(other.productImage))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice != other.productPrice)
			return false;
		if (sentTo == null) {
			if (other.sentTo != null)
				return false;
		} else if (!sentTo.equals(other.sentTo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyOrder [orderId=" + orderId + ", orderDate=" + orderDate + ", productId=" + productId
				+ ", productImage=" + productImage + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", feedback=" + feedback + ", sentTo=" + sentTo + "]";
	}

	
	
}
