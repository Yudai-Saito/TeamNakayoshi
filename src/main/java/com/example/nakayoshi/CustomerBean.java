package com.example.nakayoshi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBean {
  @Id
  @GeneratedValue
  private Integer id ;
  @Column(nullable = false)
  private String name;
  private String phone_nmber;
  private String past_contact;
  private Data data;
}
