package com.dangdang.category.controller;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.category.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@ApiOperation(value = "[카테고리] Category Controller")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    @ApiOperation(value="카테고리 추가", notes = "'/category/add?type=food' 형식으로 사용")
    public void addType(@RequestParam("type") String type){
        categoryService.add(type);
    }

    @GetMapping("/list")
    @ApiOperation(value="카테고리 목록 조회")
    public List<String> findTypeList(){
        return categoryService.find();
    }

    @PatchMapping("/modify")
    @ApiOperation(value="카테고리 수정\noldtype: 원래 타입명, newtype: 변경할 타입명", notes = "'/category/add?newtype=food&oldtype=fod' 형식으로 사용")
    public void changeType(@RequestParam("oldtype") String oldtype, @RequestParam("newtype") String newtype) throws NotFoundException {
        categoryService.change(oldtype, newtype);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="카테고리 삭제", notes = "'/category/delete?type=food' 형식으로 사용")
    public void delType(@RequestParam("type") String type) throws NotFoundException {
        categoryService.deleteType(type);
    }

}
