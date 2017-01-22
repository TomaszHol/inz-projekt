package com.holeksa.repository;

import com.holeksa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bourbonkid on 18.01.17.
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
