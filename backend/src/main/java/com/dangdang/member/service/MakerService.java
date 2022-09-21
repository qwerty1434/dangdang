package com.dangdang.member.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.member.domain.Authority;
import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.MakerJoinRequest;
import com.dangdang.member.dto.UserJoinRequest;
import com.dangdang.member.repository.MakerRepository;
import com.dangdang.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class MakerService {

    private final MakerRepository makerRepository;
    private final UserRepository userRepository;

    public void join(MakerJoinRequest input) {

        /*
        토큰 이용해서 회원 정보 가져오기 (uuid)
        이 부분은 이후 security완성 후 추가 예정
        현재는 무조건 이메일이 ssafy@ssafy.com이라는 상태에서 진행
         */
        User user = userRepository.findByEmail("ssafy@ssafy.com");
        System.out.println(user.getEmail());
        System.out.println(user.getId());

        /*
        사업자 등록 여부 등도 확인 해서 넣을 예정
        현재 생각하고 있는 방식
        1. 사업자 등록 여부와 관련 정보 추출 api 작성
        2. 프론트에서 해당 정보 받고, 메이커 등록 시 관련 정보 body에 담아서 전송
        3. 백이 받아서 다시 확인 후 메이커 생성
         */

        // 메이커 생성 시 하나의 사업자번호를 한명만 등록하게 했었는지 파악 후 예외처리 추가
        Maker maker = Maker.builder().user(user).companyNumber(input.getCompanyNumber())
                .companyName(input.getCompanyName()).img(input.getImg()).fundingSum(0L).build();
        makerRepository.save(maker);
    }

}
