package com.example.nakayoshi.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBean {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(nullable = false)
  private String name;
  private String phone_number;
  private Date created_at;
  private Date updated_at;
  private Date deleted_at;
   
}
