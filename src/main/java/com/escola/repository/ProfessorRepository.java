package com.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escola.model.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

  @Query("select p from Professor p where p.nome like ?1")
  List<Professor> obterPorNome(String nome);  
}
