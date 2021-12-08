package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Table(name = "RESIDENT")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ResidentEntity {

  @Id
  @GeneratedValue
  Long id;
  String nhiNo;
  String phoneNo;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "resident_id")
  List<ChooseEntity> chooses;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "resident_id")
  List<CancelEntity> cancels;

  @LastModifiedDate
  LocalDateTime lastModifiedDate;
}
