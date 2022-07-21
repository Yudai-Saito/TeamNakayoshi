package com.example.nakayoshi;

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
  private String phone_number;
  private String past_contact;
  private Date created_at;
  private Date updated_at;
  private Date deleted_at;

}