package com.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escola.business.AlunoBusiness;
import com.escola.business.FuncoesBusiness;
import com.escola.business.enums.CodBusinessAluno;
import com.escola.model.Aluno;
import com.escola.pojo.AlunoPojo;
import com.escola.pojo.MensagemPojo;
import com.escola.repository.AlunoRepository;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository repository;

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

  public ResponseEntity<MensagemPojo> add(AlunoPojo alunoPojo) {
    try {
      CodBusinessAluno codBusiness = AlunoBusiness.verificar(alunoPojo);
      if (codBusiness == CodBusinessAluno.OK) {
        repository.save(adaptarPojo(alunoPojo));
        return new ResponseEntity<>(new MensagemPojo("Aluno criado com sucesso."), HttpStatus.OK);
      }
      return new ResponseEntity<>(new MensagemPojo(codBusiness.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<MensagemPojo> update(Integer matricula, AlunoPojo alunoPojo) {
    Optional<Aluno> optional = repository.findById(matricula);
    if (optional.isPresent()) {
      alunoPojo.setMatricula("" + matricula);
      CodBusinessAluno codBusiness = AlunoBusiness.verificar(alunoPojo);
      if (codBusiness == CodBusinessAluno.OK) {
        repository.save(adaptarPojo(alunoPojo));
        return new ResponseEntity<>(new MensagemPojo("Aluno modificado com sucesso."), HttpStatus.OK);
      }
      return new ResponseEntity<>(new MensagemPojo(codBusiness.getDescricao()), HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<MensagemPojo> delete(Integer matricula) {
    try {
      repository.deleteById(matricula);
      return new ResponseEntity<>(new MensagemPojo("Aluno eliminado com sucesso."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<MensagemPojo> deleteAll() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(new MensagemPojo("Todos os alunos eliminados."), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Aluno adaptarPojo(AlunoPojo alunoPojo) {
    Aluno aluno = new Aluno();
    aluno.setMatricula(Integer.parseInt(alunoPojo.getMatricula()));
    aluno.setNome(alunoPojo.getNome());
    aluno.setDatanascimento(FuncoesBusiness.strToDateSQL(alunoPojo.getDatanascimento()));
    aluno.setSexo(alunoPojo.getSexo());
    aluno.setEmail(alunoPojo.getEmail());
    aluno.setContato(alunoPojo.getContato());
    return aluno;
  }
}
