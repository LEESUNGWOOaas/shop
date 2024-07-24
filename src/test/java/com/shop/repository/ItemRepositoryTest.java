package com.shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.thymeleaf.util.StringUtils;

//@SpringBootTest
//@Transactional // 트랜잭션을 걸면 동작 후 롤백을 한다 데이터를 저장을 확인하기위해선 안붙이거나 옵션을 붙일수있다.
class ItemRepositoryTest {
		@Autowired
		ItemRepository itemRepository;
		private EntityManager em;
	
		public void createItemList() {
			for (int i = 1; i <= 10; i++) {
				Item item = Item.builder()
						.itemNm("테스트상품" + i)
						.price(2000  + i)
						.stockNumber(40  + i)
						.itemDetail("테스트상품 상세정보"  + i)
						.itemSellStatus(ItemSellStatus.SELL)
						.insertDate(LocalDateTime.now())
						.updateDate(LocalDateTime.now())
						.build();
				
				itemRepository.save(item);
			}
		}
		
		@Test
		@DisplayName("상품/상세 OR테스트")
		public void findByItemNmOrItemDetailTest() {
			createItemList();
			List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트상품2", "테스트상품 상세정보8");
			
			itemList.forEach(item -> {
				System.out.println(item);
			});
		}
		
		 @Test
		    @DisplayName("가격 LessThan 테스트")
		    public void findByPriceLessThanTest(){
		        this.createItemList();
		        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
		        for(Item item : itemList){
		            System.out.println(item.toString());
		        }
		    }
		@Test
		@DisplayName("queryDsl 테스트")
		public void querydslTest2(){
			createItemList();

			BooleanBuilder builder =new BooleanBuilder();
			String itemDetail = "테스트";
			int price = 10000;
			String itemSellStatus = "SELL";

			QItem item = QItem.item;
			builder.and(item.itemDetail.like("%"+itemDetail+"%"));
			builder.and(item.price.gt(100004));

			if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)){
				builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
			}
			Pageable pageable = PageRequest.of(0,5);
			Page<Item> page = itemRepository.findAll(builder, pageable);
			page.getContent();
		}
		@Test
		@DisplayName("queryDsl 테스트 1")
		public void querydslTest(){
			createItemList();
			JPAQueryFactory query = new JPAQueryFactory(em);
			QItem qItem = QItem.item;
			query.select(QItem.item).from(QItem.item).where().orderBy().fetch();

		}

		 @Test
		    @DisplayName("JPQL 쿼리")
		    public void findByItemDetailTest(){
		        createItemList();

		        List<Item> itemList = itemRepository.findByItemDetail("테스트");

		        for (Item item : itemList) {
		            System.out.println(item);
		        }
		    }

		    @Test
		    @DisplayName("Native 쿼리")
		    public void findByItemDetailNativeTest(){
		        createItemList();

		        List<Item> itemList = itemRepository.findByItemDetailNative("테스트");

		        for (Item item : itemList) {
		            System.out.println(item);
		        }
		    }

		 
		@Test
		@DisplayName("상품 검색 테스트 ")
		public void findByItemNmTest() {
			createItemList();
			
		//	List<Item> itemList = itemRepository.findByItemNm("테스트상품1");
		/*
		 * for(Item item : itemList) { System.out.println(item); }
		 */
		//	itemList.forEach(item -> System.out.println("item"));
		//	itemList.forEach(System.out::println);
			
			//itemRepository.findByItemNm("테스트상품1").forEach(System.out::println);
			/*List<Item> itemList = itemRepository.findByItemNm("테스트상품1");
			 * itemList.forEach(System.out::println);  이 두줄과 같은 뜻 
			 * */
			itemRepository
				.findByItemNm("테스트상품1")
				.forEach((item)->{
					System.out.println(item);
					});
		}
	
	@Test
	@DisplayName("상품생성테스트")
	public void createItemTest() {
		Item item = Item.builder()
						.itemNm("테스트상품")
						.price(2000)
						.stockNumber(40)
						.itemDetail("테스트상품 상세정보")
						.itemSellStatus(ItemSellStatus.SELL)
						.insertDate(LocalDateTime.now())
						.updateDate(LocalDateTime.now())
						.build();
		System.out.println("==========Items(저장이전의 값) : "+item);
		Item savedItem = itemRepository.save(item); 
		System.out.println("==========SavedItems(저장된 이후의 값) : "+savedItem);
	}

}
