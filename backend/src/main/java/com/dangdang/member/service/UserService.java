package com.dangdang.member.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.blockchain.service.EthereumService;
import com.dangdang.member.domain.Authority;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.*;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.exception.NotValidateRefreshToken;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
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

    private final JWTUtil jwtUtil;


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

    public LoginResponse.UserInfo login(LoginRequest input) throws NotFoundException {
        User user = userRepository.findByEmail(input.getEmail());
        if(user==null)
            throw new NotFoundException("이메일을 다시 입력해주세요.");

        if(user.getPassword().equals(input.getPassword())){
            //토큰 발행 후 유저 정보 반환
            String accessToken = jwtUtil.createToken(user.getId().toString());
            return LoginResponse.UserInfo.build(user.getNickname(), user.isAdmin(), user.getEmail(), accessToken);
        }
        else {
            throw new NotFoundException("비밀번호를 다시 입력해주세요.");
        }
    }
    
    public void logout(HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        if(!user.isPresent()) throw new NotFoundException("유효한 사용자가 아닙니다.");

        user.get().setRefreshToken("");
        userRepository.save(user.get());
    }

    public void renewPW(String password, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        if(!user.isPresent()) throw new NotFoundException("유효한 사용자가 아닙니다.");

        user.get().setPassword(password);
        userRepository.save(user.get());
    }

    public boolean checkPW(String password, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        if(!user.isPresent()) throw new NotFoundException("유효한 사용자가 아닙니다.");

        if(!user.get().getPassword().equals(password)) return false;

        return true;
    }

    public void renewNick(String nickname, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        if(!user.isPresent()) throw new NotFoundException("유효한 사용자가 아닙니다.");

        user.get().setNickname(nickname);
        userRepository.save(user.get());
    }

    public String refreshToken(String email) {
        String refreshToken = jwtUtil.createRefreshToken();
        User user = userRepository.findByEmail(email);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        return refreshToken;
    }

    public TokenResponse.NewToken getNewToken(TokenRequest.Create request, String refreshToken) throws NotValidateRefreshToken, NotValidateAccessToken {
        if(!jwtUtil.validateTokenExpiration(refreshToken)){
            throw new NotValidateRefreshToken();
        }
        Optional<User> user = this.findUserByToken(request.getAccessToken());
        if(!user.isPresent()){
            throw new NotValidateAccessToken();
        }
        if(!user.get().getRefreshToken().equals(refreshToken)){
            throw new NotValidateRefreshToken();
        }
        String accessToken = jwtUtil.createToken(user.get().getId().toString());
        return TokenResponse.NewToken.build(accessToken, user.get().getEmail());
    }

    public Optional<User> findUserByToken(String accessToken) throws NotValidateAccessToken {
        String userId = jwtUtil.getUserId(accessToken);
//        return userInfoRepository.findByUserId(userId);
        return userRepository.findById(UUID.fromString(userId));
    }
}
