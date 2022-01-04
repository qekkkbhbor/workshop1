package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AssertJTest {

  @Test
  @DisplayName("空的測試")
  void test() {
    //Assertions.fail("failed");
    //fail("Not yet implemented");
  }

  @Test
  void assertNumbers() {
    //arrange
    int number = 1;

    //act

    // assert
    Assertions.assertThat(number)
        .isNotZero()
        .isGreaterThanOrEqualTo(0)
        .isLessThan(100)
        .isPositive()
        .isNotNegative()
        .isIn(1, 2, 3, 4, 5);
  }

  @Test
  void assertStrings() {
    String string = "hello world";
    Assertions.assertThat(string)
        .isNotBlank()
        .isEqualToIgnoringCase("HELLO WORLD")
        .isLowerCase()
        .contains("hello");

  }

  @Test
  void assertBoolean() {
    boolean bool = true;
    Assertions.assertThat(bool)
        .isTrue();
  }

  @Test
  void assertLocalDate() {
    LocalDate date = LocalDate.now();
    Assertions.assertThat(date)
        .isToday()
        .isBetween(date.minusDays(1), date.plusDays(1))
        .isAfterOrEqualTo(LocalDate.now());
  }

  @Test
  void assertOptional() {
    var opt = Optional.of(1);
    Assertions.assertThat(opt)
        .isPresent()
        .hasValue(1)
        .get().isEqualTo(1);
  }

  @Disabled
  @Test
  void softAssert() {
    try (var softAssertions = new AutoCloseableSoftAssertions()) {
      Assertions.assertThat("matt").isUpperCase();
      Assertions.assertThat(0).isPositive();
    }
  }

  @Test
  void assertObject() {
    var entity = new ResidentEntity();
    entity.setNhiNo("1234567890");
    entity.setPhoneNo("09876543210");
    Assertions.assertThat(entity)
        //.extracting("nhiNo")
        .extracting(ResidentEntity::getNhiNo, ResidentEntity::getPhoneNo)
        .contains("1234567890");
  }

  @Test
  void assertCollection() {
    var entity1 = new ResidentEntity();
    entity1.setNhiNo("a");
    entity1.setPhoneNo("a1");
    var entity2 = new ResidentEntity();
    entity2.setNhiNo("b");
    entity2.setPhoneNo("b1");

    //		var list = new ArrayList<ResidentEntity>();
    //		list.add(entity1);
    //		list.add(entity2);
    var list = List.of(entity1, entity2);
    Assertions.assertThat(list)
        .isNotEmpty()
        .hasSize(2)
        .extracting(ResidentEntity::getNhiNo, ResidentEntity::getPhoneNo)
        .contains(
            Assertions.tuple("a", "a1"),
            Assertions.tuple("b", "b1"));

  }

  @Test
  void assertMap() {
    //		var Map= new HashMap<String, String>();
    //		map.put("a","a1");
    //		map.put("b","b1");
    //		map.put("c","c1");

    var map = Map.of(
        "a", "a1",
        "b", "b1",
        "c", "c1");

    Assertions.assertThat(map)
        .isNotEmpty()
        .hasSize(3)
        .containsKeys("a", "b", "c")
        .matches(actual -> {
          //驗證 map 中每個 value 加上數字 1 等於期 key	
          return actual.entrySet().stream().allMatch(
              entry -> entry.getValue().equals(entry.getKey() + "1"));
        }, "value equals to key + '1' ");

  }

  @Test
  void assertException() {
    Assertions.assertThatThrownBy(() -> {
      var list = List.of(1, 2);
      list.get(2);
    }).isInstanceOf(IndexOutOfBoundsException.class);
  }

  @Test
  void assertExpectionType() {
    Assertions.assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> {
          List.of().get(1);
        });
  }

  @Test
  void assertCommonExpectionType() {
    Assertions.assertThatNullPointerException()
        .isThrownBy(() -> {
          Object obj = null;
          obj.toString();
        });
  }

  @Test
  void assertCatchExpection() {
    var throwable = Assertions.catchThrowable(() -> {
      double d = 1 / 0;
    });
    Assertions.assertThat(throwable)
        .isInstanceOf(ArithmeticException.class);
  }

  @Test
  void assertRootCauseExpection() {
    var throwable = Assertions.catchThrowable(() -> {
      throw new IllegalStateException("hello",
          new IllegalArgumentException("world"));
    });
    Assertions.assertThat(throwable)
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("hello")
        .hasRootCauseExactlyInstanceOf(IllegalArgumentException.class)
        .hasRootCauseMessage("world");
  }
}
