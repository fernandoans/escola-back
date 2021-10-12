package com.escola.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.dto.TurmaAlunoDTO;
import com.escola.model.Aluno;
import com.escola.model.Turma;
import com.escola.model.TurmaAluno;
import com.escola.repository.AlunoRepository;
import com.escola.repository.TurmaRepository;

@Service
public class TurmaAlunoConverter {

  @Autowired
  private AlunoRepository aluRepository;

  @Autowired
  private TurmaRepository turRepository;

  public TurmaAluno convertToEntity(TurmaAlunoDTO turmaAlunoDTO) {
    TurmaAluno turmaAluno = new TurmaAluno();

    // Obter o Turma
    Turma t = null;
    Optional<Turma> optionalTurma = turRepository.findById(Long.parseLong(turmaAlunoDTO.getTurma().getId()));
    if (optionalTurma.isPresent()) {
      t = optionalTurma.get();
    }
    turmaAluno.setTurma(t);

    // Obter o Aluno
    Aluno a = null;
    Optional<Aluno> optionalAluno = aluRepository.findById(Integer.parseInt(turmaAlunoDTO.getAluno().getMatricula()));
    if (optionalAluno.isPresent()) {
      a = optionalAluno.get();
    }
    turmaAluno.setAluno(a);

    return turmaAluno;
  }

}
