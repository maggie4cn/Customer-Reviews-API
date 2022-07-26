package com.udacity.course3.reviews.document;

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document("reviews")
public class MongoDBReview {
    @Id
    private String _id;

    private int reviewId;

    private String reviewContent;

    private int reviewProductId;

    private List<MongoDBComment> commentList;

    public MongoDBReview() {
    }

    public MongoDBReview(int reviewId, String reviewContent){
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.commentList = new ArrayList<>();
    }

    public MongoDBReview(Review review){
        this.reviewId = review.getReviewId();
        this.reviewContent = review.getReviewContent();
        this.reviewProductId = review.getProduct().getProductID();
        this.commentList = new ArrayList<>();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getReviewedProductId() {
        return reviewProductId;
    }

    public void setReviewedProductId(Integer reviewProductId) { this.reviewProductId = reviewProductId;}

    public List<MongoDBComment> getCommentList(){ return  commentList;}

    public void setCommentList(List<MongoDBComment> commentList) {
        this.commentList = commentList;
    }

    public void addMongoDBComment(MongoDBComment mongoDBComment) {
        this.commentList.add(mongoDBComment);
    }

}
