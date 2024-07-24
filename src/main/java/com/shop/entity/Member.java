package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
       // Member member = new Member();
        //member.setName();
        Member member = Member.builder()
                              .role(Role.USER)
                              .email(memberFormDto.getEmail())
                              .address(memberFormDto.getAddress())
                              .name(memberFormDto.getName())
                              .password(passwordEncoder.encode(memberFormDto.getPassword()))
                              .build();

        //String password = member.getPassword(); 둘다 같다.
        //String password = memberFormDto.getPassword();
        //password = passwordEncoder.encode(password);
        //member.setPassword(password);
        //String password = "";
        //password = passwordEncoder.encode(memberFormDto.getPassword());
        //member.setPassword();
        return member;
    }
}
