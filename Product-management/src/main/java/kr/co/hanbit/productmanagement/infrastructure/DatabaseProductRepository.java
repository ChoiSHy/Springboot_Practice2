package kr.co.hanbit.productmanagement.infrastructure;

import kr.co.hanbit.productmanagement.domain.Exception.EntityNotFoundException;
import kr.co.hanbit.productmanagement.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseProductRepository {


    public Product add(Product product) {
        return null;
    }

    public Product findById(Long id) {
        return null;
    }

    public List<Product> findAll() {
        return null;
    }

    public List<Product> findByName(String name) {
        return null;
    }

    public Product update(Product product) {
        return null;
    }

    public void delete(Long id) {
    }

}
