package com.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.model.Turma;
import com.escola.pojo.MensagemPojo;
import com.escola.pojo.TurmaPojo;
import com.escola.service.TurmaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/turma")
public class TurmaController {
  
  @Autowired
  private TurmaService service;

  @ApiOperation(value = "Retornar uma lista com todas as turmas")
  @GetMapping(value="/", produces="application/json")
  public ResponseEntity<List<Turma>> findAll() {
    return service.findAll();
  }

  @ApiOperation(value = "Retornar uma turma pela chave")
  @GetMapping(value="/{id}", produces="application/json")
  public ResponseEntity<Turma> findId(@PathVariable Long id) {
    return service.findId(id);
  }

  @ApiOperation(value = "Retornar uma turma pelo Nome")
  @GetMapping(value="/nome/{nome}", produces="application/json")
  public ResponseEntity<List<Turma>> findNome(@PathVariable String nome) {
    return service.findNome(nome);
  }

  @ApiOperation(value = "Adicionar uma turma")
  @PostMapping("/")
  public ResponseEntity<MensagemPojo> adicionarTurma(@RequestBody TurmaPojo turma) {
    return service.add(turma);
  }

  @ApiOperation(value = "Modificar uma turma")
  @PutMapping("/{id}")
  public ResponseEntity<MensagemPojo> modificarTurma(@PathVariable("id") Long id, @RequestBody TurmaPojo turma) {
    return service.update(id, turma);
  }

  @ApiOperation(value = "Eliminar uma turma")
  @DeleteMapping("/{id}")
  public ResponseEntity<MensagemPojo> deleteTurma(@PathVariable("id") Long id) {
    return service.delete(id);
  }

  @ApiOperation(value = "Eliminar todas as turmas")
  @DeleteMapping("/")
  public ResponseEntity<MensagemPojo> deleteAll() {
    return service.deleteAll();
  }
}
