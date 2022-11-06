package com.tekup.tp4.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tekup.tp4.web.models.Product;
import com.tekup.tp4.web.models.requests.ProductForm;

@Controller
public class ProductController {
	private static List<Product> products = new ArrayList<Product>();
	private static Long idCount =0L; 
	 static {
	 products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9",1000D, "samsung-s9.png"));
	 products.add(new Product(++idCount, "NK-5P", "Nokia 5.1 Plus", 600D, null));
	 products.add(new Product(++idCount, "IP-7", "iPhone 7",1500D, "iphone-7.jpg"));
	 }
	 
	 
	// Injectez via application.properties.
	  @Value("${welcome.message}")
	  private String message;
	  @Value("${error.message}")
	  private String errorMessage;
	  
	  @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	  public String index(Model model) {
	  model.addAttribute("message", message);
	  return "index";
	  }
	  @RequestMapping(value = { "/product-list" }, method = RequestMethod.GET)
	  public String personList(Model model) {
	  model.addAttribute("products", products);
	  return "product-list";
	  }
	  
	  @RequestMapping(value = { "/addProduct" }, method = RequestMethod.GET)
	  public String showAddProductPage(Model model) {
	  ProductForm productForm = new ProductForm();
	  model.addAttribute("productForm", productForm);
	  return "addProduct";
	  }
	  
	  @RequestMapping(value = { "/addProduct" }, method = RequestMethod.POST)
	  public String saveProduct(Model model, //
	  @ModelAttribute("productForm") ProductForm productForm) {
	  
	  String code = productForm.getCode();
	  String name = productForm.getName();
	  Double price = productForm.getPrice();
	  String img;
	  if(productForm.getImg()=="") {
		  img=null;
	  }else {
		  img = productForm.getImg();
	  }
	 
	  if (code != null && code.length() > 0 //
	  && name != null && name.length() > 0 && price >0.0) {
	  Product newProduct = new Product(++idCount, code, name,price, img);
	  products.add(newProduct); 
	  return "redirect:/product-list";
	  }
	  model.addAttribute("errorMessage", errorMessage);
	  return "addProduct";
	  }
	  
	  @RequestMapping(value = { "/deleteProduct/{id}" }, method = RequestMethod.GET)
	  public String deleteProduct(@PathVariable("id") long id) {
		  Product p=null;
		  for(Product product:products) {
			  if(product.getId()==id) {
				  p=product;
			  }
		  }
		  
		  try {
			  products.remove(p); 
			  System.out.println("product was successfully deleted!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  return "redirect:/product-list";
		 
	 

	  }
	  
	  
	  @RequestMapping(value = { "/editProduct/{id}" }, method = RequestMethod.GET)
	  public String showUpdateProduct(@PathVariable("id") long id,Model model) {
		  
		  Product p=null;
		  
		  for(Product product:products) {
			  if(product.getId()==id) {
				  p=product;
			  }
		  }
		  model.addAttribute("product",p);
		  return "updateProduct";
	  }
	  
	  @RequestMapping(value = { "/updateProduct/{id}" }, method = RequestMethod.POST)
	  public String updateProduct(@PathVariable("id") long id,Model model, @ModelAttribute("updateProduct") ProductForm productForm) {
		  
		  Product p=null;
		  
		  
		  for(Product product:products) {
			 if(product.getId()==id) {
			  p=product;
			 }
		 }
		  
		  
		 if (productForm.getCode() != null && productForm.getCode().length() > 0 
				  && productForm.getName() != null && productForm.getName().length() > 0 && productForm.getPrice() >0.0) {
				  //Product newProduct = new Product(++idCount, code, name,price, img);
				  
				   p.setCode(productForm.getCode());
				   p.setImg(productForm.getImg());
				   p.setName(productForm.getName());
				   p.setPrice(productForm.getPrice());
				  return "redirect:/product-list";
				  }
				  model.addAttribute("errorMessage", errorMessage);
				  return "updateProduct";
				  }
	  }
	  

	  
	  
	  


