package com.mindtree.yoyogift.dto;

import java.time.LocalDate;

public class GiftDto {
	private int giftId;
	private String redeemCode;
	private boolean status;
	private LocalDate giftDate;
	private ProductDto product;
	private UserDto sentGiftUser;
	private UserDto receiveGiftUser;
	
	public GiftDto(int giftId, String redeemCode, boolean status, LocalDate giftDate, ProductDto product,
			UserDto sentGiftUser, UserDto receiveGiftUser) {
		super();
		this.giftId = giftId;
		this.redeemCode = redeemCode;
		this.status = status;
		this.giftDate = giftDate;
		this.product = product;
		this.sentGiftUser = sentGiftUser;
		this.receiveGiftUser = receiveGiftUser;
	}
	public GiftDto() {
		super();
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public String getRedeemCode() {
		return redeemCode;
	}
	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public LocalDate getGiftDate() {
		return giftDate;
	}
	public void setGiftDate(LocalDate giftDate) {
		this.giftDate = giftDate;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public UserDto getSentGiftUser() {
		return sentGiftUser;
	}
	public void setSentGiftUser(UserDto sentGiftUser) {
		this.sentGiftUser = sentGiftUser;
	}
	public UserDto getReceiveGiftUser() {
		return receiveGiftUser;
	}
	public void setReceiveGiftUser(UserDto receiveGiftUser) {
		this.receiveGiftUser = receiveGiftUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((giftDate == null) ? 0 : giftDate.hashCode());
		result = prime * result + giftId;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((receiveGiftUser == null) ? 0 : receiveGiftUser.hashCode());
		result = prime * result + ((redeemCode == null) ? 0 : redeemCode.hashCode());
		result = prime * result + ((sentGiftUser == null) ? 0 : sentGiftUser.hashCode());
		result = prime * result + (status ? 1231 : 1237);
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
		GiftDto other = (GiftDto) obj;
		if (giftDate == null) {
			if (other.giftDate != null)
				return false;
		} else if (!giftDate.equals(other.giftDate))
			return false;
		if (giftId != other.giftId)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (receiveGiftUser == null) {
			if (other.receiveGiftUser != null)
				return false;
		} else if (!receiveGiftUser.equals(other.receiveGiftUser))
			return false;
		if (redeemCode == null) {
			if (other.redeemCode != null)
				return false;
		} else if (!redeemCode.equals(other.redeemCode))
			return false;
		if (sentGiftUser == null) {
			if (other.sentGiftUser != null)
				return false;
		} else if (!sentGiftUser.equals(other.sentGiftUser))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
}
