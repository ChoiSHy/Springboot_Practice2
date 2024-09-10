package kr.co.hanbit.productmanagement.infrastructure;

import kr.co.hanbit.productmanagement.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product){
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }

}
