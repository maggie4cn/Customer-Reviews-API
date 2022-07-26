package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.MongoDBRepository.MongoDBReviewRepository;
import com.udacity.course3.reviews.document.MongoDBReview;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;

    @Autowired
    private MongoDBReviewRepository mongoDBReviewRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                    @RequestBody Review review) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            review.setProduct(product);
            reviewRepository.save(review);

            MongoDBReview mongoDBReview = new MongoDBReview((review));
            mongoDBReviewRepository.save(mongoDBReview);

            return  new ResponseEntity<>(review, HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        List <MongoDBReview> mongoDBReviewList = new ArrayList<>();

        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            List<Review> mySQLReviewList = reviewRepository.findReviewByProduct(product);
            for(Review mySQLReview : mySQLReviewList){
                mongoDBReviewList.add(mongoDBReviewRepository.findByReviewId(mySQLReview.getReviewId()).get());
            }

            return new ResponseEntity<>(mongoDBReviewList, HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}