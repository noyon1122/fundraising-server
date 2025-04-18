package com.noyon.main.service;

import org.springframework.stereotype.Service;

import com.noyon.main.model.Campaign;
import com.noyon.main.model.Donation;
import com.noyon.main.repository.CampaignRepository;
import com.noyon.main.repository.DonationRepository;

@Service

public class DonationService {

	 private final DonationRepository donationRepository;
	    private final CampaignRepository campaignRepository;

	    public DonationService(DonationRepository donationRepository, CampaignRepository campaignRepository) {
	        this.donationRepository = donationRepository;
	        this.campaignRepository = campaignRepository;
	    }

	    // Make a donation
	    public Donation donate(Long campaignId, double amount) {
	        Campaign campaign = campaignRepository.findById(campaignId)
	                .orElseThrow(() -> new RuntimeException("Campaign not found with ID: " + campaignId));

	        // Update campaign current amount
	        campaign.setCurrentAmount(campaign.getCurrentAmount() + amount);
	        campaignRepository.save(campaign);

	        // Save donation record
	        Donation donation = new Donation();
	        donation.setAmount(amount);
	        donation.setCampaign(campaign);

	        return donationRepository.save(donation);
	    }
	}

