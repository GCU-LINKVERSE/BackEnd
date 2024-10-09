package com.moim.moim.sms;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsCertificationUtil {

    @Value("${COOLSMS_SEND_NUMBER}")
    private String senderNumber;

    @Value("${COOLSMS_API_KEY}")
    private String apiKey;

    @Value("${COOLSMS_API_SECRET}")
    private String apiSecret;

    DefaultMessageService messageService;

    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey,apiSecret, "https://api.coolsms.co.kr");
    }

    public SingleMessageSentResponse sendSms(String to, String verificationCode){
        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(to);
        message.setText("[모임Pay] 본인 확인 인증번호는 " + verificationCode + "입니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
        return response;
    }
}
