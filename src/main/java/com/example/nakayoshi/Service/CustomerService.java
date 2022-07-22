package com.example.nakayoshi.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nakayoshi.Bean.CustomerBean;
import com.example.nakayoshi.Bean.CustomerDetailsBean;
import com.example.nakayoshi.Form.CustomerDetailsForm;
import com.example.nakayoshi.Form.CustomerForm;
import com.example.nakayoshi.Repository.CustomerDetailsRepository;
import com.example.nakayoshi.Repository.CustomerRepository;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;
  CustomerDetailsRepository customerDetailsRepository;

  public CustomerForm create(CustomerForm customerForm) {

    CustomerBean customerBean = new CustomerBean();
    BeanUtils.copyProperties(customerForm, customerBean);
    customerRepository.save(customerBean);
    return customerForm;

  }

  public CustomerDetailsForm createDetails(CustomerDetailsForm customerDetailsForm) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();
    BeanUtils.copyProperties(customerDetailsForm, customerDetailsBean);
    customerDetailsRepository.save(customerDetailsBean);
    return customerDetailsForm;

  }

  public CustomerForm update(CustomerForm customerForm) {

    CustomerBean customerBean = new CustomerBean();
    BeanUtils.copyProperties(customerForm, customerBean);
    customerRepository.save(customerBean);
    return customerForm;
    
  }

  public CustomerDetailsForm updateDetails(CustomerDetailsForm customerDetailsForm) {

    CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();
    BeanUtils.copyProperties(customerDetailsForm, customerDetailsBean);
    customerDetailsRepository.save(customerDetailsBean);
    return customerDetailsForm;
    
  }

  public List<CustomerForm> findAll() {

    List<CustomerBean> beanList = customerRepository.findAll();
    List<CustomerForm> formList = new ArrayList<CustomerForm>();

    for(CustomerBean customerBean: beanList) {
        CustomerForm customerForm = new CustomerForm();
        BeanUtils.copyProperties(customerBean, customerForm);
        formList.add(customerForm);
    }

    return formList;

  }

  public List<CustomerDetailsForm> findAllDetails() {

    List<CustomerDetailsBean> detailsbeanList = customerDetailsRepository.findAll();
    List<CustomerDetailsForm> detailsformList = new ArrayList<CustomerDetailsForm>();

    for(CustomerDetailsBean customerDetailsBean: detailsbeanList) {
        CustomerDetailsForm customerDetailsForm = new CustomerDetailsForm();
        BeanUtils.copyProperties(customerDetailsBean, customerDetailsForm);
        detailsformList.add(customerDetailsForm);
    }

    return detailsformList;

  }

  public CustomerForm findOne(Integer id) {

    Optional<CustomerBean> customerBean = customerRepository.findById(id);
    CustomerForm customerForm = new CustomerForm();
    BeanUtils.copyProperties(customerBean, customerForm);
    return customerForm;

  }

  public CustomerDetailsForm findOneDetails(Integer id) {

    Optional<CustomerDetailsBean> customerDetailsBean = customerDetailsRepository.findById(id);
    CustomerDetailsForm customerDetailsForm = new CustomerDetailsForm();
    BeanUtils.copyProperties(customerDetailsBean, customerDetailsForm);
    return customerDetailsForm;

  }
}
