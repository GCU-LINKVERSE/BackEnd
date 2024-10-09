package com.moim.moim.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentDto {

    // private Long id;
    private LocalDate date;
    private Long payerId;
    private int amount;
    private String paymentStatus;

}