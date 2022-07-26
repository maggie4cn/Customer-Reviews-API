package com.udacity.course3.reviews.repository;
import com.udacity.course3.reviews.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {


}
