package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // @autowired 가 아니라 final로 적어서 사용하면 의존성 주입 가능
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicationMember(member); // 멤버가 기존에 등록됬는지 확인하기위해

        return memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()){
            System.out.println(findMember.get().getName()); //존재하는지 멤버를 뽑아온다.
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }


    }
}
