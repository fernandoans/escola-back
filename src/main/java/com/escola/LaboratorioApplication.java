package com.escola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.escola.config.YAMLConfig;
import com.escola.config.model.Servidores;

@SpringBootApplication
public class LaboratorioApplication implements CommandLineRunner {

  @Autowired
  private YAMLConfig myConfig;

  public static void main(String[] args) {
    SpringApplication.run(LaboratorioApplication.class, args);
  }

  public void run(String... args) throws Exception {
    System.out.println("Usando o Ambiente: " + myConfig.getAmbiente());
    System.out.println("Nome: " + myConfig.getNome());
    System.out.println("Habilitado: " + myConfig.isHabilitado());
    System.out.println("Servidores:");
    for (Servidores s: myConfig.getServidores()) {
      System.out.println("Endereço: " + s.getIp() + s.getPath());
    }
  }
}
