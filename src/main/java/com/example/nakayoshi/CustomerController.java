package com.example.nakayoshi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("customers")
public class CustomerController {

  @Autowired
  CustomerService customerService;
   
  @ModelAttribute 
  CustomerForm setUpForm() {
    return new CustomerForm();
  }

  @GetMapping
  String list(Model model, @RequestParam(defaultValue = "1") int page) {
       FindforPagenateService findforPagenateService = new FindforPagenateService();
       // 総表示数 
       int totalListCnt = findforPagenateService.findAllCnt();

       // 総表示数と現在ページ
       Pagination pagination = new Pagination(totalListCnt, page);
   
       // DB接近スタートインデックス
       int startIndex = pagination.getStartIndex();
   
       // ページことに表示する掲示物最大数
       int pageSize = pagination.getPageSize();
   
       // 表示する要素の取得
       List<CustomerBean> boardList = findforPagenateService.findListPaging(startIndex, pageSize);
   
       // モデルオブジェクトにオブジェクト格納
      model.addAttribute("boardList", boardList);
      model.addAttribute("pagination", pagination);
      model.addAttribute("customers", customerService.findAll());

      return "customers/list";
  }
}