package com.noyon.main.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noyon.main.model.Campaign;
import com.noyon.main.repository.CampaignRepository;

import java.util.List;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign createCampaign(Campaign campaign) {
        campaign.setStatus("PENDING");
        campaign.setRaisedAmount(0.0);
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }
}