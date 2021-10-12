package com.escola.business.enums;

public enum CodBusinessAluno {
  OK("Sem problemas."),
  INCLUIDO_OK("Aluno criado com sucesso."),
  ALTERADO_OK("Aluno modificado com sucesso."),
  EXCLUIDO_OK("Aluno eliminado com sucesso."),
  EXCLUIDO_ALL_OK("Todos os alunos eliminados."),
  MATRICULA_OBRIGATORIA("Matrícula não foi informada."),
  NOME_OBRIGATORIO("Nome não foi informado."),
  EMAIL_OBRIGATORIO("E-mail não foi informado."),
  DATA_MALFORMADA("Data deve estar no padrão DD/MM/AAAA.");
  
  private String descricao;

  CodBusinessAluno(String descricao) {
      this.descricao = descricao;
  }

  public String getDescricao() {
      return descricao;
  }  
}
