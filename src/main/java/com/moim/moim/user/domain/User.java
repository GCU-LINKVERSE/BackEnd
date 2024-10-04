package com.moim.moim.user.domain;

import com.moim.moim.global.BaseTime;
import com.moim.moim.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
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

    public static User from(UserRequestDto dto){
        return User.builder()
                .phoneNumber(dto.getPhoneNumber())
                .bank(dto.getBank())
                .account(dto.getAccount())
                .cardNum(dto.getCardNum())
                .valiNum(dto.getValiMon())
                .valiYear(dto.getValiYear())
                .build();

    }
}
