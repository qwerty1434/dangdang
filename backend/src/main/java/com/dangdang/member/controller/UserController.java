package com.dangdang.member.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.member.dto.JoinInputDTO;
import com.dangdang.member.dto.ResultOutputDTO;
import com.dangdang.member.dto.StringInputDTO;
import com.dangdang.member.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@ApiOperation(value = "[회원] Member Controller")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @ApiOperation(value="당당펀딩(일반) 회원가입", notes = "가입 축하 메일은 현재 안갑니다.")
    public void join(@RequestBody JoinInputDTO user) throws NotFoundException {
        userService.join(user);
    }

    @PostMapping("/check/email")
    @ApiOperation(value="이메일 중복 및 유효 검사")
    public ResultOutputDTO chkEmail(@RequestBody StringInputDTO inputDto) throws NotFoundException {
        try{
            userService.chkEmail(inputDto.getStr());
            return new ResultOutputDTO(true);
        } catch (NotFoundException e){
            return new ResultOutputDTO(false);
        }
    }

    @PostMapping("/check/nick")
    @ApiOperation(value="닉네임 중복 검사")
    public ResultOutputDTO chkNickname(@RequestBody StringInputDTO inputDto) throws NotFoundException {
        try{
            userService.chkNickname(inputDto.getStr());
            return new ResultOutputDTO(true);
        } catch (NotFoundException e){
            return new ResultOutputDTO(false);
        }
    }
}
