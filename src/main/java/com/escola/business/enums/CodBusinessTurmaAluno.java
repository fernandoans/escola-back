package com.escola.business.enums;

public enum CodBusinessTurmaAluno {
  OK("Sem problemas."),
  INCLUIDO_OK("Aluno adicionado a Turma com sucesso."),
  EXCLUIDO_OK("Aluno eliminado da Turma com sucesso."),
  EXCLUIDO_ALL_OK("Todos os alunos eliminados da Turma."),
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
