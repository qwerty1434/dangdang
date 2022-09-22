package com.dangdang.tag.service;

import com.dangdang.advice.exceptions.DuplicateException;
import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.tag.domain.FundingTag;
import com.dangdang.tag.domain.Tag;
import com.dangdang.tag.repository.FundingTagRepostiory;
import com.dangdang.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class FundingTagService {

    private final TagRepository tagRepository;
    private final FundingTagRepostiory fundingTagRepostiory;
    private final FundingRepository fundingRepository;

    public void addTag(String tagname, String fundingId) {
        List<Tag> list = tagRepository.findAllByName(tagname);
        if(list.size()!=0){
            Tag tag = new Tag(tagname);
            tagRepository.save(tag);
        }

        Tag tag = tagRepository.findByName(tagname);
        Funding funding = fundingRepository.findById(UUID.fromString(fundingId)).get();
        FundingTag fundingTag = new FundingTag(tag,funding);
        fundingTagRepostiory.save(fundingTag);
    }

    public List<Funding> findContainsTag(String tagId) {
        return fundingTagRepostiory.findAllByTagId(tagId);
    }

}
