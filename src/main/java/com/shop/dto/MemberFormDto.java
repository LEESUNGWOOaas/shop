package com.shop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberFormDto {

    private String name;
    private String email;
    private String password;
    private String address;

}
