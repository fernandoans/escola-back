package com.escola.business;

import java.util.List;

import com.escola.business.enums.CodBusinessTurma;
import com.escola.model.Turma;
import com.escola.pojo.TurmaPojo;
import com.escola.repository.TurmaRepository;

public class TurmaBusiness {
  
  private static TurmaRepository repository;

  private TurmaBusiness() {
  }

  public static CodBusinessTurma verificar(TurmaPojo turma, char tipo, TurmaRepository tRep) {
    repository = tRep;
    
    if (turma.getProfessor() == null || turma.getProfessor().getMatricula() == null) {
      return CodBusinessTurma.PROFESSOR_OBRIGATORIO;
    }
    if (turma.getCurso() == null || turma.getCurso().getId() == null) {
      return CodBusinessTurma.CURSO_OBRIGATORIO;
    }
    
    if (FuncoesBusiness.naoContemValor(turma.getNome())) {
      return CodBusinessTurma.NOME_OBRIGATORIO;
    }
    if (FuncoesBusiness.naoContemValor(turma.getCargahoraria())) {
      return CodBusinessTurma.CARGA_HORARIA_OBRIGATORIA;
    }
    if (FuncoesBusiness.naoContemValor(turma.getNumsala())) {
      return CodBusinessTurma.SALA_OBRIGATORIA;
    }
    
    if (FuncoesBusiness.naoContemValorData(turma.getDatainicio())) {
      return CodBusinessTurma.DATA_MALFORMADA;
    }
    if (FuncoesBusiness.naoContemValorData(turma.getDatatermino())) {
      return CodBusinessTurma.DATA_MALFORMADA;
    }
    if (FuncoesBusiness.interDatasErrado(turma.getDatainicio(), turma.getDatatermino())) {
      return CodBusinessTurma.INTERVALO_ERRADO;
    }
    
    if (professorMesmaTurma(turma.getProfessor().getMatricula(), turma.getDatainicio(), turma.getDatatermino(), tipo)) {
      return CodBusinessTurma.PROFESSOR_ERRADO;
    }
    return CodBusinessTurma.OK;
  }

  private static boolean professorMesmaTurma(String professor, String dataInicio, String dataFim, char tipo) {
    List<Turma> turmas = repository.obterPorProfessor(Integer.parseInt(professor), FuncoesBusiness.strToDateSQL(dataInicio), FuncoesBusiness.strToDateSQL(dataFim));
    if (tipo == 'I') {
      return !turmas.isEmpty();
    }
    if (tipo == 'A') {
      return (!turmas.isEmpty()) && (turmas.size() > 1);
    }
    return false;
  }
}
