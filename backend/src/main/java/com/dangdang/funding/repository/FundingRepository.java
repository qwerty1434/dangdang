package com.dangdang.funding.repository;

import com.dangdang.funding.domain.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FundingRepository extends JpaRepository<Funding, UUID> {



}
