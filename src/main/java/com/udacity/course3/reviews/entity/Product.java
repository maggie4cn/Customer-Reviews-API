package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private int productID;

    @Column(name = "productName")
    private String productName;


    @OneToMany
    @JoinColumn(name = "productID")
    private List<Review> reviews = new ArrayList<>();

    public  int getProductID(){ return productID; }

    public void setProductID(int id){ this.productID = id; }

    public String getName(){ return productName; }

    public void setName(String name) { this.productName = name; }


    public List<Review> getReviews(){return reviews;}

    public void setReviews(Review review){
        reviews.add(review);
    }

}
