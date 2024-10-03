package br.ifpe.shopfloorissuemanager.repository;

import br.ifpe.shopfloorissuemanager.entity.Problem;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProblemRepository implements IRepository<Problem, Integer> {

    @Override
    public void create(Problem problem) throws SQLException {

    }

    @Override
    public List<Problem> readAll() throws SQLException {

        String sql = "SELECT * FROM problem";

        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            List<Problem> problems = new ArrayList<>();
            while (rs.next()) {
                Problem problem = Problem.builder()
                        .id(rs.getInt("problem_id"))
                        .description(rs.getString("problem_description"))
                        .build();
                problems.add(problem);
            }

            return problems;
        }
    }
}
