package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDateTime;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;

    private String itemNm; // 물품이름

    private int price; // 가격

    private int stockNumber; // 재고수량


    private String itemDetail; // 상품상세설명


    private String itemSellStatus; //상품 판매상태

    private LocalDateTime insertDate; // 상품 등록시간

    private LocalDateTime updateDate; // 상품 수정시간
}
