
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

import com.escola.model.Aluno;
import com.escola.pojo.AlunoPojo;
import com.escola.pojo.MensagemPojo;
import com.escola.service.AlunoService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {
  
  @Autowired
  private AlunoService service;

  @ApiOperation(value = "Retornar uma lista com todos os alunos")
  @GetMapping(value="/", produces="application/json")
  public ResponseEntity<List<Aluno>> findAll() {
    return service.findAll();
  }

  @ApiOperation(value = "Retornar um aluno pela Matrícula")
  @GetMapping(value="/{matricula}", produces="application/json")
  public ResponseEntity<Aluno> findMatricula(@PathVariable Integer matricula) {
    return service.findMatricula(matricula);
  }

  @ApiOperation(value = "Retornar um aluno pelo Nome")
  @GetMapping(value="/nome/{nome}", produces="application/json")
  public ResponseEntity<List<Aluno>> findNome(@PathVariable String nome) {
    return service.findNome(nome);
  }

  @ApiOperation(value = "Adicionar um aluno")
  @PostMapping("/")
  public ResponseEntity<MensagemPojo> adicionarAluno(@RequestBody AlunoPojo aluno) {
    return service.add(aluno);
  }

  @ApiOperation(value = "Modificar um aluno")
  @PutMapping("/{matricula}")
  public ResponseEntity<MensagemPojo> modificarAluno(@PathVariable("matricula") Integer matricula, @RequestBody AlunoPojo aluno) {
    return service.update(matricula, aluno);
  }

  @ApiOperation(value = "Eliminar um aluno")
  @DeleteMapping("/{matricula}")
  public ResponseEntity<MensagemPojo> deleteAluno(@PathVariable("matricula") Integer matricula) {
    return service.delete(matricula);
  }

  @ApiOperation(value = "Eliminar todos os alunos")
  @DeleteMapping("/")
  public ResponseEntity<MensagemPojo> deleteAll() {
    return service.deleteAll();
  }
}
