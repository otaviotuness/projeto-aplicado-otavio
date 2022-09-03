package br.com.music.modules.configTest;

import java.util.Collection;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class CleanUpExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    final var repositories =
        SpringExtension.getApplicationContext(context).getBeansOfType(JpaRepository.class).values();

    cleanUp(repositories);
  }

  private void cleanUp(final Collection<JpaRepository> repositories) {
    repositories.forEach(CrudRepository::deleteAll);
  }
}
