package br.ifpe.shopfloorissuemanager.controller;

import br.ifpe.shopfloorissuemanager.entity.Problem;
import br.ifpe.shopfloorissuemanager.service.ProblemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/problem")
@CrossOrigin("*")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/list")
    public ResponseEntity<List<Problem>> readAll() {
        List<Problem> problems = problemService.readAll();
        return ResponseEntity.ok(problems);
    }
}
