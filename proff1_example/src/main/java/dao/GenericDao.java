package dao;

import java.util.List;

public interface GenericDao<T> {
    Long create(T product);
    T read(Long id);
    void update(T product);
    boolean delete(T product);
    List<T> findAll();
}
