package com.dangdang.funding.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.funding.dto.FundingRequest;
import com.dangdang.funding.dto.FundingResponse;
import com.dangdang.funding.service.FundingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    @ApiOperation(value="메이커의 마이페이지에서 펀딩 목록 조회", notes = "state= 0: 펀딩 승인전, 1: 펀딩 진행중, 2: 펀딩 완료")
    public ResponseEntity<FundingResponse.fundingList> makerFundingList(int state, Pageable pageable){
        return ResponseEntity.ok().body(fundingService.MakerFundingList(state, pageable));
    }

    @GetMapping("/search/category")
    @ApiOperation(value="카테고리 별 조회", notes = "카테고리 타입 별 펀딩 리스트 조회 기능 , page : 페이지 , size : 한 페이지에서 보여줄 개수")
    public ResponseEntity<FundingResponse.fundingList> categoryList(String category , Pageable pageable) throws NotFoundException {
        return ResponseEntity.ok().body(fundingService.CategoryFundingList(category, pageable));
    }

    @GetMapping("/search")
    @ApiOperation(value="마감임박, 신규상품, 인기상품 펀딩 리스트 조회", notes = "type=조회조건(endedAt/new/popular) , page : 페이지 , size : 한 페이지에서 보여줄 개수")
    public ResponseEntity<FundingResponse.fundingList> fundingList (String type, Pageable pageable) {
        System.out.println(type);
        return ResponseEntity.ok().body(fundingService.FundingList(type, pageable));
    }

    @GetMapping("/detail")
    @ApiOperation(value="펀딩 상세 조회", notes = "펀딩 상세 페이지 조회 기능으로 fundingId 를 통해 조회한 펀딩의 상세 정보 반환환")
    public ResponseEntity<FundingResponse.DetailFunding> detailFunding (String fundingId) throws NotFoundException {
        return ResponseEntity.ok().body(fundingService.DetailFunding(fundingId));
    }



//    @PatchMapping("/modify")
//    @ApiOperation(value="펀딩 임시저장 내역 수정", notes = "작성 중인 펀딩신청서 수정")
//    public ResponseEntity<?> modify (@RequestBody FundingRequest.Create request) throws NotFoundException {
//        fundingService.FundingModify(request);
////        return ResponseEntity.ok().body(fundingService.TempStorage(request));
//        return null;
//    }



}
