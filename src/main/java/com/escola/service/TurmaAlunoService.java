package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.TurmaAlunoBusiness;
import com.escola.business.enums.CodBusinessTurmaAluno;
import com.escola.converters.TurmaAlunoConverter;
import com.escola.dto.MensagemDTO;
import com.escola.dto.TurmaAlunoDTO;
import com.escola.model.TurmaAluno;
import com.escola.repository.TurmaAlunoRepository;

@Service
public class TurmaAlunoService {

  @Autowired
  private TurmaAlunoRepository repository;

  @Autowired
  private TurmaAlunoConverter converter;
  
  public ResponseEntity<List<TurmaAluno>> findAll() {
    List<TurmaAluno> turmas = (List<TurmaAluno>)repository.findAll();
    if (turmas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
    }
    return new ResponseEntity<>(turmas, HttpStatus.OK);    
  }
  
  public ResponseEntity<TurmaAluno> findId(Long id) {
    if (id != null) {
      Optional<TurmaAluno> optional = repository.findById(id);
      if (optional.isPresent()) {
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<TurmaAluno>> findTurma(Long id) {
    if (id != null) {
      List<TurmaAluno> alunos = repository.obterPorTurma(id);
      if (alunos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
      }
      return new ResponseEntity<>(alunos, HttpStatus.OK);    
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<MensagemDTO> add(TurmaAlunoDTO turmaAlunoDTO) {
    try {
      CodBusinessTurmaAluno codBusiness = TurmaAlunoBusiness.verificar(turmaAlunoDTO, 'I', repository);
      if (codBusiness == CodBusinessTurmaAluno.OK) {
        repository.save(converter.convertToEntity(turmaAlunoDTO));
        return new ResponseEntity<>(new MensagemDTO(CodBusinessTurmaAluno.INCLUIDO_OK), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemDTO> delete(Long id) {
    try {
      repository.deleteById(id);
      return new ResponseEntity<>(new MensagemDTO(CodBusinessTurmaAluno.EXCLUIDO_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemDTO> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemDTO(CodBusinessTurmaAluno.EXCLUIDO_ALL_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
