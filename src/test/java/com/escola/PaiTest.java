package com.escola;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.escola.controller.AlunoController;
import com.escola.controller.CursoController;
import com.escola.controller.ProfessorController;
import com.escola.controller.TurmaAlunoController;
import com.escola.controller.TurmaController;
import com.escola.dto.AlunoDTO;
import com.escola.dto.CursoDTO;
import com.escola.dto.ProfessorDTO;
import com.escola.dto.TurmaAlunoDTO;
import com.escola.dto.TurmaDTO;
import com.escola.model.Curso;
import com.escola.model.Turma;

@SpringBootTest(classes = LaboratorioApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PaiTest {

  // Valores usados nos testes
  protected static final Integer MATRICULA_ALUNO = new Integer(300);
  protected static final Integer MATRICULA_PROFESSOR = new Integer(400);
  protected static final String CURSO_NOME = "Curso de Teste";
  protected static final String TURMA_NOME = "Turma de Teste";

  @Autowired
  WebApplicationContext context;

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  AlunoController alunoCt;

  @Autowired
  CursoController cursoCt;

  @Autowired
  ProfessorController professorCt;

  @Autowired
  TurmaController turmaCt;

  @Autowired
  TurmaAlunoController turmaAlunoCt;

  @BeforeEach
  protected void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  @DisplayName("Verificar Controllers")
  @Order(1)
  void testContextLoads() {
    assertThat(alunoCt).isNotNull();
    assertThat(cursoCt).isNotNull();
    assertThat(professorCt).isNotNull();
    assertThat(turmaCt).isNotNull();
    assertThat(turmaAlunoCt).isNotNull();
  }

  protected boolean compStr(String msg1, String msg2) {
    return msg1.equals(msg2);
  }

  // Montagens dos Objetos

  protected AlunoDTO montarAlunoCompleto() {
    AlunoDTO objDTO = new AlunoDTO();
    objDTO.setMatricula("" + MATRICULA_ALUNO);
    objDTO.setNome("Fulano da Silva");
    objDTO.setEmail("fulano@gmail.com");
    objDTO.setDatanascimento("20/12/2007");
    objDTO.setSexo("M");
    objDTO.setContato("61 989652326");
    return objDTO;
  }

  protected ProfessorDTO montarProfessorCompleto() {
    ProfessorDTO objDTO = new ProfessorDTO();
    objDTO.setMatricula("" + MATRICULA_PROFESSOR);
    objDTO.setNome("Fulano da Silva");
    objDTO.setEmail("fulano@gmail.com");
    objDTO.setDatanascimento("20/12/2007");
    objDTO.setSexo("M");
    objDTO.setContato("61 989652326");
    objDTO.setDisciplina("Disciplina 1");
    return objDTO;
  }

  protected CursoDTO montarCursoCompleto() {
    CursoDTO objDTO = new CursoDTO();
    objDTO.setNome(CURSO_NOME);
    objDTO.setDescricao("Como fazer testes unitários");
    objDTO.setDatainicio("11/10/2021");
    objDTO.setNumsala("1");
    objDTO.setQtdalunos("5");
    objDTO.setCargahoraria("20");
    objDTO.setProfessor(new ProfessorDTO("" + MATRICULA_PROFESSOR));
    return objDTO;
  }

  protected TurmaDTO montarTurmaCompleto(long curso) {
    TurmaDTO objDTO = new TurmaDTO();
    objDTO.setNome(TURMA_NOME);
    objDTO.setCargahoraria("8");
    objDTO.setNumsala("1");
    objDTO.setDatainicio("10/10/2021");
    objDTO.setDatatermino("15/10/2021");
    objDTO.setProfessor(new ProfessorDTO(""+MATRICULA_PROFESSOR));
    objDTO.setCurso(new CursoDTO(""+curso));
    return objDTO;
  }

  protected TurmaAlunoDTO montarTurmaAlunoCompleto(long turma) {
    TurmaAlunoDTO objDTO = new TurmaAlunoDTO();
    objDTO.setAluno(new AlunoDTO(""+MATRICULA_ALUNO));
    objDTO.setTurma(new TurmaDTO(""+turma));
    return objDTO;
  }
  
  
  protected long obterCursoTeste() {
    ResponseEntity<List<Curso>> responseFind = cursoCt.findNome(CURSO_NOME);
    List<Curso> cursos = responseFind.getBody();
    return (cursos.isEmpty())?0L:cursos.get(0).getId();
  }

  protected long obterTurmaTeste() {
    ResponseEntity<List<Turma>> responseFind = turmaCt.findNome(TURMA_NOME);
    List<Turma> turmas = responseFind.getBody();
    return (turmas.isEmpty())?0L:turmas.get(0).getId();
  }
}
