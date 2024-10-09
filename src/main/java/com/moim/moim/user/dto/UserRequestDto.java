package com.moim.moim.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotNull(message = "공백일 수 없습니다.")
    private String phoneNumber;

    @NotNull(message = "공백일 수 없습니다.")
    private String bank;

    @NotNull(message = "공백일 수 없습니다.")
    private String account;

    @NotNull(message = "공백일 수 없습니다.")
    private String cardNum;

    @NotNull(message = "공백일 수 없습니다.")
    private String valiMon;

    @NotNull(message = "공백일 수 없습니다.")
    private String valiYear;
}
