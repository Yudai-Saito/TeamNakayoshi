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

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  @Column(name = "deleted_at")
  private Date deletedAt;
   
}
