package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.AlunoBusiness;
import com.escola.business.enums.CodBusinessAluno;
import com.escola.converters.AlunoConverter;
import com.escola.dto.AlunoDTO;
import com.escola.dto.MensagemDTO;
import com.escola.model.Aluno;
import com.escola.repository.AlunoRepository;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository repository;
  
  @Autowired
  private AlunoConverter converter;

  public ResponseEntity<List<Aluno>> findAll() {
    List<Aluno> alunos = (List<Aluno>) repository.findAll();
    if (alunos.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(alunos, HttpStatus.OK);
  }

  public ResponseEntity<Aluno> findMatricula(Integer matricula) {
    if (matricula != null) {
      Optional<Aluno> optional = repository.findById(matricula);
      if (optional.isPresent()) {
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<Aluno>> findNome(String nome) {
    if (nome != null) {
      List<Aluno> alunos = repository.obterPorNome("%" + nome + "%");
      if (alunos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<MensagemDTO> add(AlunoDTO alunoDTO) {
    try {
      CodBusinessAluno codBusiness = AlunoBusiness.verificar(alunoDTO);
      if (codBusiness == CodBusinessAluno.OK) {
        repository.save(converter.convertToEntity(alunoDTO));
        return new ResponseEntity<>(new MensagemDTO(CodBusinessAluno.INCLUIDO_OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<MensagemDTO> update(Integer matricula, AlunoDTO alunoDTO) {
    Optional<Aluno> optional = repository.findById(matricula);
    if (optional.isPresent()) {
      alunoDTO.setMatricula("" + matricula);
      CodBusinessAluno codBusiness = AlunoBusiness.verificar(alunoDTO);
      if (codBusiness == CodBusinessAluno.OK) {
        repository.save(converter.convertToEntity(alunoDTO));
        return new ResponseEntity<>(new MensagemDTO(CodBusinessAluno.ALTERADO_OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<MensagemDTO> delete(Integer matricula) {
    try {
      repository.deleteById(matricula);
      return new ResponseEntity<>(new MensagemDTO(CodBusinessAluno.EXCLUIDO_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<MensagemDTO> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemDTO(CodBusinessAluno.EXCLUIDO_ALL_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
