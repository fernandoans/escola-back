package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.FuncoesBusiness;
import com.escola.business.ProfessorBusiness;
import com.escola.business.enums.CodBusinessProfessor;
import com.escola.model.Professor;
import com.escola.pojo.MensagemPojo;
import com.escola.pojo.ProfessorPojo;
import com.escola.repository.ProfessorRepository;

@Service
public class ProfessorService {

  @Autowired
  private ProfessorRepository repository;
  
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
  
  public ResponseEntity<MensagemPojo> add(ProfessorPojo professorPojo) {
    try {
      CodBusinessProfessor codBusiness = ProfessorBusiness.verificar(professorPojo);
      if (codBusiness == CodBusinessProfessor.OK) {
        repository.save(adaptarPojo(professorPojo));
        return new ResponseEntity<>(new MensagemPojo("Professor criado com sucesso."), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemPojo(codBusiness.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemPojo> update(Integer matricula, ProfessorPojo professorPojo) {
    Optional<Professor> optional = repository.findById(matricula);
    if (optional.isPresent()) {
      professorPojo.setMatricula("" + matricula);
      CodBusinessProfessor codBusiness = ProfessorBusiness.verificar(professorPojo);
      if (codBusiness == CodBusinessProfessor.OK) {
        repository.save(adaptarPojo(professorPojo));
        return new ResponseEntity<>(new MensagemPojo("Professor modificado com sucesso."), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemPojo(codBusiness.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemPojo> delete(Integer matricula) {
    try {
      repository.deleteById(matricula);
      return new ResponseEntity<>(new MensagemPojo("Professor eliminado com sucesso."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemPojo> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemPojo("Todos os professores eliminados."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  private Professor adaptarPojo(ProfessorPojo professorPojo) {
    Professor professor = new Professor();
    professor.setMatricula(Integer.parseInt(professorPojo.getMatricula()));
    professor.setNome(professorPojo.getNome());
    professor.setDatanascimento(FuncoesBusiness.strToDateSQL(professorPojo.getDatanascimento()));
    professor.setSexo(professorPojo.getSexo());
    professor.setEmail(professorPojo.getEmail());
    professor.setContato(professorPojo.getContato());
    professor.setDisciplina(professorPojo.getDisciplina());
    return professor;
  }
}
