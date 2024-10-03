package br.ifpe.shopfloorissuemanager.service;

import br.ifpe.shopfloorissuemanager.entity.Problem;
import br.ifpe.shopfloorissuemanager.repository.ProblemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Service
public class ProblemService {

    final private ProblemRepository problemRepository;

    public List<Problem> readAll() {
        try {
            return problemRepository.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
