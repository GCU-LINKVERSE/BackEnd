package com.moim.moim.friend.dto;

import com.moim.moim.global.Status;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WaitingFriendListDto {
    private Long Id;
    private String phoneNumber;
    private Status status;
}
