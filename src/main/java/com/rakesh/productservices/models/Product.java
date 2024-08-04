package com.rakesh.productservices.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private Category category;
//    private int qty;
//    private int numberOfOrders;
}
