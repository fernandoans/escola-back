package com.escola.dto;

import lombok.Getter;
import lombok.Setter;

public class TurmaAlunoDTO {
  @Getter @Setter private String id;
  @Getter @Setter private TurmaDTO turma;
  @Getter @Setter private AlunoDTO aluno;
}
