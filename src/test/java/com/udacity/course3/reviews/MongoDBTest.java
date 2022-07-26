package com.udacity.course3.reviews;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.udacity.course3.reviews.MongoDBRepository.MongoDBReviewRepository;
import com.udacity.course3.reviews.document.MongoDBComment;
import com.udacity.course3.reviews.document.MongoDBReview;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoDBTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private  MongoDBReviewRepository mongoDBReviewRepository;

    @Test
    public void CreateMongoDBReviewTest() {
        MongoDBReview mongoDBReview = new MongoDBReview();
        mongoDBReview.setReviewedProductId(1);
        mongoDBReview.setReviewContent("Excellent display");

        List<MongoDBComment> commentList = new ArrayList<>();
        commentList.add(new MongoDBComment(101, "I agree"));
        commentList.add(new MongoDBComment(102, "That's not true"));
        mongoDBReview.setCommentList(commentList);
        mongoDBReviewRepository.save(mongoDBReview);

        Optional<MongoDBReview> optionalMongoDBReview = mongoDBReviewRepository.findById(mongoDBReview.get_id());
        Assert.assertTrue(optionalMongoDBReview.isPresent());
        assertThat(optionalMongoDBReview.get().getReviewContent()).isEqualTo("Excellent display");
        List<MongoDBComment> savedCommentList = optionalMongoDBReview.get().getCommentList();
        assertThat(savedCommentList.get(0).getCommentContent()).isEqualTo("I agree");
        assertThat(savedCommentList.get(1).getCommentContent()).isEqualTo("That's not true");
    }

    @Test
    public void FindReviewByProductIdTest(){
        MongoDBReview mongoDBReview = new MongoDBReview();
        mongoDBReview.setReviewedProductId(10);
        mongoDBReview.setReviewContent("A nice product");
        List<MongoDBComment> commentList = new ArrayList<>();
        commentList.add(new MongoDBComment(201, "Overall good looks"));
        commentList.add(new MongoDBComment(202, "Long battery life"));
        mongoDBReview.setCommentList(commentList);
        mongoDBReviewRepository.save(mongoDBReview);

        List<MongoDBReview> reviewList = mongoDBReviewRepository.findByReviewedProductId(10);
        Assert.assertEquals(reviewList.size(), 1);
        Assert.assertEquals(reviewList.get(0).getCommentList().size(), 2);
        Assert.assertEquals(reviewList.get(0).getCommentList().get(0).getCommentContent(), "Overall good looks");
    }

}
