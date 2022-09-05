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

  @GetMapping("{user_id}")
  String detailForm(@PathVariable Integer user_id, CustomerForm userInfo, CustomerDetailsForm userDetail, Model model) {
    model.addAttribute("customer", customerService.findOne(user_id));
    model.addAttribute("detail", customerDetailService.findUserDetails(user_id));

    return "customers/details";
  }

  @GetMapping("/form/{user_id}")
  public String postScript(@PathVariable int user_id, Model model) {
    model.addAttribute("customer", customerService.findOne(user_id));
    model.addAttribute("detail", customerDetailService.findUserDetails(user_id));

    return "forms/postscript";
  }

  @PostMapping(path = "add", params = "user_id")
  String addDetails(@RequestParam Integer user_id, @RequestParam String detail, Model model){
    customerDetailService.addDetails(user_id, detail);

    return "redirect:/customers/" + user_id;
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
}
