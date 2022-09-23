package com.dangdang.member.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.mail.MailService;
import com.dangdang.member.dto.*;
import com.dangdang.member.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@ApiOperation(value = "[회원] Member Controller")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

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
    public void login(@RequestBody LoginRequest input) throws NotFoundException {
        // 추후 토큰 return 예정
        userService.login(input);
    }

    @PostMapping("/logout")
    @ApiOperation(value="로그아웃")
    public void logout() throws NotFoundException {
        // 추후 토큰으로 user uuid 받아서 처리 예정
        userService.logout();
    }

    @PostMapping("/auth-email")
    @ApiOperation(value="인증번호 메일 전송")
    public void authMail(@RequestBody EmailRequest input) {
        mailService.sendPWMail(input.getEmail());
    }

    @PatchMapping("/change/pw")
    @ApiOperation(value="일반 회원 비밀번호 재설정")
    public void renewPW(@RequestBody pwRequest input) throws NotFoundException {
        userService.renewPW(input.getPassword());
    }

    @GetMapping("/check/pw")
    @ApiOperation(value="일반 회원 비밀번호 재설정 전 확인")
    public boolean chechPW(@RequestBody pwRequest input) throws NotFoundException {
        return userService.checkPW(input.getPassword());
    }

    @PatchMapping("/change/nick")
    @ApiOperation(value="회원 닉네임 변경")
    public void renewNick(@RequestBody nickRequest input) throws NotFoundException {
        userService.renewNick(input.getNickname());
    }
}
