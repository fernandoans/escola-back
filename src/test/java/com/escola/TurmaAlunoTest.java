package com.escola;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.escola.business.enums.CodBusinessAluno;
import com.escola.business.enums.CodBusinessCurso;
import com.escola.business.enums.CodBusinessProfessor;
import com.escola.business.enums.CodBusinessTurma;
import com.escola.business.enums.CodBusinessTurmaAluno;
import com.escola.dto.MensagemDTO;
import com.escola.dto.TurmaAlunoDTO;
import com.escola.model.TurmaAluno;

class TurmaAlunoTest extends PaiTest {

  private static Long idCurso;
  private static Long idTurma;
  private static Long idTurmaAluno;

  public TurmaAlunoTest() {
    super();
  }

  @Test
  @DisplayName("Incluir Relacionamentos")
  @Order(60)
  void testIncluirRelacionamentos() {
    // Incluir Aluno
    ResponseEntity<MensagemDTO> response = alunoCt.adicionarAluno(montarAlunoCompleto());
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.INCLUIDO_OK.getDescricao(), msg), "Aluno não foi incluído");
    // Incluir Professor
    response = professorCt.adicionarProfessor(montarProfessorCompleto());
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.INCLUIDO_OK.getDescricao(), msg), "Professor não foi incluído");
    // Incluir Curso
    response = cursoCt.adicionarCurso(montarCursoCompleto());
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.INCLUIDO_OK.getDescricao(), msg), "Curso não foi incluído");
    // Localizar Curso
    idCurso = obterCursoTeste();
    assertTrue((0L < idCurso), "Curso não foi localizado");
    // Incluir Turma
    response = turmaCt.adicionarTurma(montarTurmaCompleto(idCurso));
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.INCLUIDO_OK.getDescricao(), msg), "Turma não foi incluída");
    idTurma = obterTurmaTeste();
    assertTrue((0L < idTurma), "Turma não foi localizada");
  }

  @Test
  @DisplayName("Incluir Aluno na Turma")
  @Order(61)
  void testIncluirTurmaAluno() {
    TurmaAlunoDTO objDTO = montarTurmaAlunoCompleto(idTurma);
    ResponseEntity<MensagemDTO> response = turmaAlunoCt.adicionarTurmaAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurmaAluno.INCLUIDO_OK.getDescricao(), msg), "Aluno não foi incluído na Turma");
  }

  @Test
  @DisplayName("Localizar Aluno na Turma por Nome e verificar relacionamentos")
  @Order(62)
  void testLocalizarTurmaPorNome() {
    ResponseEntity<List<TurmaAluno>> responseFind = turmaAlunoCt.findTurma(idTurma);
    List<TurmaAluno> alunos = responseFind.getBody();
    assertNotNull(alunos, "Não encontrado o aluno na turma");
    assertNotEquals(0, alunos.size(), "Lista de alunos vazia");
    idTurmaAluno = alunos.get(0).getId();
    assertNotNull(alunos.get(0).getTurma(), "Não encontrada a Turma");
    assertNotNull(alunos.get(0).getAluno(), "Não encontrado o Aluno");
  }

  @Test
  @DisplayName("Cadastrar o mesmo aluno na turma")
  @Order(63)
  void testIncluirConcomitante() {
    TurmaAlunoDTO objDTO = montarTurmaAlunoCompleto(idTurma);
    ResponseEntity<MensagemDTO> response = turmaAlunoCt.adicionarTurmaAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurmaAluno.ALUNO_DUPLICADO.getDescricao(), msg), "Aluno foi incluído na Turma");
  }

  @Test
  @DisplayName("Eliminar Aluno da Turma")
  @Order(64)
  void testEliminarTurma() {
    ResponseEntity<MensagemDTO> response = turmaAlunoCt.deleteTurmaAluno(idTurmaAluno);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurmaAluno.EXCLUIDO_OK.getDescricao(), msg), "Aluno não foi excluído da Turma");
  }

  @Test
  @DisplayName("Eliminar Relacionamentos")
  @Order(65)
  void testEliminarRelacionamentos() {
    // Eliminar Turma
    ResponseEntity<MensagemDTO> response = turmaCt.deleteTurma(idTurma);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.EXCLUIDO_OK.getDescricao(), msg), "Turma não foi excluída");
    // Eliminar Curso
    response = cursoCt.deleteCurso(idCurso);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.EXCLUIDO_OK.getDescricao(), msg), "Curso não foi excluído");
    // Eliminar Professor
    response = professorCt.deleteProfessor(MATRICULA_PROFESSOR);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.EXCLUIDO_OK.getDescricao(), msg), "Professor não foi excluído");
    // Eliminar Aluno
    response = alunoCt.deleteAluno(MATRICULA_ALUNO);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.EXCLUIDO_OK.getDescricao(), msg), "Aluno não foi excluído");
  }

}
