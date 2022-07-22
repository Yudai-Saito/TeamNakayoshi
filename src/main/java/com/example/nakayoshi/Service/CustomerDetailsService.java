package com.example.nakayoshi.Service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nakayoshi.Bean.CustomerDetailsBean;
import com.example.nakayoshi.Form.CustomerDetailsForm;
import com.example.nakayoshi.Repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsService {
  @Autowired
  CustomerDetailsRepository customerDetailsRepository;

  public CustomerDetailsForm createDetails(CustomerDetailsForm customerDetailsForm) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();
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
