package com.dangdang.category.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.category.domain.Category;
import com.dangdang.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void add(String type) {
        Category category = new Category(type);
        categoryRepository.save(category);
    }

    public List<String> find() {
        return categoryRepository.findAllTypes();
    }

    public void change(String oldtype, String newtype) throws NotFoundException {
        try{
            Category category = categoryRepository.findByType(oldtype);
            System.out.println("category "+category.getType());
            category.setType(newtype);
            System.out.println("set 완료");
            categoryRepository.save(category);
        }catch (Exception e){
            throw new NotFoundException("찾는 타입이 없습니다.");
        }
    }

    public void deleteType(String type) throws NotFoundException {
        try{
            Category category = categoryRepository.findByType(type);
            categoryRepository.delete(category);
        }catch (Exception e){
            throw new NotFoundException("찾는 타입이 없습니다.");
        }
    }
}
