package com.example.nakayoshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Sort;
@Service
public class CustomerService {
 
  @Autowired
  CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
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
   // ページ取得メソッド
   public Paged<CustomerBean> getPage(int pageNumber, int size) {
 
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<CustomerBean> postPage = customerRepository.findAll(request);
    return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
}
}