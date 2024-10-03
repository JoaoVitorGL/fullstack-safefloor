package br.ifpe.shopfloorissuemanager.service;

import br.ifpe.shopfloorissuemanager.entity.Report;
import br.ifpe.shopfloorissuemanager.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@AllArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public void create(Report report) {
        try {
            reportRepository.create(report);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
