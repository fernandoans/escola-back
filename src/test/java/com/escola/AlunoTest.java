package com.escola;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.escola.business.enums.CodBusinessAluno;
import com.escola.dto.AlunoDTO;
import com.escola.dto.MensagemDTO;

class AlunoTest extends PaiTest {

  public AlunoTest() {
    super();
  }

  @Test
  @DisplayName("Aluno sem matrícula")
  @Order(10)
  void testAlunoSemMatricula() {
    AlunoDTO objDTO = montarAlunoCompleto();
    objDTO.setMatricula("");
    ResponseEntity<MensagemDTO> response = alunoCt.adicionarAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.MATRICULA_OBRIGATORIA.getDescricao(), msg), "Não verificada matrícula vazia");
  }

  @Test
  @DisplayName("Aluno sem nome")
  @Order(11)
  void testAlunoSemNome() {
    AlunoDTO objDTO = montarAlunoCompleto();
    objDTO.setNome("");
    ResponseEntity<MensagemDTO> response = alunoCt.adicionarAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.NOME_OBRIGATORIO.getDescricao(), msg), "Não verificado nome vazio");
  }

  @Test
  @DisplayName("Aluno sem e-mail")
  @Order(12)
  void testAlunoSemEmail() {
    AlunoDTO objDTO = montarAlunoCompleto();
    objDTO.setEmail("");
    ResponseEntity<MensagemDTO> response = alunoCt.adicionarAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.EMAIL_OBRIGATORIO.getDescricao(), msg), "Não verificado e-mail vazio");
  }

  @Test
  @DisplayName("Aluno sem data")
  @Order(13)
  void testAlunoSemData() {
    AlunoDTO objDTO = montarAlunoCompleto();
    objDTO.setDatanascimento("");
    ResponseEntity<MensagemDTO> response = alunoCt.adicionarAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data vazia");
  }

  @Test
  @DisplayName("Aluno com data errada")
  @Order(14)
  void testAlunoDataErrada() {
    AlunoDTO objDTO = montarAlunoCompleto();
    objDTO.setDatanascimento("2007/12/20");
    ResponseEntity<MensagemDTO> response = alunoCt.adicionarAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.DATA_MALFORMADA.getDescricao(), msg), "Não verificada Data mal formada");
  }

  @Test
  @DisplayName("Incluir Aluno")
  @Order(15)
  void testIncluirAluno() {
    AlunoDTO objDTO = montarAlunoCompleto();
    ResponseEntity<MensagemDTO> response = alunoCt.adicionarAluno(objDTO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.INCLUIDO_OK.getDescricao(), msg), "Aluno não foi incluído");
  }

  @Test
  @DisplayName("Eliminar Aluno")
  @Order(16)
  void testEliminarAluno() {
    ResponseEntity<MensagemDTO> response = alunoCt.deleteAluno(MATRICULA_ALUNO);
    String msg = response.getBody().getMessage();
    assertTrue(compStr(CodBusinessAluno.EXCLUIDO_OK.getDescricao(), msg), "Aluno não foi excluído");
  }
}
