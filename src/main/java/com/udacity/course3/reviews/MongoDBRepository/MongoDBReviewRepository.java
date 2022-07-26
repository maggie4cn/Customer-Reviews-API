package com.udacity.course3.reviews.MongoDBRepository;

import com.udacity.course3.reviews.document.MongoDBReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoDBReviewRepository extends MongoRepository<MongoDBReview, String>{
    Optional<MongoDBReview> findByReviewId(int reviewId);

    List<MongoDBReview> findByReviewedProductId(int reviewedProductId);
}
