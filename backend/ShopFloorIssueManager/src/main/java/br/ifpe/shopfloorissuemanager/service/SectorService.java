package br.ifpe.shopfloorissuemanager.service;

import br.ifpe.shopfloorissuemanager.entity.Employee;
import br.ifpe.shopfloorissuemanager.entity.Sector;
import br.ifpe.shopfloorissuemanager.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public void create(Sector sector) {
        try {
            sectorRepository.create(sector);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Sector> readAll() {
        try {
            return sectorRepository.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Employee> readAllEmployees(int sectorId) {
        try {
            return sectorRepository.readAllEmployees(sectorId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ProblemDailyReport> readProblemDailyReport(int sectorId) {
        try {
            return sectorRepository.readProblemDailyReport(sectorId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
