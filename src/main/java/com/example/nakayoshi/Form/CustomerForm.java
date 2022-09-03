package com.example.nakayoshi.Form;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerForm {

  private Integer id ;

  @NotNull
  private String name;

  @NotNull
  @Size(min = 12, max = 13)
  private String phoneNumber;

  @NotNull
  private Date createdAt;

  private Date updatedAt;
  private Date deletedAt;

}
