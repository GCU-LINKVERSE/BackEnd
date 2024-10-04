package com.moim.moim.user.dto;

import com.moim.moim.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponseDto {
    private Long id;

    private String phoneNumber;

    private String account;

    private String cardNum;

    private String valiNum;

    private String valiYear;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .account(user.getAccount())
                .cardNum(user.getCardNum())
                .valiNum(user.getValiNum())
                .valiYear(user.getValiYear())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
