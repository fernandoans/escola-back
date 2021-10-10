package com.escola.pojo;

import lombok.Getter;
import lombok.Setter;

public class MensagemPojo {
  @Getter @Setter private String message;
  
  public MensagemPojo() {
  }
  
  public MensagemPojo(String message) {
    this.setMessage(message);
  }
}
