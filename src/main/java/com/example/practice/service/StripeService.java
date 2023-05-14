package com.example.practice.service;

import com.example.practice.model.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value(value = "${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;


    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }
}
