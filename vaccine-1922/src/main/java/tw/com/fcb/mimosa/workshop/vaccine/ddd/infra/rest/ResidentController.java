package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tw.com.fcb.mimosa.ext.ddd.application.UseCases;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.CancelVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ChooseVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ReplaceResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.ApplicationService;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.MakeAppointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.query.QueryResidents;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.query.QueryResidentsInfo;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.CancelVaccineRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ChooseVaccineRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.MakeAppointmentRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ReplaceResidentProfileRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ResidentApi;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.ResidentInfo;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.ResidentProfile;

@RestController
@RequiredArgsConstructor
class ResidentController implements ResidentApi {

  final ApplicationService service;
  final ResidentAssembler assembler;
  final UseCases useCases;
  final QueryResidents query;
  final QueryResidentsInfo queryInfo;

  @Override
  public long makeAppointment(MakeAppointmentRequest request) {
    var command = assembler.toMakeAppointmentCommand(request);
    return service.makeAppointment(command);
  }

  @Override
  public void chooseVaccine(long id, ChooseVaccineRequest request) {
    var command = assembler.toChooseVaccineCommand(request);
    command.setId(id);
    useCases.execute(command);
    //    service.chooseVaccine(command);
  }

  @Override
  public void cancelVaccine(long id, CancelVaccineRequest request) {
    var command = assembler.toCancelVaccineCommand(request);
    command.setId(id);
    //    service.cancelVaccine(command);
    useCases.execute(command);
  }

  @Override
  public void replaceResidentProfile(long id, ReplaceResidentProfileRequest request) {
    var command = assembler.toReplaceResidentProfileCommand(request);
    command.setId(id);
    useCases.execute(command);
  }

  @Override
  public List<ResidentProfile> getResidents() {
    return query.getResidents();
  }

  @Override
  public List<ResidentInfo> getResidentsInfo() {
    return queryInfo.getResidentsInfo();
  }
}
