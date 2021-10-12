package com.escola;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.escola.business.enums.CodBusinessProfessor;
import com.escola.converters.ProfessorConverter;
import com.escola.dto.MensagemDTO;
import com.escola.dto.ProfessorDTO;
import com.escola.model.Professor;

class ProfessorTest extends PaiTest {
  
  private ProfessorConverter converter;

  public ProfessorTest() {
    super();
    converter = new ProfessorConverter();
  }

  @Test
  @DisplayName("Converter Professor")
  @Order(19)
  void testConversao() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    Professor professor = converter.convertToEntity(objDTO);
    assertTrue(compStr(professor.getNome(), objDTO.getNome()), "Nome não está presente");
  }

  
  @Test
  @DisplayName("Professor sem matrícula")
  @Order(20)
  void testProfessorSemMatricula() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    objDTO.setMatricula("");
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.MATRICULA_OBRIGATORIA.getDescricao(), msg), "Não verificada matrícula vazia");
  }

  @Test
  @DisplayName("Professor sem nome")
  @Order(21)
  void testProfessorSemNome() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    objDTO.setNome("");
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.NOME_OBRIGATORIO.getDescricao(), msg), "Não verificado nome vazio");
  }

  @Test
  @DisplayName("Professor sem e-mail")
  @Order(22)
  void testProfessorSemEmail() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    objDTO.setEmail("");
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.EMAIL_OBRIGATORIO.getDescricao(), msg), "Não verificado e-mail vazio");
  }

  @Test
  @DisplayName("Professor sem data")
  @Order(23)
  void testProfessorSemData() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    objDTO.setDatanascimento("");
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data vazia");
  }

  @Test
  @DisplayName("Professor com data errada")
  @Order(24)
  void testProfessorDataErrada() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    objDTO.setDatanascimento("2007/12/20");
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data mal formada");
  }

  @Test
  @DisplayName("Incluir Professor")
  @Order(25)
  void testIncluirProfessor() {
    ProfessorDTO objDTO = montarProfessorCompleto();
    ResponseEntity<MensagemDTO> response = professorCt.adicionarProfessor(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.INCLUIDO_OK.getDescricao(), msg), "Professor não foi incluído");
  }

  @Test
  @DisplayName("Eliminar Professor")
  @Order(26)
  void testEliminarProfessor() {
    ResponseEntity<MensagemDTO> response = professorCt.deleteProfessor(MATRICULA_PROFESSOR);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessProfessor.EXCLUIDO_OK.getDescricao(), msg), "Professor não foi excluído");
  }
}
