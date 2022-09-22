package com.dangdang.category.repository;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value="select type from category", nativeQuery = true)
    List<String> findAllTypes();

    Category findByType(String type) throws NotFoundException;


}
