package com.example.pract4;

import java.math.BigDecimal;

public class Product {

    private String Id;
    private String title;
    private String description;
    private String category;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private int imageResource;


    public Product(String id, String title, String description, String category, BigDecimal originalPrice, BigDecimal discountedPrice, int imageResource) {
        Id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.imageResource = imageResource;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
