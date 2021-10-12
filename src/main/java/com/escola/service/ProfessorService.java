package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.ProfessorBusiness;
import com.escola.business.enums.CodBusinessProfessor;
import com.escola.converters.ProfessorConverter;
import com.escola.dto.MensagemDTO;
import com.escola.dto.ProfessorDTO;
import com.escola.model.Professor;
import com.escola.repository.ProfessorRepository;

@Service
public class ProfessorService {

  @Autowired
  private ProfessorRepository repository;

  @Autowired
  private ProfessorConverter converter;
  
  public ResponseEntity<List<Professor>> findAll() {
    List<Professor> professores = (List<Professor>)repository.findAll();
    if (professores.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
    }
    return new ResponseEntity<>(professores, HttpStatus.OK);    
  }
  
  public ResponseEntity<Professor> findMatricula(Integer matricula) {
    if (matricula != null) {
      Optional<Professor> optional = repository.findById(matricula);
      if (optional.isPresent()) {
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<Professor>> findNome(String nome) {
    if (nome != null) {
      List<Professor> professores = repository.obterPorNome("%" + nome + "%");
      if (professores.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
      }
      return new ResponseEntity<>(professores, HttpStatus.OK);    
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemDTO> add(ProfessorDTO professorDTO) {
    try {
      CodBusinessProfessor codBusiness = ProfessorBusiness.verificar(professorDTO);
      if (codBusiness == CodBusinessProfessor.OK) {
        repository.save(converter.convertToEntity(professorDTO));
        return new ResponseEntity<>(new MensagemDTO(CodBusinessProfessor.INCLUIDO_OK), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemDTO> update(Integer matricula, ProfessorDTO professorDTO) {
    Optional<Professor> optional = repository.findById(matricula);
    if (optional.isPresent()) {
      professorDTO.setMatricula("" + matricula);
      CodBusinessProfessor codBusiness = ProfessorBusiness.verificar(professorDTO);
      if (codBusiness == CodBusinessProfessor.OK) {
        repository.save(converter.convertToEntity(professorDTO));
        return new ResponseEntity<>(new MensagemDTO(CodBusinessProfessor.ALTERADO_OK), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemDTO> delete(Integer matricula) {
    try {
      repository.deleteById(matricula);
      return new ResponseEntity<>(new MensagemDTO(CodBusinessProfessor.EXCLUIDO_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemDTO> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemDTO(CodBusinessProfessor.EXCLUIDO_ALL_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
