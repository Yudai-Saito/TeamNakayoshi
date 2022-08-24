package com.example.nakayoshi.Form;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDetailsForm {

  private Integer id ;
  @NotNull
  private Integer user_id;
  @NotNull
  private String detail;
  @NotNull
  private Date created_at;
  private Date deleted_at;

}
