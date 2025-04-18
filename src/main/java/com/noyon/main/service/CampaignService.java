package com.noyon.main.service;

import org.springframework.stereotype.Service;

import com.noyon.main.model.Campaign;
import com.noyon.main.repository.CampaignRepository;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public Campaign createCampaign(Campaign campaign) {
        campaign.setCurrentAmount(0.0); // initial donation 0
        return campaignRepository.save(campaign);
    }

    public Campaign donate(Long id, double amount) {
        Campaign campaign = getCampaignById(id);
        campaign.setCurrentAmount(campaign.getCurrentAmount() + amount);
        return campaignRepository.save(campaign);
    }
}
