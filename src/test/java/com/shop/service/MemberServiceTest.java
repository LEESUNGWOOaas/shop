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
                .address("��õ")
                .email("test@test.com")
                .password("1111")
                .name("ȫ�浿")
                .build();
        Member member = Member.createMember(dto,passwordEncoder); // ���� ��

        return member;
    }

    @Test
    void saveMemberTest() {
        Member member = createMember();
        System.out.println(member);
        Member member1 = memberService.saveMember(member);
        System.out.println(member1);//������
    }
}