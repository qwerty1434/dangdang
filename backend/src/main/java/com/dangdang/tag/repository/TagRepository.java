package com.dangdang.tag.repository;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {

    void findAllByName(@Param("name") String name) throws NotFoundException;

    @Query(value="select * from tag where name like :name", nativeQuery = true)
    List<Tag> findAllByNameContainTag(@Param("name") String name);

}
