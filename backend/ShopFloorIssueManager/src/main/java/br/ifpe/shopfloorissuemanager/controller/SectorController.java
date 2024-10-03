package br.ifpe.shopfloorissuemanager.controller;

import br.ifpe.shopfloorissuemanager.entity.Employee;
import br.ifpe.shopfloorissuemanager.entity.Sector;
import br.ifpe.shopfloorissuemanager.service.ProblemDailyReport;
import br.ifpe.shopfloorissuemanager.service.SectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sector")
@CrossOrigin("*")
public class SectorController {

    private final SectorService sectorService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Sector sector) {
        sectorService.create(sector);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Sector>> readAll() {
        List<Sector> sectors = sectorService.readAll();
        return ResponseEntity.ok(sectors);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> readAllEmployees(@PathVariable("id") int sectorId) {
        List<Employee> employees = sectorService.readAllEmployees(sectorId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<List<ProblemDailyReport>> readProblemDailyReport(@PathVariable("id") int sectorId) {
        List<ProblemDailyReport> report = sectorService.readProblemDailyReport(sectorId);
        return ResponseEntity.ok(report);
    }
}
