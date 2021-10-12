package com.escola.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.business.FuncoesBusiness;
import com.escola.dto.TurmaDTO;
import com.escola.model.Curso;
import com.escola.model.Professor;
import com.escola.model.Turma;
import com.escola.repository.CursoRepository;
import com.escola.repository.ProfessorRepository;

@Service
public class TurmaConverter {
  
  @Autowired
  private ProfessorRepository profRepository;

  @Autowired
  private CursoRepository curRepository;

  public Turma convertToEntity(TurmaDTO turmaDTO) {
    Turma turma = new Turma();
    turma.setNome(turmaDTO.getNome());
    turma.setCargahoraria(Integer.parseInt(turmaDTO.getCargahoraria()));
    turma.setDatainicio(FuncoesBusiness.strToDateSQL(turmaDTO.getDatainicio()));
    turma.setDatatermino(FuncoesBusiness.strToDateSQL(turmaDTO.getDatatermino()));
    turma.setNumsala(Integer.parseInt(turmaDTO.getNumsala()));
    
    // Obter o professor
    Professor p = null; 
    Optional<Professor> optionalProf = profRepository.findById(Integer.parseInt(turmaDTO.getProfessor().getMatricula()));
    if (optionalProf.isPresent()) {
      p = optionalProf.get();
    }
    turma.setProfessor(p);
    
    // Obter o curso
    Curso c = null; 
    Optional<Curso> optionalCurso = curRepository.findById(Long.parseLong(turmaDTO.getCurso().getId()));
    if (optionalCurso.isPresent()) {
      c = optionalCurso.get();
    }
    turma.setCurso(c);

    return turma;
  }
}
