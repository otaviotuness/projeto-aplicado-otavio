package br.com.music.modules.checklist.dataprovider.database;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.checklist.dataprovider.repository.ChecklistRepository;
import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.configTest.TestWithMySQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
class ChecklistMySQLDataProviderIT extends TestWithMySQL {

  @Autowired private ChecklistMySQLDataProvider checklistMySQLDataProvider;

  @Autowired private ChecklistRepository checklistRepository;

  @Autowired private UserInfo userInfo;

  @BeforeEach
  void beforeEach() {
    checklistRepository.deleteAll();
  }

  @Test
  void shouldSave_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);

    Assertions.assertEquals(0, checklistRepository.count());

    checklistMySQLDataProvider.save(checklist);

    final var expectedChecklist = checklistRepository.findAll().get(0);

    Assertions.assertNotNull(expectedChecklist);
    Assertions.assertNotNull(checklist.getIdUser());
    Assertions.assertEquals(checklist.getDescription(), expectedChecklist.getDescription());
  }

  @Test
  void shouldSaveAndUpdate_thenReturnSuccessfully() {
    final var expectedDescription = "Alter description";
    final var expectedIdUser = 1;

    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);
    checklistMySQLDataProvider.save(checklist);

    final var checklistUpdate = checklistRepository.findAll().get(0);
    checklistUpdate.setDescription(expectedDescription);
    checklistUpdate.setIdUser(expectedIdUser);

    checklistMySQLDataProvider.save(checklistUpdate);

    final var expectedchecklist = checklistRepository.findById(checklistUpdate.getId()).get();

    Assertions.assertNotNull(expectedchecklist);
    Assertions.assertEquals(expectedchecklist.getIdUser(), expectedIdUser);
    Assertions.assertEquals(expectedchecklist.getDescription(), expectedDescription);
  }

  @Test
  void shouldFindById_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);
    checklist.setId(null);
    checklistMySQLDataProvider.save(checklist);

    final var expectedChecklist = checklistMySQLDataProvider.findById(checklist.getId());

    Assertions.assertNotNull(expectedChecklist);
    Assertions.assertNotNull(checklist.getId());
    Assertions.assertEquals(checklist.getIdUser(), expectedChecklist.getIdUser());
    Assertions.assertEquals(checklist.getDescription(), expectedChecklist.getDescription());
  }

  @Test
  void shouldFindAll_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);
    checklist.setIdUser(0);
    checklist.setId(null);
    checklistMySQLDataProvider.save(checklist);

    Assertions.assertEquals(1, checklistRepository.count());

    final var expectedchecklist = checklistMySQLDataProvider.findAll().get(0);

    Assertions.assertNotNull(expectedchecklist);
    Assertions.assertEquals(checklist.getIdUser(), expectedchecklist.getIdUser());
    Assertions.assertEquals(checklist.getDescription(), expectedchecklist.getDescription());
  }

  @Test
  void shouldDelete_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);
    checklist.setId(null);

    final var checklistSave = checklistRepository.save(checklist);

    checklistMySQLDataProvider.deleteById(checklistSave.getId());

    Assertions.assertEquals(0, checklistRepository.count());
  }
}
