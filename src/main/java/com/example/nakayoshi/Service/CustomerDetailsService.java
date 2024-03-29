package com.example.nakayoshi.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nakayoshi.Bean.CustomerBean;
import com.example.nakayoshi.Bean.CustomerDetailsBean;
import com.example.nakayoshi.Form.CustomerDetailsForm;
import com.example.nakayoshi.Form.CustomerForm;
import com.example.nakayoshi.Repository.CustomerDetailsRepository;
import com.example.nakayoshi.Repository.CustomerRepository;

@Service
public class CustomerDetailsService {
  @Autowired
  CustomerDetailsRepository customerDetailsRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  JdbcTemplate jdbcTemplate;

  public CustomerDetailsForm createDetail(CustomerForm customerForm, CustomerDetailsForm customerDetailsForm) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();

    Date now = new Date();
    customerDetailsForm.setCreatedAt(now);

    Optional<CustomerBean> customer = customerRepository.findByPhoneNumber(customerForm.getPhoneNumber());
    customerDetailsForm.setUserId(customer.get().getId());
        
    BeanUtils.copyProperties(customerDetailsForm, customerDetailsBean);
    customerDetailsRepository.save(customerDetailsBean);

    return customerDetailsForm;
  }
      
  public void addDetails(Integer userId, String detail) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();

    customerDetailsBean.setUserId(userId);

    customerDetailsBean.setDetail(detail);

    Date now = new Date();
    customerDetailsBean.setCreatedAt(now);

    customerDetailsRepository.save(customerDetailsBean);

    return;
  }

  public void deleteDetails(Integer userId){
    customerDetailsRepository.deleteByUserId(userId);
  }

  public String findUserDetails(Integer userId) {

    String customerDetail = "";

    List<CustomerDetailsBean> customerDetailsBean = customerDetailsRepository.findByUserIdOrderByCreatedAtDesc(userId);

    for(CustomerDetailsBean userBean : customerDetailsBean) {
        customerDetail = customerDetail + userBean.getCreatedAt().toString() + "\n"; 
        customerDetail = customerDetail + userBean.getDetail().toString() + "\n\n"; 
    }

    return customerDetail;
  }
}
