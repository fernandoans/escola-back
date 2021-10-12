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

import com.escola.dto.MensagemDTO;
import com.escola.dto.ProfessorDTO;
import com.escola.model.Professor;
import com.escola.service.ProfessorService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/professor")
public class ProfessorController {
  
  @Autowired
  private ProfessorService service;

  @ApiOperation(value = "Retornar uma lista com todos os professores")
  @GetMapping(value="/", produces="application/json")
  public ResponseEntity<List<Professor>> findAll() {
    return service.findAll();
  }

  @ApiOperation(value = "Retornar um professor pela Matrícula")
  @GetMapping(value="/{matricula}", produces="application/json")
  public ResponseEntity<Professor> findMatricula(@PathVariable Integer matricula) {
    return service.findMatricula(matricula);
  }

  @ApiOperation(value = "Retornar um professor pelo Nome")
  @GetMapping(value="/nome/{nome}", produces="application/json")
  public ResponseEntity<List<Professor>> findNome(@PathVariable String nome) {
    return service.findNome(nome);
  }

  @ApiOperation(value = "Adicionar um professor")
  @PostMapping("/")
  public ResponseEntity<MensagemDTO> adicionarProfessor(@RequestBody ProfessorDTO professor) {
    return service.add(professor);
  }

  @ApiOperation(value = "Modificar um professor")
  @PutMapping("/{matricula}")
  public ResponseEntity<MensagemDTO> modificarProfessor(@PathVariable("matricula") Integer matricula, @RequestBody ProfessorDTO professor) {
    return service.update(matricula, professor);
  }

  @ApiOperation(value = "Eliminar um professor")
  @DeleteMapping("/{matricula}")
  public ResponseEntity<MensagemDTO> deleteProfessor(@PathVariable("matricula") Integer matricula) {
    return service.delete(matricula);
  }

  @ApiOperation(value = "Eliminar todos os professores")
  @DeleteMapping("/")
  public ResponseEntity<MensagemDTO> deleteAll() {
    return service.deleteAll();
  }
}
