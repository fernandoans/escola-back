package com.escola.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "turma")
@SequenceGenerator(name = "TURMA_SEQ", sequenceName = "turma_id_seq", allocationSize=1)
public class Turma implements Serializable {

  private static final long serialVersionUID = 2L;

  @ApiModelProperty(value = "Id (chave)")
  @Id
  @Column(name = "id", unique = true, nullable = false, length = 14)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TURMA_SEQ")
  @Getter @Setter private Long id;

  @ApiModelProperty(value = "Nome")
  @Column(name = "nome")
  @Getter @Setter private String nome;
  
  @ApiModelProperty(value = "Carga Horária")
  @Column(name = "cargahoraria")
  @Getter @Setter private Integer cargahoraria;
  
  @ApiModelProperty(value = "Data Início")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
  @Column(name = "datainicio")
  @Getter @Setter private Date datainicio;

  @ApiModelProperty(value = "Data Término")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
  @Column(name = "datatermino")
  @Getter @Setter private Date datatermino;

  @ApiModelProperty(value = "Número da Sala")
  @Column(name = "numsala")
  @Getter @Setter private Integer numsala;

  @ApiModelProperty(value = "Professor")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "professor", referencedColumnName = "matricula")
  @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
  @Getter @Setter private Professor professor;

  @ApiModelProperty(value = "Curso")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "curso", referencedColumnName = "id")
  @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
  @Getter @Setter private Curso curso;

  @ApiModelProperty(value = "Alunos")
  @OneToMany(mappedBy="turma", targetEntity = TurmaAluno.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
  @Getter @Setter private Set<TurmaAluno> alunos;  

  // Heranças Object
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Turma turma = (Turma)o;
    return id.equals(turma.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Turma{");
    sb.append("id='").append(id);
    sb.append("', nome='").append(nome);
    sb.append("', cargahoraria='").append(cargahoraria);
    sb.append("', datainicio='").append(datainicio);
    sb.append("', datatermino='").append(datatermino);
    sb.append("', numsala='").append(numsala);
    sb.append("', professor='").append(professor.getNome());
    sb.append("', curso='").append(curso.getNome());
    sb.append("'}");
    return sb.toString();
  }
  
}
