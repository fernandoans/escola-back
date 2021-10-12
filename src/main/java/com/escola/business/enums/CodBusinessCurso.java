package com.escola.business.enums;

public enum CodBusinessCurso {
  OK("Sem problemas."),
  INCLUIDO_OK("Curso criado com sucesso."),
  ALTERADO_OK("Curso modificado com sucesso."),
  EXCLUIDO_OK("Curso eliminado com sucesso."),
  EXCLUIDO_ALL_OK("Todos os cursos eliminados."),
  NOME_OBRIGATORIO("Nome não foi informado."),
  CARGA_HORARIA_OBRIGATORIA("Carga Horária não foi informada."),
  DATA_MALFORMADA("Data deve estar no padrão DD/MM/AAAA.");
  
  private String descricao;

  CodBusinessCurso(String descricao) {
      this.descricao = descricao;
  }

  public String getDescricao() {
      return descricao;
  }  
}
