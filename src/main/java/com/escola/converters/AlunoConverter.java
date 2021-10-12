package com.escola.converters;

import org.springframework.stereotype.Service;

import com.escola.business.FuncoesBusiness;
import com.escola.dto.AlunoDTO;
import com.escola.model.Aluno;

@Service
public class AlunoConverter {

  public Aluno convertToEntity(AlunoDTO alunoDTO) {
    Aluno aluno = new Aluno();
    aluno.setMatricula(Integer.parseInt(alunoDTO.getMatricula()));
    aluno.setNome(alunoDTO.getNome());
    aluno.setDatanascimento(FuncoesBusiness.strToDateSQL(alunoDTO.getDatanascimento()));
    aluno.setSexo(alunoDTO.getSexo());
    aluno.setEmail(alunoDTO.getEmail());
    aluno.setContato(alunoDTO.getContato());
    return aluno;
  }

}
