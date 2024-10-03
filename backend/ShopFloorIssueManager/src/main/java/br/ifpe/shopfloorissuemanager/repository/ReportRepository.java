package br.ifpe.shopfloorissuemanager.repository;

import br.ifpe.shopfloorissuemanager.entity.Report;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReportRepository implements IRepository<Report, Integer> {

    @Override
    public void create(Report report) throws SQLException {

        String sql = "INSERT INTO report(report_date, employee_fk_id, problem_fk_id) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {
            pstm.setDate(1, new java.sql.Date(report.getDate().getTime()));
            pstm.setInt(2, report.getEmployee().getId());
            pstm.setInt(3, report.getProblem().getId());
            pstm.execute();
        }
    }

    @Override
    public List<Report> readAll() throws SQLException {
        return null;
    }
}
