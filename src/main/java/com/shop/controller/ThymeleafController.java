package com.shop.controller;

import com.shop.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ThymeleafController {
    @GetMapping("/thymeleaf/ex1")
    public String ex1(Model model){
        ItemDto itemDto = ItemDto.builder()
                                 .itemNm("최신")
                                 .itemSellStatus("Hel")
                                 .build();
        return "thymeleaf/ex1";
    }
}
