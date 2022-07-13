package com.example.nakayoshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nakayoshi.CustomerBean;
import com.example.nakayoshi.CustomerForm;
@Service
public class CustomerService {
  @Autowired
  CustomerRepository customerRepository;

  public CustomerForm findOne(Integer id) {
    Optional<CustomerBean> customerBean = customerRepository.findById(id);
    CustomerForm customerForm = new CustomerForm();
    BeanUtils.copyProperties(customerBean, customerForm);
    return customerForm;
	  }
}