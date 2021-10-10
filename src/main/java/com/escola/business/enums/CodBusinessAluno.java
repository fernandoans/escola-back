package com.escola.business.enums;

public enum CodBusinessAluno {
  OK("Sem problemas."),
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
