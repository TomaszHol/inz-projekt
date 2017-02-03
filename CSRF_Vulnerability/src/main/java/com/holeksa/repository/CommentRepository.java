package com.holeksa.repository;

import com.holeksa.model.CoffeeProduct;
import com.holeksa.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bourbonkid on 11.01.17.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
