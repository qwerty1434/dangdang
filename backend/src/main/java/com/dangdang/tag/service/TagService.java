package com.dangdang.tag.service;

import com.dangdang.advice.exceptions.DuplicateException;
import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.tag.domain.Tag;
import com.dangdang.tag.repository.TagRepository;
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
public class TagService {

    private final TagRepository tagRepository;

    public void addTag(String tagname) throws DuplicateException {
        List<Tag> list = tagRepository.findAllByName(tagname);
        if(list.size()!=0){
            throw new DuplicateException("이미 존재하는 태그입니다");
        }
        Tag tag = new Tag(tagname);
        tagRepository.save(tag);
    }

    public List<Tag> tagList(String tag) {
        return tagRepository.findAllByNameContainTag(tag);
    }

}
