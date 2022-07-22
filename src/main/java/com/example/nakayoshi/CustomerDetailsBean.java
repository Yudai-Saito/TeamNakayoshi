package com.example.nakayoshi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsBean {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(nullable = false)
  private Integer user_id;
  @Column(nullable = false)
  private String detail;
  @Column(nullable = false)
  private Date created_at;
  private Date deleted_at;
   
}
