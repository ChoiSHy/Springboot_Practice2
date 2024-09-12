package kr.co.hanbit.productmanagement.infrastructure;

import kr.co.hanbit.productmanagement.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product){
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }
    public Product findById(Long id){
        return products.stream()
                .filter(product -> product.sameId(id))  // list내에서 id에 해당하는 값들만 선별
                .findFirst()    // 선별된 값들중 첫번쨰값 반환
                .orElseThrow(); // 없으면 throw
    }

    public List<Product> findAll(){
        return products;
    }
    public List<Product> findByName(String name){
        return products.stream()
                .filter(product -> product.containsName(name))  // name을 포함한 객체들만 선별
                .toList();
    }

    public Product update(Product product){
        Integer indexToModify = products.indexOf(product);  // 수정할 정보의 인덱스 번호 가져오기
        products.set(indexToModify, product);   // 리스트에서 해당 인덱스 정보 수정
        return products.get(indexToModify);
    }

}
