package com.escola.pojo;

import lombok.Getter;
import lombok.Setter;

public class CursoPojo {

  @Getter @Setter private String id;
  @Getter @Setter private String nome;
  @Getter @Setter private String descricao;
  @Getter @Setter private String cargahoraria;
  @Getter @Setter private String qtdalunos;
  @Getter @Setter private String datainicio;
  @Getter @Setter private String numsala;
  @Getter @Setter private ProfessorPojo professor;
  
}
