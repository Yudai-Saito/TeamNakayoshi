package com.example.nakayoshi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name = "Nakayoshi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NakayoshiBean {
  @Id
  @GeneratedValue
  private Integer id ;
  @Column(nullable = false)
  private String name;
  private String callnuumber;
  private String inquiry;
  private Data data;
}
