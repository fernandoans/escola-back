package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.FuncoesBusiness;
import com.escola.model.Curso;
import com.escola.model.Professor;
import com.escola.pojo.CursoPojo;
import com.escola.pojo.MensagemPojo;
import com.escola.repository.CursoRepository;
import com.escola.repository.ProfessorRepository;

@Service
public class CursoService {

  @Autowired
  private CursoRepository repository;
  
  @Autowired
  private ProfessorRepository profRepository;

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
  
  public ResponseEntity<MensagemPojo> add(CursoPojo cursoPojo) {
    try {
      repository.save(adaptarPojo(cursoPojo));
      return new ResponseEntity<>(new MensagemPojo("Curso criado com sucesso."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemPojo> update(Long id, CursoPojo cursoPojo) {
    Optional<Curso> optional = repository.findById(id);
    if (optional.isPresent()) {
      Curso obj = adaptarPojo(cursoPojo);
      obj.setId(id);
      repository.save(obj);
      return new ResponseEntity<>(new MensagemPojo("Curso modificado com sucesso."), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemPojo> delete(Long id) {
    try {
      repository.deleteById(id);
      return new ResponseEntity<>(new MensagemPojo("Curso eliminado com sucesso."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemPojo> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemPojo("Todos os cursos eliminados."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  private Curso adaptarPojo(CursoPojo cursoPojo) {
    Curso curso = new Curso();
    curso.setNome(cursoPojo.getNome());
    curso.setDescricao(cursoPojo.getDescricao());
    curso.setCargahoraria(Integer.parseInt(cursoPojo.getCargahoraria()));
    curso.setQtdalunos(Integer.parseInt(cursoPojo.getQtdalunos()));
    curso.setDatainicio(FuncoesBusiness.strToDateSQL(cursoPojo.getDatainicio()));
    curso.setNumsala(Integer.parseInt(cursoPojo.getNumsala()));
    
    // Obter o professor
    Professor p = null; 
    Optional<Professor> optional = profRepository.findById(Integer.parseInt(cursoPojo.getProfessor().getMatricula()));
    if (optional.isPresent()) {
      p = optional.get();
    }
    curso.setProfessor(p);
    return curso;
  }
}
