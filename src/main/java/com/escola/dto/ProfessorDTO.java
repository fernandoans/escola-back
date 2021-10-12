package com.escola.dto;

import lombok.Getter;
import lombok.Setter;

public class ProfessorDTO {
  @Getter @Setter private String matricula;
  @Getter @Setter private String nome;
  @Getter @Setter private String datanascimento;
  @Getter @Setter private String sexo;
  @Getter @Setter private String contato;
  @Getter @Setter private String email;
  @Getter @Setter private String disciplina;
  
  public ProfessorDTO() {
    
  }
  
  public ProfessorDTO(String matricula) {
    this.setMatricula(matricula);
  }
}
