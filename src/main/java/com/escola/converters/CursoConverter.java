package com.escola.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.business.FuncoesBusiness;
import com.escola.dto.CursoDTO;
import com.escola.model.Curso;
import com.escola.model.Professor;
import com.escola.repository.ProfessorRepository;

@Service
public class CursoConverter {

  @Autowired
  private ProfessorRepository profRepository;

  public Curso convertToEntity(CursoDTO cursoDTO) {
    Curso curso = new Curso();
    curso.setNome(cursoDTO.getNome());
    curso.setDescricao(cursoDTO.getDescricao());
    curso.setCargahoraria(Integer.parseInt(cursoDTO.getCargahoraria()));
    curso.setQtdalunos(Integer.parseInt(cursoDTO.getQtdalunos()));
    curso.setDatainicio(FuncoesBusiness.strToDateSQL(cursoDTO.getDatainicio()));
    curso.setNumsala(Integer.parseInt(cursoDTO.getNumsala()));

    // Obter o professor
    Professor p = null;
    Optional<Professor> optional = profRepository.findById(Integer.parseInt(cursoDTO.getProfessor().getMatricula()));
    if (optional.isPresent()) {
      p = optional.get();
    }
    curso.setProfessor(p);
    return curso;
  }

}
