package com.mindtree.yoyogift.dto;

import java.time.LocalDate;

import com.mindtree.yoyogift.entity.FeedBack;

public class MyGifts {
	
	private int giftId;
	private LocalDate giftDate;
	private int productId;
	private String productImage;
	private String productName;
	private String recievedFrom;
	private FeedBackDto feedback;



	public MyGifts(int giftId, LocalDate giftDate, int productId, String productImage, String productName,
			String recievedFrom, FeedBackDto feedback) {
		super();
		this.giftId = giftId;
		this.giftDate = giftDate;
		this.productId = productId;
		this.productImage = productImage;
		this.productName = productName;
		this.recievedFrom = recievedFrom;
		this.feedback = feedback;
	}

	public MyGifts() {
		// TODO Auto-generated constructor stub
	}

	public int getGiftId() {
		return giftId;
	}

	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public LocalDate getGiftDate() {
		return giftDate;
	}

	public void setGiftDate(LocalDate giftDate) {
		this.giftDate = giftDate;
	}

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

	public String getRecievedFrom() {
		return recievedFrom;
	}

	public void setRecievedFrom(String recievedFrom) {
		this.recievedFrom = recievedFrom;
	}

	public FeedBackDto getFeedback() {
		return feedback;
	}

	public void setFeedback(FeedBackDto feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "MyGifts [giftId=" + giftId + ", giftDate=" + giftDate + ", productId=" + productId + ", productImage="
				+ productImage + ", productName=" + productName + ", recievedFrom=" + recievedFrom + ", feedback="
				+ feedback + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((giftDate == null) ? 0 : giftDate.hashCode());
		result = prime * result + giftId;
		result = prime * result + productId;
		result = prime * result + ((productImage == null) ? 0 : productImage.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((recievedFrom == null) ? 0 : recievedFrom.hashCode());
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
		MyGifts other = (MyGifts) obj;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (giftDate == null) {
			if (other.giftDate != null)
				return false;
		} else if (!giftDate.equals(other.giftDate))
			return false;
		if (giftId != other.giftId)
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
		if (recievedFrom == null) {
			if (other.recievedFrom != null)
				return false;
		} else if (!recievedFrom.equals(other.recievedFrom))
			return false;
		return true;
	}

}
