

package com.noyon.main.controller;

import org.springframework.web.bind.annotation.*;

import com.noyon.main.model.Donation;
import com.noyon.main.service.DonationService;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/{campaignId}")
    public Donation donateToCampaign(@PathVariable Long campaignId, @RequestParam double amount) {
        return donationService.donate(campaignId, amount);
    }
}