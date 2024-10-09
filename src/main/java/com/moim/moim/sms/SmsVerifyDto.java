package com.moim.moim.sms;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SmsVerifyDto {
    @NotNull(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNum;
    @NotNull(message = "인증번호를 입력해주세요.")
    private String certificationCode;
}
