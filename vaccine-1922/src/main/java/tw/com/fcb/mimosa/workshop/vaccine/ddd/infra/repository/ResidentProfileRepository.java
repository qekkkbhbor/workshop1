package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentProfileRepository
    extends JpaRepository<ResidentProfileEntity, Long> {

  Optional<ResidentProfileEntity> findByResidentId(Long id);
}
