package com.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escola.model.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {

  @Query("select c from Curso c where c.nome like ?1")
  List<Curso> obterPorNome(String nome); 

}
