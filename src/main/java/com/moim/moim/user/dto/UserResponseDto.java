package com.moim.moim.user.dto;

import com.moim.moim.friend.domain.Friend;
import com.moim.moim.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserResponseDto {
    private Long id;

    private String phoneNumber;

    private String account;

    private String cardNum;

    private String valiMon;

    private String valiYear;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

//    private List<Friend> friendList;

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .account(user.getAccount())
                .cardNum(user.getCardNum())
                .valiMon(user.getValiMon())
                .valiYear(user.getValiYear())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
//                .friendList(user.getFriendList())
                .build();
    }
}
