package com.noyon.main.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.noyon.main.dto.PaymentRequest;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/initiate")
    public ResponseEntity<?> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            String storeId = "noyon6803c527e9827"; // your sandbox store ID
            String storePassword = "noyon6803c527e9827@ssl"; // your sandbox password

            String paymentUrl = "https://sandbox.sslcommerz.com/gwprocess/v3/api.php";

            // Prepare request parameters
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("store_id", storeId);
            params.add("store_passwd", storePassword);
            params.add("total_amount", String.valueOf(paymentRequest.getAmount()));
            params.add("currency", "BDT");
            params.add("tran_id", UUID.randomUUID().toString()); // unique transaction ID
            params.add("success_url", "http://localhost:5173/payment/success");
            params.add("fail_url", "http://localhost:5173/payment/fail");
            params.add("cancel_url", "http://localhost:5173/payment/cancel");
            params.add("cus_name", paymentRequest.getCustomerName());
            params.add("cus_email", paymentRequest.getCustomerEmail());
            params.add("cus_phone", paymentRequest.getCustomerPhone());

            // Setup HTTP request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(paymentUrl, entity, Map.class);

            // Check and extract Gateway URL
            Map<String, Object> body = response.getBody();
            if (body != null && body.containsKey("GatewayPageURL")) {
                String gatewayUrl = (String) body.get("GatewayPageURL");
                return ResponseEntity.ok(Collections.singletonMap("gatewayUrl", gatewayUrl));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Gateway URL not received from SSLCommerz");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Payment initiation failed");
        }
    }
}
