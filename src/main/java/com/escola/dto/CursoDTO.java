package com.escola.dto;

import lombok.Getter;
import lombok.Setter;

public class CursoDTO {

  @Getter @Setter private String id;
  @Getter @Setter private String nome;
  @Getter @Setter private String descricao;
  @Getter @Setter private String cargahoraria;
  @Getter @Setter private String qtdalunos;
  @Getter @Setter private String datainicio;
  @Getter @Setter private String numsala;
  @Getter @Setter private ProfessorDTO professor;
  
  public CursoDTO() {
    
  }
  
  public CursoDTO(String id) {
    this.setId(id);
  }
}
