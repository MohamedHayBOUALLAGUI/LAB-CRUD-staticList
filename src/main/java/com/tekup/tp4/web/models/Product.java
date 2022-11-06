package com.tekup.tp4.web.models;

//import org.springframework.lang.NonNull;

public class Product {

	private long id;
	//@NonNull
	private String code;
	private String name;
	private Double price;
	private String img;
	public Product(long id, String code, String name, Double price, String img) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		this.img = img;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", price=" + price + ", img=" + img + "]";
	}
	
	
}
