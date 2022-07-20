package com.example.nakayoshi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer>{
	
}
