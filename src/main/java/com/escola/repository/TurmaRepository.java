package com.escola.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escola.model.Turma;

@Repository
public interface TurmaRepository extends CrudRepository<Turma, Long> {

  @Query("select t from Turma t where t.nome like ?1")
  List<Turma> obterPorNome(String nome);  
    
  @Query(
      "select t from Turma t where t.professor.matricula = ?1 and (" +
          "(t.datainicio <= ?2 and t.datatermino >= ?2) or (t.datainicio <= ?3 and t.datatermino >= ?3) or" +
          "(t.datainicio >= ?2 and t.datatermino <= ?3))"
  )
  List<Turma> obterPorProfessor(Integer professor, Date datInicio, Date dataFim);   
}
