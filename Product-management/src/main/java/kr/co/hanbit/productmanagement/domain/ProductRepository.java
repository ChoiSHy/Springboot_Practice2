package kr.co.hanbit.productmanagement.domain;

import kr.co.hanbit.productmanagement.domain.Product;

import java.util.List;

public interface ProductRepository {
    Product add(Product product);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByNameContaining(String name);
    Product update(Product product);
    void delete(Long id);
}
