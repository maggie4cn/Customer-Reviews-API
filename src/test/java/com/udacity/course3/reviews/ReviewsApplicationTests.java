package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ReviewRepository reviewRepository;


	@BeforeTransaction
	public void setUp(){
		Product product1 = new Product();
		product1.setName("Apple MacBook Pro");
		productRepository.save(product1);

		Review review1 = new Review();
		review1.setReviewContent("Excellent battery life");
		review1.setProduct(product1);
		reviewRepository.save(review1);

		Comment comment1 = new Comment();
		comment1.setCommentContent("It delivers impressive performance");
		comment1.setReview(review1);
		commentRepository.save(comment1);

	}

	@Test
	public void injectedComponentsNotNull(){
		assertThat(dataSource).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(productRepository).isNotNull();
		assertThat(reviewRepository).isNotNull();
		assertThat(commentRepository).isNotNull();
	}

	@Test
	public void testCreateProduct(){
		Product product2 = new Product();
		product2.setName("Lenovo ThinkPad X1");
		productRepository.save(product2);
		Integer proId = product2.getProductID();
		Product savedProduct =productRepository.findById(proId).get();
		assertThat(savedProduct).isEqualTo(product2);
	}

	@Test
	public void testFindProductById(){
		Optional<Product> productOptional = productRepository.findById(1);
		assertThat(productOptional.get().getName()).isEqualTo("Apple MacBook Pro");
	}

	@Test
	public void testListProducts(){
		List<Product> productList = (List<Product>)productRepository.findAll();
		assertThat(productList.size()).isGreaterThan(1);
	}

	@Test
	public void testCreateReview(){
		Product firstProduct = productRepository.findById(1).get();
		Review review = new Review();
		review.setReviewContent("Good performance");
		review.setProduct(firstProduct);
		reviewRepository.save(review);
		Integer reviewId = review.getReviewId();
		Review savedReview = reviewRepository.findById(reviewId).get();
		assertThat(savedReview).isEqualTo(review);
	}

	@Test
	public void testCreateComment(){
		Review secondReview = new Review();
		secondReview.setReviewContent("Stellar overall");
		Product secondProduct = productRepository.findById(2).get();
		secondReview.setProduct(secondProduct);
		reviewRepository.save(secondReview);
		Comment comment = new Comment();
		comment.setCommentContent("I agree");
		comment.setReview(secondReview);
		commentRepository.save(comment);
		Integer commentId = comment.getCommentID();
		Comment savedComment = commentRepository.findById(commentId).get();
		assertThat(savedComment).isEqualTo(comment);
	}
}