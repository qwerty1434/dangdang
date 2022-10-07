package com.dangdang.withdraw.repository;

import com.dangdang.withdraw.domain.WithdrawForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WithdrawRepository extends JpaRepository<WithdrawForm, UUID> {

    @Query(value = "SELECT * FROM withdraw_form WHERE funding_id=:fundingId", nativeQuery = true)
    List<WithdrawForm> findAllByFundingId(@Param("fundingId") String fundingId);

}
