package com.escola.business.enums;

public enum CodBusinessTurma {
  OK("Sem problemas."),
  INTERVALO_ERRADO("A data de término não pode ser menor que a data início."),
  PROFESSOR_OBRIGATORIO("Professor não foi informado."),
  CURSO_OBRIGATORIO("Curso não foi informado."),
  NOME_OBRIGATORIO("Nome não foi informado."),
  CARGA_HORARIA_OBRIGATORIA("Carga Horária não foi informada."),
  SALA_OBRIGATORIA("Sala não foi informada."),
  DATA_MALFORMADA("Data deve estar no padrão DD/MM/AAAA."),
  PROFESSOR_ERRADO("Professor possui turma com vigências concomitantes.");
  
  private String descricao;

  CodBusinessTurma(String descricao) {
      this.descricao = descricao;
  }

  public String getDescricao() {
      return descricao;
  }  
}
