package com.dangdang.member.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.member.domain.Authority;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.UserJoinRequest;
import com.dangdang.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class UserService {

    private final UserRepository userRepository;

    public void join(UserJoinRequest user) throws NotFoundException {

        chkEmail(user.getEmail());
        chkNickname(user.getNickname());

        User newUser = new User(user.getEmail(),user.getPassword(),user.getNickname());

        // >>>>>>>>>> 이 때 지갑을 생성??

        newUser.setAuthority(Authority.ROLE_USER);
        userRepository.save(newUser);
    }

    public void chkEmail(String email) throws NotFoundException {
        if(userRepository.findByEmail(email) !=null) {
            throw new NotFoundException("이미 사용 중인 이메일입니다.");
        }
    }

    public void chkNickname(String nickname) throws NotFoundException {
        if(userRepository.findByNickname(nickname) !=null) {
            throw new NotFoundException("이미 사용 중인 닉네임입니다.");
        }
    }

    public void vaildUserId(String userId) throws NotFoundException {
        try{
            User user = userRepository.findById(UUID.fromString(userId)).get();
        } catch(Exception e){
            throw new NotFoundException("유효하지 않은 유저 정보입니다.");
        }
    }
}
