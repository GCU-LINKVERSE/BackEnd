package com.moim.moim.payment.application;

import com.moim.moim.global.Status;
import com.moim.moim.payment.domain.Payment;
import com.moim.moim.payment.domain.PaymentRepository;
import com.moim.moim.payment.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setDate(paymentDto.getDate());
        payment.setAmount(paymentDto.getAmount());
        // 필요한 payer 정보 처리 로직 추가
        payment.setPaymentStatus(Status.valueOf(paymentDto.getPaymentStatus()));
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDto(savedPayment);
    }

    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PaymentDto getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentDto convertToDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setDate(payment.getDate());
        dto.setAmount(payment.getAmount());
        dto.setPaymentStatus(payment.getPaymentStatus().toString());
        return dto;
    }
}