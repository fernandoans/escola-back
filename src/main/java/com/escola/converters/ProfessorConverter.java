package com.escola.converters;

import org.springframework.stereotype.Service;

import com.escola.business.FuncoesBusiness;
import com.escola.dto.ProfessorDTO;
import com.escola.model.Professor;

@Service
public class ProfessorConverter {

  public Professor convertToEntity(ProfessorDTO professorDTO) {
    Professor professor = new Professor();
    professor.setMatricula(Integer.parseInt(professorDTO.getMatricula()));
    professor.setNome(professorDTO.getNome());
    professor.setDatanascimento(FuncoesBusiness.strToDateSQL(professorDTO.getDatanascimento()));
    professor.setSexo(professorDTO.getSexo());
    professor.setEmail(professorDTO.getEmail());
    professor.setContato(professorDTO.getContato());
    professor.setDisciplina(professorDTO.getDisciplina());
    return professor;
  }
}
