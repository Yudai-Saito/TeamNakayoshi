package com.example.nakayoshi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nakayoshi.Bean.CustomerDetailsBean;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsBean, Integer>{
    
}
