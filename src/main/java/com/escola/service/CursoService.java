package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.CursoBusiness;
import com.escola.business.enums.CodBusinessCurso;
import com.escola.converters.CursoConverter;
import com.escola.dto.CursoDTO;
import com.escola.dto.MensagemDTO;
import com.escola.model.Curso;
import com.escola.repository.CursoRepository;

@Service
public class CursoService {

  @Autowired
  private CursoRepository repository;
  
  @Autowired
  private CursoConverter converter;
  
  public ResponseEntity<List<Curso>> findAll() {
    List<Curso> cursos = (List<Curso>)repository.findAll();
    if (cursos.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
    }
    return new ResponseEntity<>(cursos, HttpStatus.OK);    
  }
  
  public ResponseEntity<Curso> findId(Long id) {
    if (id != null) {
      Optional<Curso> optional = repository.findById(id);
      if (optional.isPresent()) {
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<Curso>> findNome(String nome) {
    if (nome != null) {
      List<Curso> cursoes = repository.obterPorNome("%" + nome + "%");
      if (cursoes.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);      
      }
      return new ResponseEntity<>(cursoes, HttpStatus.OK);    
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemDTO> add(CursoDTO cursoDTO) {
    try {
      CodBusinessCurso codBusiness = CursoBusiness.verificar(cursoDTO);
      if (codBusiness == CodBusinessCurso.OK) {
        repository.save(converter.convertToEntity(cursoDTO));
        return new ResponseEntity<>(new MensagemDTO(CodBusinessCurso.INCLUIDO_OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemDTO> update(Long id, CursoDTO cursoDTO) {
    Optional<Curso> optional = repository.findById(id);
    if (optional.isPresent()) {
      cursoDTO.setId(""+id);
      CodBusinessCurso codBusiness = CursoBusiness.verificar(cursoDTO);
      if (codBusiness == CodBusinessCurso.OK) {
        Curso obj = converter.convertToEntity(cursoDTO);
        obj.setId(id);
        repository.save(obj);
        return new ResponseEntity<>(new MensagemDTO(CodBusinessCurso.ALTERADO_OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemDTO> delete(Long id) {
    try {
      repository.deleteById(id);
      return new ResponseEntity<>(new MensagemDTO(CodBusinessCurso.EXCLUIDO_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemDTO> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemDTO(CodBusinessCurso.EXCLUIDO_ALL_OK), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
