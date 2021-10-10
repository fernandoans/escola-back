package com.escola.business.enums;

public enum CodBusinessTurmaAluno {
  OK("Sem problemas."),
  TURMA_OBRIGATORIA("Turma não foi informada."),
  ALUNO_DUPLICADO("O Aluno aparece duas vezes na mesma turma."),
  ALUNO_OBRIGATORIO("Aluno não foi informado.");

  private String descricao;

  CodBusinessTurmaAluno(String descricao) {
      this.descricao = descricao;
  }

  public String getDescricao() {
      return descricao;
  }  
}
