package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //optional 있을수도있고 없을수도있다. null처리를위해
    Optional<Member> findByEmail(String email);

}
