package com.dangdang.funding.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.dto.FundingContent;
import com.dangdang.funding.dto.FundingRequest;
import com.dangdang.funding.dto.FundingResponse;
import com.dangdang.funding.service.FundingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funding")
@RequiredArgsConstructor
@ApiOperation(value = "[펀딩] Funding Controller")
public class FundingController {

    private final FundingService fundingService;

    @PostMapping("/regist")
    @ApiOperation(value="펀딩 신청하기", notes = "작성한 펀딩 신청서 기준으로 펀딩을 신청")
    public ResponseEntity<FundingResponse.Regist> regist(@RequestBody FundingRequest.Create request) throws NotFoundException {
        return ResponseEntity.ok().body(fundingService.RegistFunding(request));
    }

    @PostMapping("/temp-storage")
    @ApiOperation(value="펀딩 임시저장", notes = "작성 중인 펀딩신청서 임시 저장")
    public ResponseEntity<FundingResponse.Regist> tempStorage(@RequestBody FundingRequest.Create request) throws NotFoundException {
        return ResponseEntity.ok().body(fundingService.TempStorage(request));
    }

    @GetMapping("/maker/list")
    @ApiOperation(value="메이커가 개최한 펀딩 목록 조회", notes = "state= 0: 펀딩 승인전, 1: 펀딩 진행중, 2: 펀딩 완료")
    public ResponseEntity<FundingResponse.fundingList> makerFundingList(int state){
        return ResponseEntity.ok().body(fundingService.MakerFundingList(state));
    }



//    @PatchMapping("/modify")
//    @ApiOperation(value="펀딩 임시저장 내역 수정", notes = "작성 중인 펀딩신청서 수정")
//    public ResponseEntity<?> modify (@RequestBody FundingRequest.Create request) throws NotFoundException {
//        fundingService.FundingModify(request);
////        return ResponseEntity.ok().body(fundingService.TempStorage(request));
//        return null;
//    }



}
