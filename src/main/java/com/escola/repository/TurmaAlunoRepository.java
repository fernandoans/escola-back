package com.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escola.model.TurmaAluno;

@Repository
public interface TurmaAlunoRepository extends CrudRepository<TurmaAluno, Long> {

  @Query("select ta from TurmaAluno ta where ta.turma.id = ?1")
  List<TurmaAluno> obterPorTurma(Long turma); 
  
  @Query("delete from TurmaAluno ta where ta.turma = ?1")
  Integer deleteByTurma(Long turma); 

  @Query("select ta from TurmaAluno ta where ta.aluno.matricula = ?1 and ta.turma.id = ?2")
  List<TurmaAluno> obterPorAluno(Integer aluno, Long turma); 

}
