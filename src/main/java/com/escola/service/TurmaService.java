package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.FuncoesBusiness;
import com.escola.business.TurmaBusiness;
import com.escola.business.enums.CodBusinessTurma;
import com.escola.model.Curso;
import com.escola.model.Professor;
import com.escola.model.Turma;
import com.escola.pojo.MensagemPojo;
import com.escola.pojo.TurmaPojo;
import com.escola.repository.CursoRepository;
import com.escola.repository.ProfessorRepository;
import com.escola.repository.TurmaRepository;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository repository;
  
  @Autowired
  private ProfessorRepository profRepository;

  @Autowired
  private CursoRepository curRepository;

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
  
  public ResponseEntity<MensagemPojo> add(TurmaPojo turmaPojo) {
    try {
      CodBusinessTurma codBusiness = TurmaBusiness.verificar(turmaPojo, 'I', repository);
      if (codBusiness == CodBusinessTurma.OK) {
        repository.save(adaptarPojo(turmaPojo));
        return new ResponseEntity<>(new MensagemPojo("Turma criada com sucesso."), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemPojo(codBusiness.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemPojo> update(Long id, TurmaPojo turmaPojo) {
    Optional<Turma> optional = repository.findById(id);
    if (optional.isPresent()) {
      CodBusinessTurma codBusiness = TurmaBusiness.verificar(turmaPojo, 'A', repository);
      if (codBusiness == CodBusinessTurma.OK) {
        Turma obj = adaptarPojo(turmaPojo);
        obj.setId(id);
        repository.save(obj);
        return new ResponseEntity<>(new MensagemPojo("Turma modificada com sucesso."), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemPojo(codBusiness.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  public ResponseEntity<MensagemPojo> delete(Long id) {
    try {
      taservice.delete(id);
      repository.deleteById(id);
      return new ResponseEntity<>(new MensagemPojo("Turma eliminada com sucesso."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemPojo> deleteAll() {
    try {
      taservice.deleteAll();
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemPojo("Todas as turmas eliminadas."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  private Turma adaptarPojo(TurmaPojo turmaPojo) {
    Turma turma = new Turma();
    turma.setNome(turmaPojo.getNome());
    turma.setCargahoraria(Integer.parseInt(turmaPojo.getCargahoraria()));
    turma.setDatainicio(FuncoesBusiness.strToDateSQL(turmaPojo.getDatainicio()));
    turma.setDatatermino(FuncoesBusiness.strToDateSQL(turmaPojo.getDatatermino()));
    turma.setNumsala(Integer.parseInt(turmaPojo.getNumsala()));
    
    // Obter o professor
    Professor p = null; 
    Optional<Professor> optionalProf = profRepository.findById(Integer.parseInt(turmaPojo.getProfessor().getMatricula()));
    if (optionalProf.isPresent()) {
      p = optionalProf.get();
    }
    turma.setProfessor(p);
    
    // Obter o curso
    Curso c = null; 
    Optional<Curso> optionalCurso = curRepository.findById(Long.parseLong(turmaPojo.getCurso().getId()));
    if (optionalCurso.isPresent()) {
      c = optionalCurso.get();
    }
    turma.setCurso(c);

    return turma;
  }
}
