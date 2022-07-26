package com.udacity.course3.reviews.document;

public class MongoDBComment {
    private Integer commentId;
    private String commentContent;

    public MongoDBComment() {
    }

    public MongoDBComment(Integer commentId, String commentText) {
        this.commentId = commentId;
        this.commentContent = commentText;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
