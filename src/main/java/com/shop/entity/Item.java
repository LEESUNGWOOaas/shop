package com.shop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.shop.constant.ItemSellStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private Long id;
	@Column(nullable = false,length = 50)
	private String itemNm; // 물품이름
	
	private int price; // 가격
	
	private int stockNumber; // 재고수량
	
	@Lob
	@Column(nullable = false)
	private String itemDetail; // 상품상세설명
	
	@Enumerated(EnumType.STRING) // 열거타입에 사용(ordinal - 숫자로 관리  String 문자로관리 )
	private ItemSellStatus itemSellStatus; //상품 판매상태
	//@CreateBy 생성자 등록하는 것 
	//@CreatedDate
	private LocalDateTime insertDate; // 상품 등록시간
	//@LastModifiedDate // 마지막 수정일을 관리 (업데이트날)
	private LocalDateTime updateDate; // 상품 수정시간
	
	
}
