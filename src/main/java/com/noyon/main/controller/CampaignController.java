package com.noyon.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.noyon.main.model.Campaign;
import com.noyon.main.service.CampaignService;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin(origins = "http://localhost:3000")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @PostMapping
    public Campaign create(@RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @GetMapping
    public List<Campaign> getAll() {
        return campaignService.getAllCampaigns();
    }
}