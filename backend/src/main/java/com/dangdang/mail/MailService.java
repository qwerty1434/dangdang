package com.dangdang.mail;

import com.dangdang.member.dto.AuthNumResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class MailService {

    private final JavaMailSender emailSender;

    @Value("${from_email}")
    private String from_email = "";

    public AuthNumResponse sendPWMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String authKey="";
        try{
            message.setFrom(from_email);

            message.setTo(email);
            message.setSubject("가입 이메일 인증번호입니다.");

            //인증키 6자리 랜덤으로 생성 후 초기화
            authKey = Integer.toString( ThreadLocalRandom.current().nextInt(100000, 1000000) );

            message.setText("인증번호는 " + authKey + "입니다.\n인증번호를 입력해주세요.");
            emailSender.send(message);
            return new AuthNumResponse(authKey);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new AuthNumResponse("error");
    }
}