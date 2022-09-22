package com.dangdang.tag.repository;

import com.dangdang.funding.domain.Funding;
import com.dangdang.tag.domain.FundingTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FundingTagRepostiory extends JpaRepository<FundingTag, UUID>{

    @Query(value="select funding_id from funding_tag where tag_id = :tagId", nativeQuery = true)
    List<Funding> findAllByTagId(@Param("tagId") String tagId);
}
