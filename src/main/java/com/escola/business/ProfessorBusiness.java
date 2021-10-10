package com.escola.business;

import com.escola.business.enums.CodBusinessProfessor;
import com.escola.pojo.ProfessorPojo;

public class ProfessorBusiness {

  private ProfessorBusiness() {
  }

  public static CodBusinessProfessor verificar(ProfessorPojo professor) {
    if (FuncoesBusiness.naoContemValor(professor.getMatricula())) {
      return CodBusinessProfessor.MATRICULA_OBRIGATORIA;
    }
    if (FuncoesBusiness.naoContemValor(professor.getNome())) {
      return CodBusinessProfessor.NOME_OBRIGATORIO;
    }
    if (FuncoesBusiness.naoContemValor(professor.getEmail())) {
      return CodBusinessProfessor.EMAIL_OBRIGATORIO;
    }
    if (FuncoesBusiness.naoContemValorData(professor.getDatanascimento())) {
      return CodBusinessProfessor.DATA_MALFORMADA;
    }
    return CodBusinessProfessor.OK;
  }
}
