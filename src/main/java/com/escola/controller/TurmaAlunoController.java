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

import com.escola.dto.MensagemDTO;
import com.escola.dto.TurmaAlunoDTO;
import com.escola.model.TurmaAluno;
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

  @ApiOperation(value = "Retornar um aluno na turma pela chave")
  @GetMapping(value="/{id}", produces="application/json")
  public ResponseEntity<TurmaAluno> findId(@PathVariable Long id) {
    return service.findId(id);
  }
  
  @ApiOperation(value = "Retornar os alunos de uma Turma")
  @GetMapping(value="/turma/{id}", produces="application/json")
  public ResponseEntity<List<TurmaAluno>> findTurma(@PathVariable Long id) {
    return service.findTurma(id);
  }

  @ApiOperation(value = "Adicionar um aluno na turma")
  @PostMapping("/")
  public ResponseEntity<MensagemDTO> adicionarTurmaAluno(@RequestBody TurmaAlunoDTO turmaAluno) {
    return service.add(turmaAluno);
  }

  @ApiOperation(value = "Eliminar um aluno na turma")
  @DeleteMapping("/{id}")
  public ResponseEntity<MensagemDTO> deleteTurmaAluno(@PathVariable("id") Long id) {
    return service.delete(id);
  }

  @ApiOperation(value = "Eliminar todos os alunos da turma")
  @DeleteMapping("/")
  public ResponseEntity<MensagemDTO> deleteAll() {
    return service.deleteAll();
  }
}
