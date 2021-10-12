package com.escola.dto;

import com.escola.business.enums.CodBusinessAluno;
import com.escola.business.enums.CodBusinessCurso;
import com.escola.business.enums.CodBusinessProfessor;
import com.escola.business.enums.CodBusinessTurma;
import com.escola.business.enums.CodBusinessTurmaAluno;

import lombok.Getter;
import lombok.Setter;

public class MensagemDTO {
  @Getter @Setter private String message;
  
  public MensagemDTO() {
  }
  
  public MensagemDTO(String message) {
    this.setMessage(message);
  }
  
  // Mensagens de Negócio

  public MensagemDTO(CodBusinessAluno en) {
    this.setMessage(en.getDescricao());
  }  

  public MensagemDTO(CodBusinessProfessor en) {
    this.setMessage(en.getDescricao());
  }  

  public MensagemDTO(CodBusinessCurso en) {
    this.setMessage(en.getDescricao());
  }  

  public MensagemDTO(CodBusinessTurma en) {
    this.setMessage(en.getDescricao());
  }  

  public MensagemDTO(CodBusinessTurmaAluno en) {
    this.setMessage(en.getDescricao());
  }  
}
