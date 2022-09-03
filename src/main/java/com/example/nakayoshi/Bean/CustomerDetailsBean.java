package com.example.nakayoshi.Bean;

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

  @Column(name="user_id", nullable = false)
  private Integer userId;

  @Column(nullable = false)
  private String detail;

  @Column(name="created_at")
  private Date createdAt;

  @Column(name="deleted_at")
  private Date deletedAt;
   
}
