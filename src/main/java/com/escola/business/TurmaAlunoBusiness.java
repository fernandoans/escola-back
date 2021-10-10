package com.escola.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.escola.business.enums.CodBusinessTurmaAluno;
import com.escola.model.TurmaAluno;
import com.escola.pojo.TurmaAlunoPojo;
import com.escola.repository.TurmaAlunoRepository;

public class TurmaAlunoBusiness {

  @Autowired
  private static TurmaAlunoRepository repository;

  private TurmaAlunoBusiness() {
  }
  
  public static CodBusinessTurmaAluno verificar(TurmaAlunoPojo turmaAluno, char tipo, TurmaAlunoRepository taRep) {
    repository = taRep;
    if (turmaAluno.getTurma() == null || turmaAluno.getTurma().getId() == null) {
      return CodBusinessTurmaAluno.TURMA_OBRIGATORIA;
    }
    if (turmaAluno.getAluno() == null || turmaAluno.getAluno().getMatricula() == null) {
      return CodBusinessTurmaAluno.ALUNO_OBRIGATORIO;
    }
    
    if (alunoMesmaTurma(turmaAluno.getAluno().getMatricula(), turmaAluno.getTurma().getId(), tipo)) {
      return CodBusinessTurmaAluno.ALUNO_DUPLICADO;
    }

    return CodBusinessTurmaAluno.OK;
  }

  private static boolean alunoMesmaTurma(String aluno, String turma, char tipo) {
    List<TurmaAluno> turmas = repository.obterPorAluno(Integer.parseInt(aluno), Long.parseLong(turma));
    if (tipo == 'I') {
      return !turmas.isEmpty();
    }
    if (tipo == 'A') {
      return (!turmas.isEmpty()) && (turmas.size() > 1);
    }
    return false;
  }
}
