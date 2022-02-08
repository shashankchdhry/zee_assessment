package com.learning.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Login {

	@Id
	@Size(max = 50)
	@NotBlank
	@Email
	private String email;

	@Size(max = 100)
	@NotBlank
	private String password;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "registerId")
	private Register register;

}