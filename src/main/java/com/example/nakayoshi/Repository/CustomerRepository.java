package com.example.nakayoshi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nakayoshi.Bean.CustomerBean;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer>{
	
}
