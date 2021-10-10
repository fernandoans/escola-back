package com.escola.business;

import com.escola.business.enums.CodBusinessAluno;
import com.escola.pojo.AlunoPojo;

public class AlunoBusiness {

  private AlunoBusiness() {
  }

  public static CodBusinessAluno verificar(AlunoPojo aluno) {
    if (FuncoesBusiness.naoContemValor(aluno.getMatricula())) {
      return CodBusinessAluno.MATRICULA_OBRIGATORIA;
    }
    if (FuncoesBusiness.naoContemValor(aluno.getNome())) {
      return CodBusinessAluno.NOME_OBRIGATORIO;
    }
    if (FuncoesBusiness.naoContemValor(aluno.getEmail())) {
      return CodBusinessAluno.EMAIL_OBRIGATORIO;
    }
    if (FuncoesBusiness.naoContemValorData(aluno.getDatanascimento())) {
      return CodBusinessAluno.DATA_MALFORMADA;
    }
    return CodBusinessAluno.OK;
  }
}
