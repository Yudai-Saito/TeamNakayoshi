package com.example.nakayoshi.Controller;

import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.nakayoshi.Form.CustomerDetailsForm;
import com.example.nakayoshi.Form.CustomerForm;
import com.example.nakayoshi.Service.CustomerService;
import com.example.nakayoshi.Service.CustomerDetailsService;

@Controller
@RequestMapping("customers")
public class CustomerController {

  CustomerForm custForm = new CustomerForm();
  CustomerDetailsForm custDetailsForm = new CustomerDetailsForm();

  @Autowired
  CustomerService customerService;
  CustomerDetailsService customerDetailsService;

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
  String create(@ModelAttribute("customers") CustomerForm form, 
  @ModelAttribute("details") CustomerDetailsForm form2, Model mode) {
    customerService.create(form);
    customerDetailsService.createDetails(form2);
    custForm.setCreated_at(new Date());
    custDetailsForm.setCreated_at(new Date());
    return "redirect:customers";
  }	

  @PostMapping(path = "detail", params = "form")
  String detailForm(@RequestParam Integer id, CustomerForm form, CustomerDetailsForm form2) {
    CustomerForm customerForm = customerService.findOne(id);
    BeanUtils.copyProperties(customerForm,  form);
    CustomerDetailsForm customerDetailsForm = customerDetailsService.findOneDetails(id);
    BeanUtils.copyProperties(customerDetailsForm,  form2);
    return "customers/detail";
  }

  @PostMapping(path = "edit", params = "form")
  String editForm(@RequestParam Integer id, CustomerForm form) {
    CustomerForm customerForm = customerService.findOne(id);
    BeanUtils.copyProperties(customerForm,  form);
    return "customers/edit";
  }

  @PostMapping(path = "edit")
  String edit(@RequestParam Integer id, CustomerForm form) {
    customerService.update(form);
    custForm.setUpdated_at(new Date());
    return "redirect:/customers";
  }

}