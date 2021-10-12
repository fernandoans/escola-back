package com.escola.business.enums;

public enum CodBusinessProfessor {
  OK("Sem problemas."),
  INCLUIDO_OK("Professor criado com sucesso."),
  ALTERADO_OK("Professor modificado com sucesso."),
  EXCLUIDO_OK("Professor eliminado com sucesso."),
  EXCLUIDO_ALL_OK("Todos os professores eliminados."),
  MATRICULA_OBRIGATORIA("Matrícula não foi informada."),
  NOME_OBRIGATORIO("Nome não foi informado."),
  EMAIL_OBRIGATORIO("E-mail não foi informado."),
  DATA_MALFORMADA("Data deve estar no padrão DD/MM/AAAA.");
  
  private String descricao;

  CodBusinessProfessor(String descricao) {
      this.descricao = descricao;
  }

  public String getDescricao() {
      return descricao;
  }  
}
