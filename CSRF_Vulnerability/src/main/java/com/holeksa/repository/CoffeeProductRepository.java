package com.holeksa.repository;

import com.holeksa.model.CoffeeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bourbonkid on 11.01.17.
 */
public interface CoffeeProductRepository extends JpaRepository<CoffeeProduct, Long> {

        List<CoffeeProduct> findByName(String name);

        //This is the same query as above. Only to show, that you can create your own parametrized query
        @Query("SELECT cp FROM CoffeeProduct cp where cp.name = ?1")
        List<CoffeeProduct> findCustomQuery(String cofee);
}
