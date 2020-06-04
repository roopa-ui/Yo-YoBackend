package com.mindtree.yoyogift.dto;

public class UserDto {
	private int userId;
	private String userName;
	private String mailId;
	private String passWord;

	private int yoyoCash;
	public UserDto(int userId, String userName, String mailId, String passWord, int yoyoCash) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mailId = mailId;
		this.passWord = passWord;
		this.yoyoCash = yoyoCash;
	}
	public UserDto() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getYoyoCash() {
		return yoyoCash;
	}
	public void setYoyoCash(int yoyoCash) {
		this.yoyoCash = yoyoCash;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", mailId=" + mailId + ", passWord=" + passWord
				+ ", yoyoCash=" + yoyoCash + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mailId == null) ? 0 : mailId.hashCode());
		result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + yoyoCash;
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
		UserDto other = (UserDto) obj;
		if (mailId == null) {
			if (other.mailId != null)
				return false;
		} else if (!mailId.equals(other.mailId))
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (yoyoCash != other.yoyoCash)
			return false;
		return true;
	}
	
	

}
