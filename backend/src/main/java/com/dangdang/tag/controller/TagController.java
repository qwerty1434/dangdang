package com.dangdang.tag.controller;

import com.dangdang.advice.exceptions.DuplicateException;
import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.tag.domain.Tag;
import com.dangdang.tag.service.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
@ApiOperation(value = "[태그] Tag Controller")
public class TagController {

    private final TagService tagService;

    @PostMapping("/add")
    @ApiOperation(value="태그 추가. 태그는 펀딩 추가 시에도 가능하다.")
    public void addTag(@RequestParam("tagname") String tagname) throws NotFoundException, DuplicateException {
        tagService.addTag(tagname);
    }

    @GetMapping("/list")
    @ApiOperation(value="태그명이 포함된 태그 목록 반환")
    public List<Tag> tagList(@RequestParam("tagname") String tagname) throws NotFoundException {
        return tagService.tagList(tagname);
    }

}
