package com.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.model.TurmaAluno;
import com.escola.pojo.MensagemPojo;
import com.escola.pojo.TurmaAlunoPojo;
import com.escola.service.TurmaAlunoService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/turmaAluno")
public class TurmaAlunoController {
  
  @Autowired
  private TurmaAlunoService service;

  @ApiOperation(value = "Retornar uma lista com todos os alunos nas turmas")
  @GetMapping(value="/", produces="application/json")
  public ResponseEntity<List<TurmaAluno>> findAll() {
    return service.findAll();
  }

  @ApiOperation(value = "Retornar uma aluno na turma pela chave")
  @GetMapping(value="/{id}", produces="application/json")
  public ResponseEntity<TurmaAluno> findId(@PathVariable Long id) {
    return service.findId(id);
  }

  @ApiOperation(value = "Adicionar um aluno na turma")
  @PostMapping("/")
  public ResponseEntity<MensagemPojo> adicionarTurmaAluno(@RequestBody TurmaAlunoPojo turmaAluno) {
    return service.add(turmaAluno);
  }

  @ApiOperation(value = "Eliminar um aluno na turma")
  @DeleteMapping("/{id}")
  public ResponseEntity<MensagemPojo> deleteTurmaAluno(@PathVariable("id") Long id) {
    return service.delete(id);
  }

  @ApiOperation(value = "Eliminar todos os alunos da turma")
  @DeleteMapping("/")
  public ResponseEntity<MensagemPojo> deleteAll() {
    return service.deleteAll();
  }
}
