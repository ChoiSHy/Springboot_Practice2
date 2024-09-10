package kr.co.hanbit.productmanagement.application;

import kr.co.hanbit.productmanagement.domain.Product;
import kr.co.hanbit.productmanagement.infrastructure.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductService {
    private ProductRepository productRepository;

    @Autowired
    SimpleProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public Product add(Product product){
        Product savedProduct = productRepository.add(product);
        return savedProduct;
    }

}
