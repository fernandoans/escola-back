package com.escola.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "turmaaluno")
@SequenceGenerator(name = "TURMA_ALUNO_SEQ", sequenceName = "turmaaluno_id_seq", allocationSize=1)
public class TurmaAluno implements Serializable {

  private static final long serialVersionUID = 2L;

  @ApiModelProperty(value = "Id (chave)")
  @Id
  @Column(name = "id", unique = true, nullable = false, length = 14)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TURMA_ALUNO_SEQ")
  @Getter @Setter private Long id;

  @ApiModelProperty(value = "Turma")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "turma", referencedColumnName = "id")
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  @Getter @Setter private Turma turma;

  @ApiModelProperty(value = "Aluno")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "aluno", referencedColumnName = "matricula")
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  @Getter @Setter private Aluno aluno;

}
