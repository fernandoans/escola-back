package com.escola.pojo;

import lombok.Getter;
import lombok.Setter;

public class TurmaAlunoPojo {
  @Getter @Setter private String id;
  @Getter @Setter private TurmaPojo turma;
  @Getter @Setter private AlunoPojo aluno;
}
