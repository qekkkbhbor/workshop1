package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MockitoTest {

  @Test
  void mock() {
    var repository = Mockito.mock(AppointmentRepository.class);
    Mockito.when(repository.count()).thenReturn(1);
    var actual = repository.count();

    Assertions.assertThat(actual).isGreaterThanOrEqualTo(0).isNotNegative();

  }

  @Test
  void mockInput() {
    var mock = Mockito.mock(AppointmentRepository.class);
    Mockito.when(mock.countByName(Mockito.anyString())).thenReturn(1);
    Mockito.when(mock.countByName(Mockito.eq("hi"))).thenReturn(2);
    var actual = mock.countByName("hello");

    // act input hello
    Assertions.assertThat(actual).isPositive().isEqualTo(1);

    // act input hi
    Assertions.assertThat(mock.countByName("hi")).isPositive().isEqualTo(2);
  }

  @Test
  void mockThrow() {
    var mock = Mockito.mock(AppointmentRepository.class);
    Mockito.when(mock.countByNameLike(Mockito.anyString())).thenThrow(IllegalStateException.class);
    //act input hello
    //assert catch IllegalStateExpection
    var throwable = Assertions.catchThrowable(() -> {
      mock.countByNameLike("hi");
    });
    Assertions.assertThat(throwable)
        .isInstanceOf(IllegalStateException.class);

  }

  @Test
  void mockVoidThrow() {
    var mock = Mockito.mock(AppointmentRepository.class);
    Mockito.doThrow(IllegalStateException.class).when(mock).print();

    Assertions.assertThatIllegalStateException()
        .isThrownBy(mock::print);
  }

  @Test
  void spy() {
    //		var repository = new AppointmentRepository();
    //		System.out.println(repository.count());
    //		var mock =  Mockito.mock(AppointmentRepository.class);
    //		System.out.println(repository.count());//0

    var spy = Mockito.spy(AppointmentRepository.class);
    Mockito.doThrow(IllegalStateException.class).when(spy).print();

    //assert print throw
    //assert count()
    Assertions.assertThatIllegalStateException()
        .isThrownBy(spy::print);

    Assertions.assertThat(spy.count()).isEqualTo(-1);

  }

  @Test
  void spyVerify() {
    var spy = Mockito.spy(AppointmentRepository.class);
    spy.call(3);
    Mockito.verify(spy, Mockito.times(3)).count();
    Mockito.verify(spy, Mockito.atLeastOnce()).count();
    Mockito.verify(spy, Mockito.never()).print();
  }

  @Test
  void spyVerifyInOrder() {
    var spy = Mockito.spy(AppointmentRepository.class);
    var inOrder = Mockito.inOrder(spy);
    spy.call(3);
    inOrder.verify(spy).call(3);
    inOrder.verify(spy, Mockito.atLeast(2)).count();
  }

  static class AppointmentRepository {
    int count() {
      return -1;
    }

    int countByName(String name) {
      return -1;
    }

    int countByNameLike(String name) {
      return -1;
    }

    void print() {

    }

    void call(int num) {
      for (int i = 0; i < num; i++) {
        count();
      }
    }
  }
}
