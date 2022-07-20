package com.example.nakayoshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  public CustomerForm create(CustomerForm customerForm) {

	  CustomerBean customerBean = new CustomerBean();
	  BeanUtils.copyProperties(customerForm, customerBean);
	  customerRepository.save(customerBean);
	  return customerForm;

	}

  public CustomerForm update(CustomerForm customerForm) {

	  CustomerBean customerBean = new CustomerBean();
	  BeanUtils.copyProperties(customerForm, customerBean);
	  customerRepository.save(customerBean);
	  return customerForm;
    
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

  public CustomerForm findOne(Integer id) {

    Optional<CustomerBean> customerBean = customerRepository.findById(id);
    CustomerForm customerForm = new CustomerForm();
    BeanUtils.copyProperties(customerBean, customerForm);
    return customerForm;

	}
}