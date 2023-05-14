package com.example.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequest {

    private String description;
    private long amount;
    private String currency = "USD";
    private String stripeEmail;
    private String stripeToken;
}
