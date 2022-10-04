package com.dangdang.member.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.member.dto.*;
import com.dangdang.member.exception.InsufficientfundsException;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.service.BusinessService;
import com.dangdang.member.service.MakerService;
import com.dangdang.withdraw.dto.WithdrawFormResponse;
import com.dangdang.withdraw.service.WithdrawService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public boolean join(@RequestBody BusinessRequest maker, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken, IOException, ParseException {
        try{
            makerService.join(maker, req);
        } catch(Exception e){
            return false;
        }
        return true;
    }

    @PostMapping("/coin-app")
    @ApiOperation(value="코인 사용 신청")
    public void coinApplication(@RequestBody CoinAppRequest input, HttpServletRequest req) throws NotValidateAccessToken, InsufficientfundsException {
        withdrawService.coinApplication(input, req);
    }

    @GetMapping("/coin-app/list")
    @ApiOperation(value="코인 사용 신청 목록 조회", notes = "'/api/maker/coin-app/list?fundingId=45fe5566-3377-4c97-8e5a-7afeba50dafb' 형식으로 사용")
    public List<WithdrawFormResponse> applicationList(@RequestParam("fundingId") String fundingId) {
        return withdrawService.applicationList(fundingId);
    }

    @GetMapping("/pjt-info")
    @ApiOperation(value="판매자 페이지 메이커 프로젝트 정보 조회", notes = "'/api/maker/pjt-info?makerId=45fe5566-3377-4c97-8e5a-7afeba50dafb' 형식으로 사용")
    public MakerInfoResponse pjtInfo(@RequestParam("makerId") String makerId) throws NotFoundException {
        return makerService.pjtInfo(makerId);
    }

    @GetMapping("/company-info")
    @ApiOperation(value="판매자 페이지/ 마이 페이지 메이커 회사 정보 조회", notes = "'/api/maker/company-info?makerId=45fe5566-3377-4c97-8e5a-7afeba50dafb' 형식으로 사용")
    public MakerCompanyInfo makerInfo(@RequestParam("makerId") String makerId) throws NotFoundException {
        return makerService.makerInfo(makerId);
    }

    @GetMapping("/total-fund")
    @ApiOperation(value="메이커가 진행했던 펀딩의 총 모금액 조회", notes = "'/api/maker/total-fund?makerId=45fe5566-3377-4c97-8e5a-7afeba50dafb' 형식으로 사용")
    public TotalFundResponse totalFund(@RequestParam("makerId") String makerId) throws NotFoundException {
        return makerService.totalFund(makerId);
    }

    @GetMapping("/total-support")
    @ApiOperation(value="메이커 총 서포터 수 조회", notes = "'/api/maker/total-support?makerId=45fe5566-3377-4c97-8e5a-7afeba50dafb' 형식으로 사용")
    public TotalSupportResponse totalSupport(@RequestParam("makerId") String makerId) throws NotFoundException {
        return makerService.totalSupport(makerId);
    }

    @GetMapping("/funding-list")
    @ApiOperation(value="(판매자페이지) 메이커 펀딩 목록 조회", notes = "'/api/maker/funding-list?page=1&size=12&sort=startDate 형식으로 사용")
    public List<FundingListResponse> findFundingList(
            @RequestBody MakerFundingListRequest input, Pageable pageable, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        return makerService.findFundingList(input, pageable, req);
    }

    @PostMapping("/change/img")
    @ApiOperation(value="메이커 프로필 이미지 변경 및 생성(헤더, imgUrl 필요), 성공 시 return true, 실패 시 return false")
    public boolean changeImgFindByToken(@RequestBody MakerUrlRequest input, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        try{
            makerService.changeImg(input, req);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @PostMapping("/change/img-by-email")
    @ApiOperation(value="메이커 프로필 이미지 변경 및 생성(email, imgUrl 필요), 성공 시 return true, 실패 시 return false")
    public boolean changeImgFindByEmail(@RequestBody MakerEmailUrlRequest input) throws NotFoundException, NotValidateAccessToken {
        try{
            makerService.changeMyImg(input);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @PostMapping("/my-info")
    @ApiOperation(value="메이커 사업자 등록 번호, 회사 이름 return")
    public ResponseEntity<MakerCompanyResponse> findCompanyInfo(HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        return ResponseEntity.ok().body(makerService.findCompanyInfo(req));
    }
}