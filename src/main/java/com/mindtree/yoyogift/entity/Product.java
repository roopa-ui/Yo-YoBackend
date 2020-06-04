package com.mindtree.yoyogift.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;

	private String productName;
	private int yoyoPrice;
	private String description;
	private String manufacturer;
	private String productImage;

	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<FeedBack> feedbacks;

	public Product(int productId, String productName, int yoyoPrice, String description, String manufacturer,

			String productImage, Category category, List<FeedBack> feedbacks) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.yoyoPrice = yoyoPrice;
		this.description = description;
		this.manufacturer = manufacturer;
		this.productImage = productImage;
		this.category = category;
		this.feedbacks = feedbacks;
	}

	public Product() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getYoyoPrice() {
		return yoyoPrice;
	}

	public void setYoyoPrice(int yoyoPrice) {
		this.yoyoPrice = yoyoPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getmanufacturer() {
		return manufacturer;
	}

	public void setmanufacturer(String manufacturer) {
		this.manufacturer = manufacturer;

	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<FeedBack> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedBack> feedbacks) {
		this.feedbacks = feedbacks;
	}

}
