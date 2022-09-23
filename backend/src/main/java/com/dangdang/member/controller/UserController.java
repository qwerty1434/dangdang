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
    public void join(@RequestBody UserJoinRequest user) throws NotFoundException {
        userService.join(user);
    }

    @PostMapping("/check/email")
    @ApiOperation(value="이메일 중복 및 유효 검사")
    public boolean chkEmail(@RequestBody StringRequest inputDto) throws NotFoundException {
        try{
            userService.chkEmail(inputDto.getStr());
            return false;
        } catch (NotFoundException e){
            return true;
        }
    }

    @PostMapping("/check/nick")
    @ApiOperation(value="닉네임 중복 검사")
    public boolean chkNickname(@RequestBody StringRequest inputDto) throws NotFoundException {
        try{
            userService.chkNickname(inputDto.getStr());
            return false;
        } catch (NotFoundException e){
            return true;
        }
    }

    @PostMapping("/login")
    @ApiOperation(value="로그인")
    public ResponseEntity<LoginResponse.UserInfo> login(@RequestBody LoginRequest input, HttpServletResponse res) throws NotFoundException {
        // 추후 토큰 return 예정
        LoginResponse.UserInfo userInfo= userService.login(input);
        String refreshToken = userService.refreshToken(userInfo.getEmail());
        res.addHeader("Set-Cookie", "refreshToken="+refreshToken+"; path=/; MaxAge=7 * 24 * 60 * 60; SameSite=Lax; HttpOnly");
        return ResponseEntity.ok().body(userService.login(input));
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
    @ApiOperation(value="(마이페이지) 내가 참여한 목록 조회", notes = "'/api/user/funding-list?state=0&page=1&size=12&sort=startDate 형식으로 사용")
    public List<FundingListResponse> findFundingList(String state, Pageable pageable, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        return userService.findFundingList(state, pageable, req);
    }

}
