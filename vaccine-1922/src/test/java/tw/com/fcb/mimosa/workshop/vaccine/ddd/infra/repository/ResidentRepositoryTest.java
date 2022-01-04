package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.mimosa.test.MimosaTest;

@MimosaTest
@Transactional
class ResidentRepositoryTest {

  @Autowired
  ResidentRepository repository;
  ResidentEntity entity;

  @BeforeEach
  void arrange() {
    entity = new ResidentEntity();
    entity.setPhoneNo("0987654321");
    entity.setChooses(List.of());
    entity.setCancels(List.of());
  }

  @Test
  void generateId() {
    repository.save(entity);
    Assertions.assertThat(entity)
        .extracting("id")
        .matches(Objects::nonNull);

  }

  @Test
  void generateLastModifiedDate() {
    repository.save(entity);
    Assertions.assertThat(entity)
        .extracting(ResidentEntity::getLastModifiedDate)
        .matches(Objects::nonNull);

  }

  @Test
  void crud() {
    //create
    var id = repository.save(entity).getId();
    Assertions.assertThat(id).isNotNull().isPositive();

    //read
    var found = repository.findById(id);
    Assertions.assertThat(found)
        .isPresent()
        .get()
        .extracting(ResidentEntity::getPhoneNo)
        .isEqualTo("0987654321");

    //update
    entity.setNhiNo("1234567890");
    repository.save(entity);

    //find by id 
    //assert phoneNo nhiNo
    var update = repository.findById(id);
    Assertions.assertThat(update)
        .isPresent()
        .get()
        .extracting(ResidentEntity::getPhoneNo, ResidentEntity::getNhiNo)
        .contains("0987654321", "1234567890");

    //delete
    repository.deleteById(id);
    // find by id 
    //assert isEmpty
    var delete = repository.findById(id);
    Assertions.assertThat(delete)
        .isEmpty();
  }
}
