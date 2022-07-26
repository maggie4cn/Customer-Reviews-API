package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID")
    private int reviewID;

    @Column(name = "reviewContent")
    private String reviewContent;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @OneToMany
    @JoinColumn(name = "reviewID")
    private  List<Comment> comments;


    public Integer getReviewId() {
        return reviewID;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewID = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public void setProduct (Product product){
        this.product = product;
    }

    public Product getProduct(){
        return  product;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(Comment comment) {
        comments.add(comment);
    }
}
