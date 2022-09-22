package com.dangdang.tag.repository;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {

    List<Tag> findAllByName(@Param("name") String name);

    @Query(value="select * from tag where name LIKE %:name%", nativeQuery = true)
    List<Tag> findAllByNameContainTag(@Param("name") String name);

    Tag findByName(@Param("name") String name);

}
