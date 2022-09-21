package com.dangdang.member.controller;

import com.dangdang.member.dto.CoinAppRequest;
import com.dangdang.member.dto.MakerJoinRequest;
import com.dangdang.member.service.MakerService;
import com.dangdang.withdraw.domain.WithdrawForm;
import com.dangdang.withdraw.dto.WithdrawFormResponse;
import com.dangdang.withdraw.service.WithdrawService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maker")
@RequiredArgsConstructor
@ApiOperation(value = "[회원] Maker Controller")
public class MakerController {

    private final MakerService makerService;
    private final WithdrawService withdrawService;

    @PostMapping("/join")
    @ApiOperation(value="메이커 등록")
    public void join(@RequestBody MakerJoinRequest maker) {
        makerService.join(maker);
    }

    @PostMapping("/coin-app")
    @ApiOperation(value="코인 사용 신청")
    public void coinApplication(@RequestBody CoinAppRequest input) {
        withdrawService.coinApplication(input);
    }

    @GetMapping("/coin-app/list")
    @ApiOperation(value="코인 사용 신청 목록 조회", notes = "'/coin-app/list?fundingId=45fe5566-3377-4c97-8e5a-7afeba50dafb' 형식으로 사용")
    public List<WithdrawFormResponse> applicationList(@RequestParam("fundingId") String fundingId) {
        return withdrawService.applicationList(fundingId);
    }

}
