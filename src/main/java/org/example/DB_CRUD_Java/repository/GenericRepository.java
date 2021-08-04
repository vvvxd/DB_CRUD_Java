package org.example.DB_CRUD_Java.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID, Q> {

    T getById(ID id);

    List<T> getAll() throws SQLException;

    T save(T s, Q e);

    T update(T s, Q e);

    T save(T s);

    T update(T s);

    void deleteById(ID id);

}
