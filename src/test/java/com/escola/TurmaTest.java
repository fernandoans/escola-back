package com.escola;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.escola.business.enums.CodBusinessCurso;
import com.escola.business.enums.CodBusinessProfessor;
import com.escola.business.enums.CodBusinessTurma;
import com.escola.dto.MensagemDTO;
import com.escola.dto.TurmaDTO;
import com.escola.model.Turma;

class TurmaTest extends PaiTest {

  private static Long idCurso;
  private static Long idTurma;

  public TurmaTest() {
    super();
  }

  @Test
  @DisplayName("Incluir Relacionamentos")
  @Order(40)
  void testIncluirRelacionamentos() {
    // Incluir Professor
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(montarProfessorCompleto());
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.INCLUIDO_OK.getDescricao(), msg), "Professor não foi incluído");
    // Incluir Curso
    response = cursoCt.adicionarCurso(montarCursoCompleto());
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.INCLUIDO_OK.getDescricao(), msg), "Curso não foi incluído");
    // Localizar Curso
    idCurso = obterCursoTeste();
    assertTrue((0L < idCurso), "Curso não foi localizado");
  }

  @Test
  @DisplayName("Turma sem nome")
  @Order(41)
  void testTurmaSemNome() {
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    objDTO.setNome("");
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.NOME_OBRIGATORIO.getDescricao(), msg), "Não verificado nome vazio");
  }

  @Test
  @DisplayName("Turma sem carga horária")
  @Order(42)
  void testTurmaSemCargaHoraria() {
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    objDTO.setCargahoraria("");
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.CARGA_HORARIA_OBRIGATORIA.getDescricao(), msg),
        "Não verificada carga horária vazia");
  }

  @Test
  @DisplayName("Turma sem sala")
  @Order(43)
  void testTurmaSemSala() {
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    objDTO.setNumsala("");
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.SALA_OBRIGATORIA.getDescricao(), msg), "Não verificada sala de aula vazia");
  }

  @Test
  @DisplayName("Turma sem data início/término")
  @Order(44)
  void testTurmaSemData() {
    // Testar Data Início
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    objDTO.setDatainicio("");
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data Início vazia");
    // Testar Data Término
    objDTO = montarTurmaCompleto(idCurso);
    objDTO.setDatatermino("");
    response = turmaCt.adicionarTurma(objDTO);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data Término vazia");
  }

  @Test
  @DisplayName("Turma com data início/término errada")
  @Order(45)
  void testTurmaDataErrada() {
    // Testar Data Início
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    objDTO.setDatainicio("2007/12/20");
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data Início mal formada");
    // Testar Data Término
    objDTO = montarTurmaCompleto(idCurso);
    objDTO.setDatatermino("2007/12/20");
    response = turmaCt.adicionarTurma(objDTO);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.DATA_MALFORMADA.getDescricao(), msg),
        "Não verificada Data Término mal formada");
  }

  @Test
  @DisplayName("Turma com intervalo de datas errado")
  @Order(46)
  void testTurmaIntervaloData() {
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    objDTO.setDatainicio("20/10/2021");
    objDTO.setDatatermino("10/10/2021");
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.INTERVALO_ERRADO.getDescricao(), msg), "Não verificada intervalo de datas");
  }

  @Test
  @DisplayName("Incluir Turma")
  @Order(47)
  void testIncluirTurma() {
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.INCLUIDO_OK.getDescricao(), msg), "Turma não foi incluída");
  }

  @Test
  @DisplayName("Localizar Turma por Nome e verificar relacionamentos")
  @Order(48)
  void testLocalizarTurmaPorNome() {
    ResponseEntity<List<Turma>> responseFind = turmaCt.findNome(CURSO_NOME);
    List<Turma> turmas = responseFind.getBody();
    assertNotNull(turmas, "Não encontrado o turma");
    assertNotEquals(0, turmas.size(), "Lista de turmas vazia");
    idTurma = turmas.get(0).getId();
    assertNotNull(turmas.get(0).getProfessor(), "Não encontrado o Professor");
    assertNotNull(turmas.get(0).getCurso(), "Não encontrado o Curso");
  }

  @Test
  @DisplayName("Cadastrar outra Turma para o mesmo Professor com concomitância")
  @Order(49)
  void testIncluirConcomitante() {
    TurmaDTO objDTO = montarTurmaCompleto(idCurso);
    // Erro no Início
    objDTO.setDatainicio("11/10/2021");
    objDTO.setDatatermino("15/10/2021");
    ResponseEntity<MensagemDTO> response = turmaCt.adicionarTurma(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.PROFESSOR_ERRADO.getDescricao(), msg), "Turma foi incluída errada (início)");
    // Erro no Final
    objDTO.setDatainicio("09/10/2021");
    objDTO.setDatatermino("13/10/2021");
    response = turmaCt.adicionarTurma(objDTO);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.PROFESSOR_ERRADO.getDescricao(), msg), "Turma foi incluída errada (término)");
    // Erro em ambos períodos por conter
    objDTO.setDatainicio("09/10/2021");
    objDTO.setDatatermino("16/10/2021");
    response = turmaCt.adicionarTurma(objDTO);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.PROFESSOR_ERRADO.getDescricao(), msg),
        "Turma foi incluída errada (contém ambos)");
    // Erro em ambos períodos por estar contido
    objDTO.setDatainicio("11/10/2021");
    objDTO.setDatatermino("14/10/2021");
    response = turmaCt.adicionarTurma(objDTO);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.PROFESSOR_ERRADO.getDescricao(), msg),
        "Turma foi incluída errada (contido ambos)");
  }

  @Test
  @DisplayName("Eliminar Turma")
  @Order(50)
  void testEliminarTurma() {
    ResponseEntity<MensagemDTO> response = turmaCt.deleteTurma(idTurma);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessTurma.EXCLUIDO_OK.getDescricao(), msg), "Turma não foi excluída");
  }

  @Test
  @DisplayName("Eliminar Relacionamentos")
  @Order(51)
  void testEliminarRelacionamentos() {
    // Eliminar Curso
    ResponseEntity<MensagemDTO> response = cursoCt.deleteCurso(idCurso);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.EXCLUIDO_OK.getDescricao(), msg), "Curso não foi excluído");
    // Eliminar Professor
    response = professorCt.deleteProfessor(MATRICULA_PROFESSOR);
    msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.EXCLUIDO_OK.getDescricao(), msg), "Professor não foi excluído");
  }

}
