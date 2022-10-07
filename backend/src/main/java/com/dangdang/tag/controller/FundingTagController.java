package com.dangdang.tag.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.funding.domain.Funding;
import com.dangdang.tag.domain.Tag;
import com.dangdang.tag.service.FundingTagService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/funding-tag")
@RequiredArgsConstructor
@ApiOperation(value = "[태그] FundingTag Controller")
public class FundingTagController {

    private final FundingTagService fundingTagService;

    @GetMapping("/list")
    @ApiOperation(value="펀딩에 태그가 없는데? 추후 논의")
    public List<Funding> tagList(@RequestParam("tagId") String tagId) throws NotFoundException {
        return fundingTagService.findContainsTag(tagId);
    }

}
