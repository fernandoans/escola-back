package com.escola.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.escola.config.model.Servidores;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {

  @Getter @Setter private String nome;
  @Getter @Setter private String ambiente;
  @Getter @Setter private boolean habilitado;
  @Getter @Setter private List<Servidores> servidores = new ArrayList<>();

}