package com.dangdang.funding.repository;

import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.dto.FundingSimpleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FundingRepository extends JpaRepository<Funding, UUID> {

    @Query(value = "SELECT * FROM funding WHERE (`maker_id`=:makerId  and `state`=:state) order by `start_date` ", nativeQuery = true)
    List<Funding> findByMakerIdAndState(@Param("makerId") String makerId, @Param("state") int state);

    @Query(value = "SELECT * FROM funding WHERE (`category_id`=:categoryId and `state`=:state) order by `start_date` ", nativeQuery = true)
    List<Funding> findByCategoryAndState(@Param("categoryId") String categoryId, @Param("state") int state);

    List<Funding> findByState(int state);

    @Query(value = "SELECT * FROM funding WHERE maker_id=:makerId and state>=1 order by state", nativeQuery = true)
    List<Funding> findByMakerId(@Param("makerId") String makerId);
}
