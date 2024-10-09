package com.moim.moim.user.domain;

import com.moim.moim.friend.domain.Friend;
import com.moim.moim.global.BaseTime;
import com.moim.moim.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String cardNum;

    @Column(nullable = false)
    private String valiMon;

    @Column(nullable = false)
    private String valiYear;

    @OneToMany(mappedBy = "users")
    private List<Friend> friendList = new ArrayList<>();

    public static User from(UserRequestDto dto){
        return User.builder()
                .phoneNumber(dto.getPhoneNumber())
                .bank(dto.getBank())
                .account(dto.getAccount())
                .cardNum(dto.getCardNum())
                .valiMon(dto.getValiMon())
                .valiYear(dto.getValiYear())
                .build();

    }

    public void update(UserRequestDto request){
        this.phoneNumber = request.getPhoneNumber();
        this.bank = request.getBank();
        this.account = request.getAccount();
        this.cardNum = request.getCardNum();
        this.valiMon = request.getValiMon();
        this.valiYear = request.getValiYear();
    }
}
