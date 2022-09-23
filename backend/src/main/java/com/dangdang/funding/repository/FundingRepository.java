package com.dangdang.funding.repository;

import com.dangdang.funding.domain.Funding;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface FundingRepository extends JpaRepository<Funding, UUID> {

    List<Funding> findByMakerIdAndState(UUID makerId, int state, Pageable pageable);

//    @Query(value = "SELECT * FROM funding WHERE (`category_id`=:categoryId and `state`=:state) order by `start_date` ", nativeQuery = true)
//    List<Funding> findByCategoryAndState(@Param("categoryId") String categoryId, @Param("state") int state);

    List<Funding> findByState(int state, Pageable pageable);

    List<Funding> findByCategoryIdAndState(UUID categoryId, int state, Pageable pageable);
//
//    @Query(value = "SELECT * FROM funding WHERE state=:state", nativeQuery = true)
//    List<Funding> findByStateAndSort(@Param("state") int state, Pageable pageable);

    @Query(value = "SELECT * FROM funding WHERE `state`=:state order by (now_price/target_price) DESC  ", nativeQuery = true)
    List<Funding> findByPopular( @Param("state") int state, Pageable pageable);


    @Query(value = "SELECT * FROM funding WHERE maker_id=:makerId and state>=1 order by state", nativeQuery = true)
    List<Funding> findByMakerId(@Param("makerId") String makerId);

    @Query(value = "SELECT * FROM funding WHERE `detail_state`=:detailState and `start_date`=:now  ", nativeQuery = true)
    List<Funding> findByDetailStateAndStartDate(@Param("detailState") String detailState, @Param("now") Timestamp now);

    @Query(value = "SELECT * FROM funding WHERE `detail_state`=:detailState and `end_date`=:now  ", nativeQuery = true)
    List<Funding> findByDetailStateAndEndDate(@Param("detailState") String detailState, @Param("now") Timestamp now);

    @Query(value = "SELECT * FROM funding WHERE `state`=:state and `title` like %:keyword% order by `start_date` Desc ", nativeQuery = true)
    List<Funding> findByKeywordAndState(String keyword, int state, Pageable pageable);
}
