package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.TurmaAlunoBusiness;
import com.escola.business.enums.CodBusinessTurmaAluno;
import com.escola.model.Aluno;
import com.escola.model.Turma;
import com.escola.model.TurmaAluno;
import com.escola.pojo.MensagemPojo;
import com.escola.pojo.TurmaAlunoPojo;
import com.escola.repository.AlunoRepository;
import com.escola.repository.TurmaAlunoRepository;
import com.escola.repository.TurmaRepository;

@Service
public class TurmaAlunoService {

  @Autowired
  private TurmaAlunoRepository repository;
  
  @Autowired
  private AlunoRepository aluRepository;

  @Autowired
  private TurmaRepository turRepository;
  
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

  public ResponseEntity<MensagemPojo> add(TurmaAlunoPojo turmaAlunoPojo) {
    try {
      CodBusinessTurmaAluno codBusiness = TurmaAlunoBusiness.verificar(turmaAlunoPojo, 'I', repository);
      if (codBusiness == CodBusinessTurmaAluno.OK) {
        repository.save(adaptarPojo(turmaAlunoPojo));
        return new ResponseEntity<>(new MensagemPojo("Turma criada com sucesso."), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemPojo(codBusiness.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

  public ResponseEntity<MensagemPojo> delete(Long id) {
    try {
      repository.deleteById(id);
      return new ResponseEntity<>(new MensagemPojo("Turma eliminada com sucesso."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  public ResponseEntity<MensagemPojo> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemPojo("Todas as turmas eliminadas."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  private TurmaAluno adaptarPojo(TurmaAlunoPojo turmaAlunoPojo) {
    TurmaAluno turmaAluno = new TurmaAluno();

    // Obter o Turma
    Turma t = null; 
    Optional<Turma> optionalTurma = turRepository.findById(Long.parseLong(turmaAlunoPojo.getTurma().getId()));
    if (optionalTurma.isPresent()) {
      t = optionalTurma.get();
    }
    turmaAluno.setTurma(t);

    // Obter o Aluno
    Aluno a = null; 
    Optional<Aluno> optionalAluno = aluRepository.findById(Integer.parseInt(turmaAlunoPojo.getAluno().getMatricula()));
    if (optionalAluno.isPresent()) {
      a = optionalAluno.get();
    }
    turmaAluno.setAluno(a);

    return turmaAluno;
  }
}
