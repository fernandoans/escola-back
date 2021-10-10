package com.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escola.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

  @Query("select a from Aluno a where a.nome like ?1")
  List<Aluno> obterPorNome(String nome);  
}
