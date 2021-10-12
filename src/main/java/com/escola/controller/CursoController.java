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

import com.escola.dto.CursoDTO;
import com.escola.dto.MensagemDTO;
import com.escola.model.Curso;
import com.escola.service.CursoService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/curso")
public class CursoController {
  
  @Autowired
  private CursoService service;

  @ApiOperation(value = "Retornar uma lista com todos os cursos")
  @GetMapping(value="/", produces="application/json")
  public ResponseEntity<List<Curso>> findAll() {
    return service.findAll();
  }

  @ApiOperation(value = "Retornar um curso pela Chave")
  @GetMapping(value="/{id}", produces="application/json")
  public ResponseEntity<Curso> findId(@PathVariable Long id) {
    return service.findId(id);
  }

  @ApiOperation(value = "Retornar um curso pelo Nome")
  @GetMapping(value="/nome/{nome}", produces="application/json")
  public ResponseEntity<List<Curso>> findNome(@PathVariable String nome) {
    return service.findNome(nome);
  }

  @ApiOperation(value = "Adicionar um curso")
  @PostMapping("/")
  public ResponseEntity<MensagemDTO> adicionarCurso(@RequestBody CursoDTO curso) {
    return service.add(curso);
  }

  @ApiOperation(value = "Modificar um curso")
  @PutMapping("/{id}")
  public ResponseEntity<MensagemDTO> modificarCurso(@PathVariable("id") Long id, @RequestBody CursoDTO curso) {
    return service.update(id, curso);
  }

  @ApiOperation(value = "Eliminar um curso")
  @DeleteMapping("/{id}")
  public ResponseEntity<MensagemDTO> deleteCurso(@PathVariable("id") Long id) {
    return service.delete(id);
  }

  @ApiOperation(value = "Eliminar todos os cursos")
  @DeleteMapping("/")
  public ResponseEntity<MensagemDTO> deleteAll() {
    return service.deleteAll();
  }
}
