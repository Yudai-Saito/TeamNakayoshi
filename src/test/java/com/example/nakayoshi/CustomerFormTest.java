package com.example.nakayoshi;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.nakayoshi.Form.CustomerDetailsForm;
import com.example.nakayoshi.Form.CustomerForm;

//@SpringBootTest
class CustomerFormTest {
/*@Autowired 
  CustomerBean customerBean;*/
  static CustomerForm customerForm = null;
  static CustomerDetailsForm customerDetailsForm = null;
  @BeforeEach
  void テスト前処理() {
    customerForm = new CustomerForm();
    customerDetailsForm = new CustomerDetailsForm();
  }

  @Test
  void name_test() {
    customerForm.setName("なかよし");
    assertThat(customerForm.getName()) .isEqualTo("なかよし");
  }

  @Test
  void phone_number_test() {
    customerForm.setPhoneNumber("090-7071-7201");
    assertThat(customerForm.getPhoneNumber()) .isEqualTo("090-7071-7201");
  }

  @Test
  void phone_number_size_test() {
    customerForm.setPhoneNumber("090-7070-1324");
    assertThat(customerForm.getPhoneNumber().length()) .isBetween(12, 13);
  }

  @Test
  void Detail_test() {
    customerDetailsForm.setDetail("みんな中吉");
    assertThat(customerDetailsForm.getDetail()) .isEqualTo("みんな中吉");
  }

  @Test
  void date_test() {
    Date cur = new Date();
    String str = new SimpleDateFormat("yyyy-MM-dd").format(cur);
    assertEquals("2022-07-15", str);
  }

  @AfterEach
  void テスト後処理() {
    customerForm = null;
 }

}
