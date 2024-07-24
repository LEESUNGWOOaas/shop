package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //optional ���������ְ� ���������ִ�. nulló��������
    Optional<Member> findByEmail(String email);

}
