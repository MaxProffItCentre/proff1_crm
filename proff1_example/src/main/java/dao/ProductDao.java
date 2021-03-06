package dao;

import java.util.List;

import data.Product;

public interface ProductDao {
    Long create(Product product);
    Product read(Long id);
    void update(Product product);
    boolean delete(Product product);
    List<Product> findAll();
    List<Product> findProductsByBeginString(String begin);
}
