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
        User savedUser = userRepository.save(newUser);
        String accessToken = jwtUtil.createToken(savedUser.getId().toString());
        // 회원가입 시 해당 계정으로 관리자가 50000원 입금해주는 코드
        ethereumService.sendMoneyToTargetAddressFromAdmin(savedUser.getPublicKey(), 50000);
        return LoginResponse.UserInfo.build(newUser.getNickname(), false, newUser.getEmail(), accessToken);
    }

    public boolean chkEmail(String email) throws NotFoundException {
        // true는 중복 이메일 있는 것, false는 없는 것
        User user = userRepository.findByEmail(email);
        if(user!=null) return true;
        return false;
    }

    public boolean chkNickname(String nickname) throws NotFoundException {
        // true는 중복 닉 있는 것, false는 없는 것
        User user = userRepository.findByNickname(nickname);
        if(user!=null) return true;
        return false;
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

    public List<FundingListResponse> findFundingList(int state, Pageable pageable, HttpServletRequest req) throws NotValidateAccessToken, NotFoundException {
        String uuid = jwtUtil.getUserIdByHeaderAccessToken(req);
        User user = userRepository.findById(UUID.fromString(uuid)).get();
        if(user==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        List<OrderHistory> histories = historyRepository.findAllByUserId(UUID.fromString(uuid));
        List<FundingListResponse> output = new LinkedList<>();
        Set<String> set = new HashSet<>();

        for(OrderHistory o: histories) {
            UUID fundingId = o.getFunding().getId();
            if (set.contains(fundingId.toString())) continue;
            set.add(fundingId.toString());
        }

        List<String> list = new ArrayList<>(set);
        List<Funding> fundingList = fundingRepository.findAllById(state,pageable,list);
        for(Funding f: fundingList){
            LocalDateTime start = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            LocalDateTime end = f.getEndDate().toLocalDateTime();
            int day = (int) ChronoUnit.DAYS.between(start, end);

            List<FundThumbnail> imgList = fundThumbnailRepository.findByFundingId(f.getId().toString());
            FundThumbnail ff=new FundThumbnail();
            for(FundThumbnail img : imgList){
                if(img.getSequence()==0) ff=img;
            }
            FundingListResponse result = new FundingListResponse(f.getId().toString(), f.getTitle(), f.getCompany(),
                    ff.getImg(), f.getNowPrice(), (f.getNowPrice()*100/ f.getTargetPrice()),
                    f.getEndDate(),f.getDetailState(), day, f.getCategory().getType());
            output.add(result);
        }

        return output;
    }

    public boolean changeNick(nickRequest request, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        // true는 중복 이메일 있는 것, false는 없는 것
        if(this.chkNickname(request.getNickname())) {
            return false;
        }else{
            user.get().setNickname(request.getNickname());
            userRepository.save(user.get());
            return true;
        }
    }

    public CheckCoinResponse checkRemainCoins(HttpServletRequest req) throws NotValidateAccessToken {
        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        return CheckCoinResponse.build(ethereumService.getWonBalance(user.get().getPublicKey()));

    }

    public UUIDResponse findmyId(HttpServletRequest req) throws NotValidateAccessToken {
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        return new UUIDResponse(userId);
    }

    public EmailResponse findMyEmail(UUIDRequest input){
        UUID uuid = UUID.fromString(input.getUuid());
        User user = userRepository.findById(uuid).get();

        return new EmailResponse(user.getEmail());
    }
}
