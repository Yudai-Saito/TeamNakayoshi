package com.example.nakayoshi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nakayoshi.Bean.CustomerDetailsBean;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsBean, Integer>{
   List<CustomerDetailsBean> findByUserIdOrderByCreatedAtDesc(Integer id);
}
