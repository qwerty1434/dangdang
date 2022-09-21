package com.dangdang.member.controller;

import com.dangdang.member.dto.MakerJoinRequest;
import com.dangdang.member.service.MakerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maker")
@RequiredArgsConstructor
@ApiOperation(value = "[회원] Maker Controller")
public class MakerController {

    private final MakerService makerService;

    @PostMapping("/join")
    @ApiOperation(value="메이커 등록")
    public void join(@RequestBody MakerJoinRequest maker) {
        makerService.join(maker);
    }

}
