package com.dangdang.member.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.blockchain.service.EthereumService;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.image.domain.FundThumbnail;
import com.dangdang.image.repository.FundThumbnailRepository;
import com.dangdang.member.domain.Authority;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.*;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.exception.NotValidateRefreshToken;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.order.domain.OrderHistory;
import com.dangdang.order.repository.OrderHistoryRepository;
import com.dangdang.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    private final OrderHistoryRepository historyRepository;

    private final FundingRepository fundingRepository;

    private final FundThumbnailRepository fundThumbnailRepository;

    private final JWTUtil jwtUtil;


    public LoginResponse.UserInfo join(UserJoinRequest user) throws NotFoundException {

        chkEmail(user.getEmail());
        chkNickname(user.getNickname());

        User newUser = new User(user.getEmail(),user.getPassword(),user.getNickname(),ethereumService.createAccount());

        newUser.setAuthority(Authority.ROLE_USER);
        UUID userId = userRepository.save(newUser).getId();
        String accessToken = jwtUtil.createToken(userId.toString());
        return LoginResponse.UserInfo.build(newUser.getNickname(), false, newUser.getEmail(), accessToken);
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

    public List<FundingListResponse> findFundingList(String state, Pageable pageable, HttpServletRequest req) throws NotValidateAccessToken, NotFoundException {
        String uuid = jwtUtil.getUserIdByHeaderAccessToken(req);
        User user = userRepository.findById(UUID.fromString(uuid)).get();
        if(user==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        List<OrderHistory> histories = historyRepository.findAllByUserId(UUID.fromString(uuid));
        List<FundingListResponse> output = new LinkedList<>();
        Set<String> set = new HashSet<>();

        for(OrderHistory o: histories){
            UUID fundingId = o.getFunding().getId();
            if(set.contains(fundingId.toString())) continue;
            set.add(fundingId.toString());

            Funding f = fundingRepository.findById(fundingId).get();
            LocalDateTime start = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            LocalDateTime end = f.getEndDate().toLocalDateTime();
            int day = (int) ChronoUnit.DAYS.between(start, end);

            List<FundThumbnail> imgList = fundThumbnailRepository.findByFundingId(f.getId().toString());
            FundThumbnail ff=new FundThumbnail();
            for(FundThumbnail img : imgList){
                if(img.getSequence()==0) ff=img;
            }
            FundingListResponse result = new FundingListResponse(f.getId().toString(), f.getTitle(), f.getCompany(),
                    ff.getImg(), f.getNowPrice(), (1.0*f.getNowPrice()/ f.getTargetPrice()),
                    f.getEndDate(),f.getDetailState(), day, f.getCategory().getType());
            output.add(result);
        }
        return output;
    }
}
