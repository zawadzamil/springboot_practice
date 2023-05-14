package com.example.practice.controller;

import com.example.practice.model.ChargeRequest;
import com.example.practice.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.RefundCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/stripe")
@CrossOrigin(origins = "*")
public class StripeController {
    private StripeService stripeService;

    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestBody ChargeRequest chargeRequest)
            throws StripeException {
        ChargeCreateParams params = new ChargeCreateParams.Builder()
                .setAmount(chargeRequest.getAmount() * 100)
                .setCurrency(chargeRequest.getCurrency())
                .setDescription("Example charge")
                .setSource(chargeRequest.getStripeToken())
                .build();
        Charge charge = Charge.create(params);
        return new ResponseEntity<>("Payment processed successfully", HttpStatus.OK);

    }

    @PostMapping("/refund")
    public ResponseEntity<String> refund(@RequestParam(value = "id", defaultValue = "") String id,
                                         @RequestParam(value = "amount") long amount) throws StripeException {
        RefundCreateParams params = new RefundCreateParams.Builder()
                .setCharge(id)
                .setAmount(amount * 100)
                .build();
        Refund refund = Refund.create(params);
        return new ResponseEntity<>("Refund processed successfully", HttpStatus.OK);
    }


}
