package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.CancelVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ChooseVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.MakeAppointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ReplaceResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Appointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ChooseEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentInfoEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentProfileEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.CancelVaccineRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ChooseVaccineRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.MakeAppointmentRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ReplaceResidentProfileRequest;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.ResidentInfo;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.ResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Mapper
public interface ResidentAssembler {

  MakeAppointment toMakeAppointmentCommand(MakeAppointmentRequest request);

  ChooseVaccine toChooseVaccineCommand(ChooseVaccineRequest request);

  ResidentEntity toEntity(Appointment domain);

  CancelVaccine toCancelVaccineCommand(CancelVaccineRequest request);

  Appointment fromEntity(ResidentEntity residentEntity);

  ReplaceResidentProfile toReplaceResidentProfileCommand(ReplaceResidentProfileRequest request);

  @Mapping(target = "id", ignore = true)
  ResidentProfileEntity copyProfileEntity(ResidentEntity resident,
      @MappingTarget ResidentProfileEntity profile);

  default String toChooseString(List<ChooseEntity> chooses) {
    return chooses.stream()
        .map(ChooseEntity::getVaccine)
        .map(Vaccine::name)
        .collect(Collectors.joining(","));
  }

  @Mapping(target = "residentId", source = "id")
  ResidentProfileEntity toProfileEntity(ResidentEntity resident);

  ResidentProfile toProfile(ResidentProfileEntity profileEntity);

  ResidentInfo toInfo(ResidentInfoEntity infoEntity);

  default List<Vaccine> fromChooseString(String choose) {
    return Arrays.stream(choose.split(","))
        .map(Vaccine::valueOf)
        .collect(Collectors.toList());
  }
}
