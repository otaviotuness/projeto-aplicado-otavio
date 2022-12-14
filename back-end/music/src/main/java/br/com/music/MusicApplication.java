package br.com.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class MusicApplication {

  public static void main(String[] args) {
    SpringApplication.run(MusicApplication.class, args);
  }
}
