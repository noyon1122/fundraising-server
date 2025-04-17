package com.noyon.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noyon.main.model.Campaign;

@Repository

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}
