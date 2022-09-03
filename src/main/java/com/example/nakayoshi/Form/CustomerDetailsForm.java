package com.example.nakayoshi.Form;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDetailsForm {

  private Integer id;

  @NotNull
  private Integer userId;
  
  @NotNull
  private String detail;

  @NotNull
  private Date createdAt;
  
  private Date deletedAt;

}
