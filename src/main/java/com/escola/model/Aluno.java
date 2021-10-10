package com.escola.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @ApiModelProperty(value = "Matrícula (chave)")
  @Id
  @Column(name = "matricula")
  @Getter @Setter private Integer matricula;

  @ApiModelProperty(value = "Nome Completo")
  @Column(name = "nome")
  @Getter @Setter private String nome;
  
  @ApiModelProperty(value = "Data de Nascimento")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
  @Column(name = "datanascimento")
  @Getter @Setter private Date datanascimento;

  @ApiModelProperty(value = "Sexo")
  @Column(name = "sexo")
  @Getter @Setter private String sexo;

  @ApiModelProperty(value = "Contato")
  @Column(name = "contato")
  @Getter @Setter private String contato;
  
  @ApiModelProperty(value = "E-mail")
  @Column(name = "email")
  @Getter @Setter private String email;

  // Heranças Object
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aluno aluno = (Aluno)o;
    return matricula.equals(aluno.getMatricula());
  }

  @Override
  public int hashCode() {
    return Objects.hash(matricula);
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Aluno{");
    sb.append("matricula='").append(matricula);
    sb.append("', nome='").append(nome);
    sb.append("', datanascimento='").append(datanascimento);
    sb.append("', sexo='").append(sexo);
    sb.append("', contato='").append(contato);
    sb.append("', email='").append(email);
    return sb.toString();
  }
}