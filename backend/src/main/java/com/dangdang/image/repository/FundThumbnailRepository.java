package com.dangdang.image.repository;

import com.dangdang.image.domain.BodyImage;
import com.dangdang.image.domain.FundThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FundThumbnailRepository extends JpaRepository<FundThumbnail, UUID> {

    @Query(value = "SELECT * FROM fund_thumbnail WHERE funding_id=:fundingId  and `sequence`=:sequence ", nativeQuery = true)
    FundThumbnail findByFundingIdAndSequence(@Param("fundingId")String fundingId, @Param("sequence") int sequence);

    @Query(value = "SELECT * FROM fund_thumbnail WHERE `funding_id`=:fundingId  order by sequence ", nativeQuery = true)
    List<FundThumbnail> findByFundingId(@Param("fundingId") String fundingId);
}
