package com.moim.moim.user.domain;

import com.moim.moim.global.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class User extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String cardNum;

    @Column(nullable = false)
    private String valiNum;

    @Column(nullable = false)
    private String valiYear;

}
