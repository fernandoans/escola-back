package com.escola.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "curso")
@SequenceGenerator(name = "CURSO_SEQ", sequenceName = "curso_id_seq", allocationSize=1)
public class Curso implements Serializable {

  private static final long serialVersionUID = 2L;

  @ApiModelProperty(value = "Id (chave)")
  @Id
  @Column(name = "id", unique = true, nullable = false, length = 14)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CURSO_SEQ")
  @Getter @Setter private Long id;

  @ApiModelProperty(value = "Nome")
  @Column(name = "nome")
  @Getter @Setter private String nome;
  
  @ApiModelProperty(value = "Descrição")
  @Column(name = "descricao")
  @Getter @Setter private String descricao;
    
  @ApiModelProperty(value = "Carga Horária")
  @Column(name = "cargahoraria")
  @Getter @Setter private Integer cargahoraria;
  
  @ApiModelProperty(value = "Quantidade de Alunos")
  @Column(name = "qtdalunos")
  @Getter @Setter private Integer qtdalunos;

  @ApiModelProperty(value = "Data Início")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
  @Column(name = "datainicio")
  @Getter @Setter private Date datainicio;

  @ApiModelProperty(value = "Número da Sala")
  @Column(name = "numsala")
  @Getter @Setter private Integer numsala;

  @ApiModelProperty(value = "Professor")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "professor", referencedColumnName = "matricula")
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  @Getter @Setter private Professor professor;

  // Heranças Object
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Curso curso = (Curso)o;
    return id.equals(curso.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Curso{");
    sb.append("id='").append(id);
    sb.append("', nome='").append(nome);
    sb.append("', descricao='").append(descricao);
    sb.append("', cargahoraria='").append(cargahoraria);
    sb.append("', qtdalunos='").append(qtdalunos);
    sb.append("', datainicio='").append(datainicio);
    sb.append("', numsala='").append(numsala);
    sb.append("', professor='").append(professor.getNome());
    sb.append("'}");
    return sb.toString();
  }
  
}
