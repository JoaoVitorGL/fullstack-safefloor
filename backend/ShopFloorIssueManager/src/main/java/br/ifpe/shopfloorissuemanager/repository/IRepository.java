package br.ifpe.shopfloorissuemanager.repository;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<Entity, ID> {

    public void create(Entity entity) throws SQLException;
    List<Entity> readAll() throws SQLException;
}
