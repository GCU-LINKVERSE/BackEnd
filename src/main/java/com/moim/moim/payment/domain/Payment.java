package com.moim.moim.payment.domain;


import com.moim.moim.global.Status;
import com.moim.moim.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private User payer;  // 유저 엔티티와 연결 (가정)

    private int amount;

    @Enumerated(EnumType.STRING)
    private Status paymentStatus;

}
