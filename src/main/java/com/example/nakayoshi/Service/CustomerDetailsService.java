package com.example.nakayoshi.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
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
    customerDetailsForm.setCreated_at(now);

    String sql = "select * from customers where phone_number = ?";

    Map<String, Object> user = jdbcTemplate.queryForMap(sql, customerForm.getPhone_number());
    customerDetailsForm.setUser_id((Integer)user.get("id"));

    BeanUtils.copyProperties(customerDetailsForm, customerDetailsBean);
    customerDetailsRepository.save(customerDetailsBean);

    return customerDetailsForm;
  }

  public CustomerDetailsForm updateDetails(CustomerDetailsForm customerDetailsForm) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();
    BeanUtils.copyProperties(customerDetailsForm, customerDetailsBean);
    customerDetailsRepository.save(customerDetailsBean);

    return customerDetailsForm;
  }

  public CustomerDetailsForm findOneDetails(Integer id) {

    Optional<CustomerDetailsBean> customerDetailsBean = customerDetailsRepository.findById(id);
    CustomerDetailsForm customerDetailsForm = new CustomerDetailsForm();
    BeanUtils.copyProperties(customerDetailsBean, customerDetailsForm);

    return customerDetailsForm;
  }
}
