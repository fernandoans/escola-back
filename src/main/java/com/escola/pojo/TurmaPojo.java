package com.escola.pojo;

import lombok.Getter;
import lombok.Setter;

public class TurmaPojo {

  @Getter @Setter private String id;
  @Getter @Setter private String nome;
  @Getter @Setter private String cargahoraria;
  @Getter @Setter private String numsala;
  @Getter @Setter private String datainicio;
  @Getter @Setter private String datatermino;
  @Getter @Setter private ProfessorPojo professor;
  @Getter @Setter private CursoPojo curso;
  
}
