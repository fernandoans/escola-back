package com.escola;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.escola.business.enums.CodBusinessCurso;
import com.escola.business.enums.CodBusinessProfessor;
import com.escola.dto.CursoDTO;
import com.escola.dto.MensagemDTO;
import com.escola.dto.ProfessorDTO;

class CursoTest extends PaiTest {

  private static long id;

  public CursoTest() {
    super();
  }

  @Test
  @DisplayName("Incluir Professor")
  @Order(30)
  void testIncluirProfessor() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.INCLUIDO_OK.getDescricao(), msg), "Professor não foi incluído");
  }

  @Test
  @DisplayName("Curso sem nome")
  @Order(31)
  void testCursoSemNome() {
    CursoDTO objDTO = montarCursoCompleto();
    objDTO.setNome("");
    ResponseEntity<MensagemDTO> response = cursoCt.adicionarCurso(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.NOME_OBRIGATORIO.getDescricao(), msg), "Não verificado nome vazio");
  }

  @Test
  @DisplayName("Curso sem carga horária")
  @Order(32)
  void testCursoSemCargaHoraria() {
    CursoDTO objDTO = montarCursoCompleto();
    objDTO.setCargahoraria("");
    ResponseEntity<MensagemDTO> response = cursoCt.adicionarCurso(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.CARGA_HORARIA_OBRIGATORIA.getDescricao(), msg),
        "Não verificada carga horária vazia");
  }

  @Test
  @DisplayName("Curso sem data")
  @Order(33)
  void testCursoSemData() {
    CursoDTO objDTO = montarCursoCompleto();
    objDTO.setDatainicio("");
    ResponseEntity<MensagemDTO> response = cursoCt.adicionarCurso(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data vazia");
  }

  @Test
  @DisplayName("Curso com data errada")
  @Order(34)
  void testCursoDataErrada() {
    CursoDTO objDTO = montarCursoCompleto();
    objDTO.setDatainicio("2007/12/20");
    ResponseEntity<MensagemDTO> response = cursoCt.adicionarCurso(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data mal formada");
  }

  @Test
  @DisplayName("Incluir Curso")
  @Order(35)
  void testIncluirCurso() {
    CursoDTO objDTO = montarCursoCompleto();
    ResponseEntity<MensagemDTO> response = cursoCt.adicionarCurso(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.INCLUIDO_OK.getDescricao(), msg), "Curso não foi incluído");
  }

  @Test
  @DisplayName("Localizar Curso por Nome")
  @Order(36)
  void testLocalizarCursoPorNome() {
    id = obterCursoTeste();
    assertTrue((0L < id), "Lista de cursos vazia");
  }

  @Test
  @DisplayName("Eliminar Curso")
  @Order(37)
  void testEliminarCurso() {
    ResponseEntity<MensagemDTO> response = cursoCt.deleteCurso(id);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessCurso.EXCLUIDO_OK.getDescricao(), msg), "Curso não foi excluído");
  }

  @Test
  @DisplayName("Eliminar Professor")
  @Order(38)
  void testEliminarProfessor() {
    ResponseEntity<MensagemDTO> response = professorCt.deleteProfessor(MATRICULA_PROFESSOR);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.EXCLUIDO_OK.getDescricao(), msg), "Professor não foi excluído");
  }
}
