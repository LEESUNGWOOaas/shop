package com.shop.service;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberService memberService;

    public Member createMember() {
       MemberFormDto dto =  MemberFormDto.builder()
                .address("인천")
                .email("test@test.com")
                .password("1111")
                .name("홍길동")
                .build();
        Member member = Member.createMember(dto,passwordEncoder); // 저장 전

        return member;
    }

    @Test
    void saveMemberTest() {
        Member member = createMember();
        System.out.println(member);
        Member member1 = memberService.saveMember(member);
        System.out.println(member1);//저장후
    }
}