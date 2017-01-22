package com.holeksa.repository;

import com.holeksa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bourbonkid on 18.01.17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
