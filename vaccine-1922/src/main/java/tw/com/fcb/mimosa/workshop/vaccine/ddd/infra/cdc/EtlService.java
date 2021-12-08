package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.cdc;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentProfileRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class EtlService {

  final ResidentRepository residentRepository;
  final ResidentProfileRepository residentProfileRepository;
  final ResidentAssembler assembler;

  @Scheduled(fixedRate = 10 * 1000)
  public void etl() {
    var to = LocalDateTime.now();
    var from = to.minusSeconds(10);
    // 1. 找出 lastModifiedTime 在 from 跟 to 之間的資料
    // 2. 依序轉換成 ResidentProfileEntity
    // 3. save to RESIDENT_PROFILE
    // (residentId 不存在就 insert, 已存在就 update)

    residentRepository.findByLastModifiedDateBetween(from, to)
        .stream()
        .map(resident -> {
          return residentProfileRepository.findByResidentId(resident.getId())
              .map(profile -> assembler.copyProfileEntity(resident, profile)) // copy profile
              .orElseGet(() -> assembler.toProfileEntity(resident)) // new profile
          ;
        }).forEach(residentProfileRepository::save);
  }

}
