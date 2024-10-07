package com.moim.moim.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SmsService {

    private final SmsCertificationUtil smsCertificationUtil;
    private final SmsCertificationDao smsCertificationDao;

    public void SendSms(SmsRequestDto smsRequestDto) {
        String phoneNum = smsRequestDto.getPhoneNum(); // SmsrequestDto에서 전화번호를 가져온다.
        String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000); // 6자리 인증 코드를 랜덤으로 생성
        smsCertificationUtil.sendSms(phoneNum, certificationCode); // SMS 인증 유틸리티를 사용하여 SMS 발송
        smsCertificationDao.createSmsCertification(phoneNum, certificationCode);
    }

    public boolean verifyCode(SmsVerifyDto smsVerifyDto) {
        if (isVerify(smsVerifyDto.getPhoneNum(), smsVerifyDto.getCertificationCode())) { // 인증 코드 검증
            smsCertificationDao.removeSmsCertification(smsVerifyDto.getPhoneNum()); // 검증이 성공하면 Redis에서 인증 코드 삭제
            return true; // 인증 성공 반환
        } else {
            return false; // 인증 실패 반환
        }
    }

    // 전화번호와 인증 코드를 검증하는 메서드
    public boolean isVerify(String phoneNum, String certificationCode) {
        return smsCertificationDao.hasKey(phoneNum) && // 전화번호에 대한 키가 존재하고
                smsCertificationDao.getSmsCertification(phoneNum).equals(certificationCode); // 저장된 인증 코드와 입력된 인증 코드가 일치하는지 확인
    }
}