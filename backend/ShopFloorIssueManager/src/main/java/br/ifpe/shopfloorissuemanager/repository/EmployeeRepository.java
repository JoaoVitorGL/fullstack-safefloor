package br.ifpe.shopfloorissuemanager.repository;

import br.ifpe.shopfloorissuemanager.entity.Employee;
import br.ifpe.shopfloorissuemanager.entity.Sector;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository implements IRepository<Employee, Integer> {
    @Override
    public void create(Employee employee) throws SQLException {

        String sql = "INSERT INTO employee(employee_name, sector_fk_id) VALUES (?, ?)";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {
            pstm.setString(1, employee.getName());
            pstm.setInt(2, employee.getSector().getId());
            pstm.execute();
        }
    }

    public List<Employee> readBySector(int sectorId) throws SQLException {

        String sql = "SELECT * FROM employee INNER JOIN sector ON sector_fk_id = sector_id WHERE sector_fk_id = ? ORDER BY employee_name";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

            pstm.setInt(1, sectorId);
            ResultSet rs = pstm.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getInt("employee_id"))
                        .name(rs.getString("employee_name"))
                        .sector(Sector.builder()
                                .id(rs.getInt("sector_id"))
                                .name(rs.getString("sector_name"))
                                .build())
                        .build();
                employees.add(employee);
            }

            return employees;
        }
    }

    @Override
    public List<Employee> readAll() throws SQLException {

        String sql = "SELECT * FROM employee INNER JOIN sector ON sector_fk_id = sector_id";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getInt("employee_id"))
                        .name(rs.getString("employee_name"))
                        .sector(Sector.builder()
                                .id(rs.getInt("sector_id"))
                                .name(rs.getString("sector_name"))
                                .build())
                        .build();
                employees.add(employee);
            }

            return employees;
        }
    }
}
