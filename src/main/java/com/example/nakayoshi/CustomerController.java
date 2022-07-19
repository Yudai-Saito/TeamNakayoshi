package com.example.nakayoshi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
  String list(Model model) {
    model.addAttribute("customers", customerService.findAll());
    return "customers/list";
  }

  @PostMapping(path="create")
  String create(CustomerForm form, Model mode) {
    customerService.create(form);
    return "redirect:customers";
  }	

  @PostMapping(path = "detail", params = "form")
  String detailForm(@RequestParam Integer id, CustomerForm form) {
    CustomerForm customerForm = customerService.findOne(id);
    BeanUtils.copyProperties(customerForm,  form);
    return "customers/detail";
  }

}