package com.tekup.tp4.web.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {
	private String code;
	private String name;
	private Double price;
	private String img;
}
