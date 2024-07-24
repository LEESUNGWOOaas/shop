package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // @autowired �� �ƴ϶� final�� ��� ����ϸ� ������ ���� ����
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicationMember(member); // ����� ������ ��ω���� Ȯ���ϱ�����

        return memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()){
            System.out.println(findMember.get().getName()); //�����ϴ��� ����� �̾ƿ´�.
            throw new IllegalArgumentException("�̹� �����ϴ� ȸ���Դϴ�.");
        }


    }
}
