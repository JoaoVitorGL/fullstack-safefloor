package br.ifpe.shopfloorissuemanager.controller;

import br.ifpe.shopfloorissuemanager.entity.Employee;
import br.ifpe.shopfloorissuemanager.entity.Report;
import br.ifpe.shopfloorissuemanager.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Report report) {
        reportService.create(report);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
