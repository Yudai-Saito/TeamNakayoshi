package com.example.nakayoshi.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nakayoshi.Bean.CustomerDetailsBean;
import com.example.nakayoshi.Form.CustomerDetailsForm;
import com.example.nakayoshi.Form.CustomerForm;
import com.example.nakayoshi.Repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsService {
  @Autowired
  CustomerDetailsRepository customerDetailsRepository;

  @Autowired
  JdbcTemplate jdbcTemplate;

  public CustomerDetailsForm createDetail(CustomerForm customerForm, CustomerDetailsForm customerDetailsForm) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();

    Date now = new Date();
    customerDetailsForm.setCreatedAt(now);

    String sql = "select * from customers where phone_number = ?";

    Map<String, Object> user = jdbcTemplate.queryForMap(sql, customerForm.getPhoneNumber());
    customerDetailsForm.setUserId((Integer)user.get("id"));

    BeanUtils.copyProperties(customerDetailsForm, customerDetailsBean);
    customerDetailsRepository.save(customerDetailsBean);

    return customerDetailsForm;
  }
      
  public void addDetails(Integer user_id, String detail) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();

    customerDetailsBean.setUserId(user_id);

    customerDetailsBean.setDetail(detail);

    Date now = new Date();
    customerDetailsBean.setCreatedAt(now);

    customerDetailsRepository.save(customerDetailsBean);

    return;
  }

  public String findUserDetails(Integer user_id) {

    String customerDetail = "";

    List<CustomerDetailsBean> customerDetailsBean = customerDetailsRepository.findByUserIdOrderByCreatedAtDesc(user_id);

    for(CustomerDetailsBean userBean : customerDetailsBean) {
        customerDetail = customerDetail + userBean.getCreatedAt().toString() + "\n"; 
        customerDetail = customerDetail + userBean.getDetail().toString() + "\n\n"; 
    }

    return customerDetail;
  }
}
