package br.ifpe.shopfloorissuemanager.controller;

import br.ifpe.shopfloorissuemanager.entity.Employee;
import br.ifpe.shopfloorissuemanager.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Employee employee) {
        employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/sector/{id}")
    public ResponseEntity<List<Employee>> readBySector(@PathVariable("id") int sectorId) {
        List<Employee> employees = employeeService.readBySector(sectorId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> readAll() {
        List<Employee> employees = employeeService.readAll();
        return ResponseEntity.ok(employees);
    }
}
