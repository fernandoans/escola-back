package com.escola.dto;

import lombok.Getter;
import lombok.Setter;

public class TurmaDTO {

  @Getter @Setter private String id;
  @Getter @Setter private String nome;
  @Getter @Setter private String cargahoraria;
  @Getter @Setter private String numsala;
  @Getter @Setter private String datainicio;
  @Getter @Setter private String datatermino;
  @Getter @Setter private ProfessorDTO professor;
  @Getter @Setter private CursoDTO curso;

  public TurmaDTO() {
    
  }
  
  public TurmaDTO(String id) {
    this.setId(id);
  }
}
