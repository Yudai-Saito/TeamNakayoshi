package com.example.nakayoshi.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nakayoshi.Bean.CustomerBean;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer>{

   Optional<CustomerBean> findByPhoneNumber(String phoneNumber);
   Optional<CustomerBean> findByPhoneNumberLike(String phoneNumber);
}
