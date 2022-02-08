package com.learning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int foodId;

	@NotNull
	private int foodCost;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private FOODTYPE foodType;

	@NotBlank
	private String foodPic;

}
