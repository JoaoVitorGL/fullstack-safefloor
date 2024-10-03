package br.ifpe.shopfloorissuemanager.repository;

import br.ifpe.shopfloorissuemanager.entity.Employee;
import br.ifpe.shopfloorissuemanager.entity.Sector;
import br.ifpe.shopfloorissuemanager.service.ProblemDailyReport;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SectorRepository implements IRepository<Sector, Integer> {
    @Override
    public void create(Sector sector) throws SQLException {

        String sql = "INSERT INTO sector(sector_name) VALUES (?)";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {
            pstm.setString(1, sector.getName());
            pstm.execute();
        }
    }

    @Override
    public List<Sector> readAll() throws SQLException {

        String sql = "SELECT * FROM sector ORDER BY sector_name";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            List<Sector> sectors = new ArrayList<>();
            while (rs.next()) {
                Sector sector = Sector.builder().
                        id(rs.getInt("sector_id"))
                        .name(rs.getString("sector_name"))
                        .build();
                sectors.add(sector);
            }

            return sectors;
        }
    }

    public List<Employee> readAllEmployees(int sectorId) throws SQLException {

        String sql = "SELECT * FROM employee INNER JOIN sector ON sector_fk_id = sector_id WHERE sector_fk_id = ?";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

            pstm.setInt(1, sectorId);
            ResultSet rs = pstm.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getInt("employee_id"))
                        .name(rs.getString("employee_name"))
                        .sector(Sector.builder()
                                .id(sectorId)
                                .name(rs.getString("sector_name"))
                                .build())
                        .build();
                employees.add(employee);
            }

            return employees;
        }
    }

    public List<ProblemDailyReport> readProblemDailyReport(int sectorId) throws SQLException {

        String sql = """
                SELECT 
                    r.report_date,
                    p.problem_description,
                    COUNT(r.report_id) AS report_count
                FROM 
                    report r
                JOIN 
                    employee e ON r.employee_fk_id = e.employee_id
                JOIN 
                    sector s ON e.sector_fk_id = s.sector_id 
                JOIN 
                    problem p ON r.problem_fk_id = p.problem_id 
                WHERE 
                    s.sector_id = ?
                GROUP BY 
                    r.report_date, p.problem_description
                ORDER BY 
                    r.report_date desc, p.problem_description;
                """;
        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

            pstm.setInt(1, sectorId);
            ResultSet rs = pstm.executeQuery();
            List<ProblemDailyReport> dailyReports = new ArrayList<>();
            while (rs.next()) {
                ProblemDailyReport report = ProblemDailyReport.builder()
                        .reportDate(rs.getDate("report_date"))
                        .problemDescription(rs.getString("problem_description"))
                        .reportCount(rs.getInt("report_count"))
                        .build();
                dailyReports.add(report);
            }
            return dailyReports;
        }
    }
}
