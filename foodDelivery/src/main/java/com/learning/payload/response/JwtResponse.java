package com.learning.payload.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JwtResponse {

	@Setter(value = AccessLevel.NONE)
	@Getter(value = AccessLevel.NONE)
	private String token; // It is an encrypted string which will help us to access the secured end point
							// from the server.
	@Setter(value = AccessLevel.NONE)
	@Getter(value = AccessLevel.NONE)
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	@Setter(value = AccessLevel.NONE)
	private List<String> roles;

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String type) {
		this.type = type;
	}

	public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

}