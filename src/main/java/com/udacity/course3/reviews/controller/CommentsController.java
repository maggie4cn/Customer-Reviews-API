package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.MongoDBRepository.MongoDBReviewRepository;
import com.udacity.course3.reviews.document.MongoDBComment;
import com.udacity.course3.reviews.document.MongoDBReview;
import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.management.relation.RoleUnresolvedList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    private CommentRepository commentRepository;

    private ReviewRepository reviewRepository;

    @Autowired
    private MongoDBReviewRepository mongoDBReviewRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                    @RequestBody Comment comment) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            comment.setReview(review);
            commentRepository.save(comment);

            //Save to MongoDB review repository
            MongoDBComment mongoDBComment = new MongoDBComment(comment.getCommentID(), comment.getCommentContent());
            Optional<MongoDBReview> optionalMongoDBReview = mongoDBReviewRepository.findByReviewId(reviewId);
            if(optionalMongoDBReview.isPresent()){
                MongoDBReview mongoDBReview = optionalMongoDBReview.get();
                mongoDBReview.addMongoDBComment(mongoDBComment);
                mongoDBReviewRepository.save(mongoDBReview);
            }

            return  new ResponseEntity<>(comment, HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            List<Comment> commentList = review.getComments();
            return  new ResponseEntity<>(commentList, HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}