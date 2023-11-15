
package com.example.back.payload;

import lombok.Data;

@Data
public class LoginDto {
	private String userEmail;
	private String userPassword;

	public LoginDto() {
	}

	public LoginDto(String userEmail, String userPassword) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
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

	@Override
	public String toString() {
		return "LoginDto [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}

}