package com.dangdang.member.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.mail.MailService;
import com.dangdang.member.dto.*;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.exception.NotValidateRefreshToken;
import com.dangdang.member.service.UserService;
import com.dangdang.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@ApiOperation(value = "[회원] Member Controller")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    private final JWTUtil jwtUtil;


    @PostMapping("/join")
    @ApiOperation(value="당당펀딩(일반) 회원가입", notes = "가입 축하 메일은 현재 안갑니다.")
    public ResponseEntity<LoginResponse.UserInfo> join(@RequestBody UserJoinRequest user, HttpServletResponse res) throws NotFoundException {
        LoginResponse.UserInfo userInfo = userService.join(user);
        String refreshToken = userService.refreshToken(userInfo.getEmail());
        res.addHeader("Set-Cookie", "refreshToken="+refreshToken+"; path=/; MaxAge=7 * 24 * 60 * 60; SameSite=Lax; HttpOnly");
        return ResponseEntity.ok().body(userInfo);
    }

    @PostMapping("/check/email")
    @ApiOperation(value="이메일 중복 및 유효 검사")
    public boolean chkEmail(@RequestBody StringRequest inputDto) throws NotFoundException {
        try{
            boolean flag = userService.chkEmail(inputDto.getStr());
            return flag;
        } catch (NotFoundException e){
            return false;
        }
    }

    @PostMapping("/check/nick")
    @ApiOperation(value="닉네임 중복 검사")
    public boolean chkNickname(@RequestBody StringRequest inputDto) throws NotFoundException {
        // true는 중복 닉 있는 것, false는 없는 것, exception도 없는 것
        try{
            boolean flag = userService.chkNickname(inputDto.getStr());
            return flag;
        } catch (NotFoundException e){
            return false;
        }
    }

    @PostMapping("/login")
    @ApiOperation(value="로그인")
    public ResponseEntity<LoginResponse.UserInfo> login(@RequestBody LoginRequest input, HttpServletResponse res) throws NotFoundException {
        // 추후 토큰 return 예정
        LoginResponse.UserInfo userInfo= userService.login(input);
        String refreshToken = userService.refreshToken(userInfo.getEmail());
        res.addHeader("Set-Cookie", "refreshToken="+refreshToken+"; path=/; MaxAge=7 * 24 * 60 * 60; SameSite=Lax; HttpOnly");
        return ResponseEntity.ok().body(userInfo);
    }


    @PostMapping("/logout")
    @ApiOperation(value="로그아웃")
    public void logout(HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // 추후 토큰으로 user uuid 받아서 처리 예정
        userService.logout(req);
    }

    @PostMapping("/auth-email")
    @ApiOperation(value="인증번호 메일 전송")
    public AuthNumResponse authMail(@RequestBody EmailRequest input) {
        return mailService.sendPWMail(input.getEmail());
    }

    @PatchMapping("/change/pw")
    @ApiOperation(value="일반 회원 비밀번호 재설정")
    public void renewPW(@RequestBody pwRequest input, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        userService.renewPW(input.getPassword(), req);
    }

    @GetMapping("/check/pw")
    @ApiOperation(value="일반 회원 비밀번호 재설정 전 확인")
    public boolean chechPW(@RequestBody pwRequest input, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        return userService.checkPW(input.getPassword(), req);
    }

    @PatchMapping("/retoken")
    @ApiOperation(value = "토큰 재발급", notes = "accessToken은 body로 refrestToken은 쿠키로 전달받아 토큰 유효성 확인 후 jwt 토큰 재발급")
    public ResponseEntity<TokenResponse.NewToken> reIssue(@RequestBody TokenRequest.Create request, HttpServletResponse res, @CookieValue(name="refreshToken") String refresh, HttpServletRequest req) throws NotValidateAccessToken, NotValidateRefreshToken {
        TokenResponse.NewToken response = userService.getNewToken(request, refresh);
        String refreshToken = userService.refreshToken(response.getEmail());
        res.addHeader("Set-Cookie", "refreshToken="+refreshToken+"; path=/; MaxAge=7 * 24 * 60 * 60; SameSite=Lax; HttpOnly");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/funding-list")
    @ApiOperation(value="(마이페이지) 내가 참여한 목록 조회", notes = "'/api/user/funding-list?state=0&page=1&size=12&sort=start_date 형식으로 사용")
    public List<FundingListResponse> findFundingList(int state, Pageable pageable, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        return userService.findFundingList(state, pageable, req);
    }

    @PatchMapping("/change/nick")
    @ApiOperation(value = "유저 닉네임 변경", notes = "유저의 닉네임을 변경하는 api로 중복되는 닉네임이 있을 시 false로 반환")
    public boolean changeNick(@RequestBody nickRequest request, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        return userService.changeNick(request, req);
    }

    @GetMapping("/checkcoin")
    @ApiOperation(value = "유저의 남은 잔액 확인", notes = "유저 마이페이지에 남은 잔액 표시")
    public ResponseEntity<CheckCoinResponse> checkRemainCoin(HttpServletRequest req) throws NotValidateAccessToken {
        return ResponseEntity.ok().body(userService.checkRemainCoins(req));
    }

    @GetMapping("/find/my-id")
    @ApiOperation(value="토큰에서 내 uuid 가져오기. 필요할거같네용. 토큰만 보내주세용")
    public ResponseEntity<UUIDResponse> findFundingList(HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        return ResponseEntity.ok().body(userService.findmyId(req));
    }

    @PostMapping("/find/my-email")
    @ApiOperation(value="uuid에서 email 가져오기")
    public ResponseEntity<EmailResponse> findEmail(@RequestBody UUIDRequest input) throws NotFoundException, NotValidateAccessToken {
        return ResponseEntity.ok().body(userService.findMyEmail(input));
    }
}
