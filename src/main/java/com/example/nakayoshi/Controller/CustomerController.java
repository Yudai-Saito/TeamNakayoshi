package com.example.nakayoshi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Autowired
  CustomerService customerService;
  @Autowired
  CustomerDetailsService customerDetailService;

  @ModelAttribute 
  CustomerForm setUpForm() {
    return new CustomerForm();
  }

  @GetMapping
  String list(Model model) {
    //ページングを実装する場合はAllではなくLimit的なのをかける
    model.addAttribute("customers", customerService.findAll());
    return "customers/list";
  }

  @GetMapping("regist")
  String regist(@ModelAttribute("customer") CustomerForm userInfo, 
    @ModelAttribute("detail") CustomerDetailsForm userDetail, Model model){

    return "customers/register";
  }

  @PostMapping(path = "create")
  String create(@ModelAttribute("customer") CustomerForm userInfo, 
    @ModelAttribute("detail") CustomerDetailsForm userDetail, Model model) {;
    customerService.createCustomer(userInfo);
    customerDetailService.createDetail(userInfo, userDetail);

    return "redirect:/customers";
  }	

  @GetMapping("{userId}")
  String detailForm(@PathVariable Integer userId, CustomerForm userInfo, CustomerDetailsForm userDetail, Model model) {
    model.addAttribute("customer", customerService.findOne(userId));
    model.addAttribute("detail", customerDetailService.findUserDetails(userId));

    return "customers/details";
  }

  @GetMapping("/form/{userId}")
  public String postScript(@PathVariable int userId, Model model) {
    model.addAttribute("customer", customerService.findOne(userId));
    model.addAttribute("detail", customerDetailService.findUserDetails(userId));

    return "forms/postscript";
  }

  @PostMapping(path = "add", params = "userId")
  String addDetails(@RequestParam Integer userId, @RequestParam String detail, Model model){
    customerDetailService.addDetails(userId, detail);

    return "redirect:/customers/" + userId;
  }

  @PostMapping(path = "/delete")
  String customerDelete(@RequestParam Integer userId){
    customerDetailService.deleteDetails(userId);
    customerService.delete(userId);

    return "redirect:/customers";
  }

  @GetMapping("/edit/{userId}")
  String editForm(@PathVariable Integer userId, Model model) {
    model.addAttribute("customer", customerService.findOne(userId));

    return "customers/edit";
  }

  @PostMapping(path = "/edit")
  String edit(@RequestParam Integer userId, @RequestParam String userName, @RequestParam String phoneNumber) {
    customerService.updateCustomer(userId, userName, phoneNumber);

    return "redirect:/customers/" + userId;
  }

  @GetMapping("/search")
  String customerSearch(@RequestParam String phoneNumber, Model model){

    model.addAttribute("customers", customerService.findOne(phoneNumber));
    return "customers/list";
  }
}
