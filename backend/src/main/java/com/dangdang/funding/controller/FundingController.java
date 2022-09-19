package com.dangdang.funding.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fund")
@RequiredArgsConstructor
@ApiOperation(value = "[펀딩] Funding Controller")
public class FundingController {

}
