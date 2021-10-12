package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.TurmaBusiness;
import com.escola.business.enums.CodBusinessTurma;
import com.escola.converters.TurmaConverter;
import com.escola.dto.MensagemDTO;
import com.escola.dto.TurmaDTO;
import com.escola.model.Turma;
import com.escola.repository.TurmaRepository;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository repository;
  
  @Autowired
  private TurmaConverter converter;

  @Autowired
  private TurmaAlunoService taservice;  
  
  public ResponseEntity<List<Turma>> findAll() {
    List<Turma> turmas = (List<Turma>)repository.findAll();
    if (turmas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
    }
    return new ResponseEntity<>(turmas, HttpStatus.OK);    
  }
  
  public ResponseEntity<Turma> findId(Long id) {
    if (id != null) {
      Optional<Turma> optional = repository.findById(id);
      if (optional.isPresent()) {
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<Turma>> findNome(String nome) {
    if (nome != null) {
      List<Turma> turmas = repository.obterPorNome("%" + nome + "%");
      if (turmas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
      }
      return new ResponseEntity<>(turmas, HttpStatus.OK);    
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemDTO> add(TurmaDTO turmaDTO) {
    try {
      CodBusinessTurma codBusiness = TurmaBusiness.verificar(turmaDTO, 'I', repository);
      if (codBusiness == CodBusinessTurma.OK) {
        repository.save(converter.convertToEntity(turmaDTO));
        return new ResponseEntity<>(new MensagemDTO(CodBusinessTurma.INCLUIDO_OK), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemDTO> update(Long id, TurmaDTO turmaDTO) {
    Optional<Turma> optional = repository.findById(id);
    if (optional.isPresent()) {
      CodBusinessTurma codBusiness = TurmaBusiness.verificar(turmaDTO, 'A', repository);
      if (codBusiness == CodBusinessTurma.OK) {
        Turma obj = converter.convertToEntity(turmaDTO);
        obj.setId(id);
        repository.save(obj);
        return new ResponseEntity<>(new MensagemDTO(CodBusinessTurma.ALTERADO_OK), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemDTO> delete(Long id) {
    try {
      taservice.delete(id);
      repository.deleteById(id);
      return new ResponseEntity<>(new MensagemDTO(CodBusinessTurma.EXCLUIDO_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemDTO> deleteAll() {
    try {
      taservice.deleteAll();
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemDTO(CodBusinessTurma.EXCLUIDO_ALL_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
