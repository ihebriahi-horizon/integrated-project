package com.example.back.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String userEmail;
    private String userPassword;
    private String userFirstname;
    private String userLastname;
    private String userAddress;
    private byte userGender;
    private int userAge;
    
	public SignUpDto() {
	}
	public SignUpDto(String userEmail, String userPassword, String userFirstname, String userLastname,
			String userAddress, byte userGender, int userAge) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.userAddress = userAddress;
		this.userGender = userGender;
		this.userAge = userAge;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserFirstname() {
		return userFirstname;
	}
	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}
	public String getUserLastname() {
		return userLastname;
	}
	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public byte getUserGender() {
		return userGender;
	}
	public void setUserGender(byte userGender) {
		this.userGender = userGender;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	@Override
	public String toString() {
		return "SignUpDto [userEmail=" + userEmail + ", userPassword=" + userPassword + ", userFirstname="
				+ userFirstname + ", userLastname=" + userLastname + ", userAddress=" + userAddress + ", userGender="
				+ userGender + ", userAge=" + userAge + "]";
	}
    
    
}