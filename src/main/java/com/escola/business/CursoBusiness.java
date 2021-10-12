package com.escola.business;

import com.escola.business.enums.CodBusinessCurso;
import com.escola.dto.CursoDTO;

public class CursoBusiness {

  private CursoBusiness() {
  }

  public static CodBusinessCurso verificar(CursoDTO curso) {
    if (FuncoesBusiness.naoContemValor(curso.getNome())) {
      return CodBusinessCurso.NOME_OBRIGATORIO;
    }
    if (FuncoesBusiness.naoContemValor(curso.getCargahoraria())) {
      return CodBusinessCurso.CARGA_HORARIA_OBRIGATORIA;
    }
    if (FuncoesBusiness.naoContemValorData(curso.getDatainicio())) {
      return CodBusinessCurso.DATA_MALFORMADA;
    }
    return CodBusinessCurso.OK;
  }
}
