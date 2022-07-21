package com.example.nakayoshi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  String list(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
  @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model){
    model.addAttribute("customers", customerService.findAll());
    model.addAttribute("customers", customerService.getPage(pageNumber, size));
    return "customers/list";
  }
}