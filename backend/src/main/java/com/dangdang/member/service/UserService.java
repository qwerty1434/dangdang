package com.dangdang.member.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.blockchain.service.EthereumService;
import com.dangdang.member.domain.Authority;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.LoginRequest;
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

    private final EthereumService ethereumService;

    public void join(UserJoinRequest user) throws NotFoundException {

        chkEmail(user.getEmail());
        chkNickname(user.getNickname());

        User newUser = new User(user.getEmail(),user.getPassword(),user.getNickname(),ethereumService.createAccount());

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

    public void login(LoginRequest input) throws NotFoundException {
        User user = userRepository.findByEmail(input.getEmail());
        if(user==null)
            throw new NotFoundException("이메일을 다시 입력해주세요.");

        if(user.getPassword().equals(input.getPassword())){
            // 토큰 생성. 지금은 일단 에러 안나면 로그인
        }
        else {
            throw new NotFoundException("비밀번호를 다시 입력해주세요.");
        }
    }
    
    public void logout() throws NotFoundException {
        // 토큰으로 user uuid 찾기
        String uuid = "";

        User user = userRepository.findById(UUID.fromString(uuid)).get();
        if(user==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        user.setRefreshToken("");
        userRepository.save(user);
    }

    public void renewPW(String password) throws NotFoundException {
        // 토큰으로 user uuid 찾기
        String uuid = "";

        User user = userRepository.findById(UUID.fromString(uuid)).get();
        if(user==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        user.setPassword(password);
        userRepository.save(user);
    }

    public boolean checkPW(String password) throws NotFoundException {
        // 토큰으로 user uuid 찾기
        String uuid = "";

        User user = userRepository.findById(UUID.fromString(uuid)).get();
        if(user==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        if(!user.getPassword().equals(password)) return false;

        return true;
    }

    public void renewNick(String nickname) throws NotFoundException {
        // 토큰으로 user uuid 찾기
        String uuid = "";

        User user = userRepository.findById(UUID.fromString(uuid)).get();
        if(user==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        user.setNickname(nickname);
        userRepository.save(user);
    }
}
