package com.mindtree.yoyogift.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.yoyogift.dto.ProductDto;

@Entity
public class Gift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int giftId;
	private String redeemCode;
	private boolean status;
	private LocalDate giftDate;
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;
	@OneToOne(cascade = CascadeType.ALL)
	private User sentGiftUser;
	@OneToOne(cascade = CascadeType.ALL)
	private User receivedGiftUser;

	public Gift(int giftId, String redeemCode, boolean status, LocalDate giftDate, Product product, User sentGiftUser,
			User receiveGiftUser) {
		super();
		this.giftId = giftId;
		this.redeemCode = redeemCode;
		this.status = status;
		this.giftDate = giftDate;
		this.product = product;
		this.sentGiftUser = sentGiftUser;
		this.receivedGiftUser = receiveGiftUser;
	}

	public Gift() {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getSentGiftUser() {
		return sentGiftUser;
	}

	public void setSentGiftUser(User sentGiftUser) {
		this.sentGiftUser = sentGiftUser;
	}

	public User getReceiveGiftUser() {
		return receivedGiftUser;
	}

	public void setReceiveGiftUser(User receiveGiftUser) {
		this.receivedGiftUser = receiveGiftUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((giftDate == null) ? 0 : giftDate.hashCode());
		result = prime * result + giftId;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((receivedGiftUser == null) ? 0 : receivedGiftUser.hashCode());
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
		Gift other = (Gift) obj;
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
		if (receivedGiftUser == null) {
			if (other.receivedGiftUser != null)
				return false;
		} else if (!receivedGiftUser.equals(other.receivedGiftUser))
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
