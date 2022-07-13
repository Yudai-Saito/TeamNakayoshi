package com.example.nakayoshi;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer>{
	@Query("SELECT X FROM CustomerBean X ORDER BY X.name")
	  List<CustomerBean> findAllOrderByTitle();
}
