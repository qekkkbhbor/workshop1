package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
