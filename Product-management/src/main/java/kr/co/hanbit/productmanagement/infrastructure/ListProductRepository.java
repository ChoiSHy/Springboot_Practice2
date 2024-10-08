package kr.co.hanbit.productmanagement.infrastructure;

import kr.co.hanbit.productmanagement.domain.Exception.EntityNotFoundException;
import kr.co.hanbit.productmanagement.domain.Product;
import kr.co.hanbit.productmanagement.domain.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/*
* 단순 애플리케이션 내의 기능만으로 데이터를 처리하는 것을 배우는 Repository.
* 휘발성 데이터이기 때문에 실질적으로 사용하지는 않겠지만, JAVA 기능 및 레이어드 설계를 이해하기 위해 연습
*
* */
@Repository
@Profile("test")
public class ListProductRepository implements ProductRepository {
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
                .orElseThrow(()->new EntityNotFoundException("Product를 찾지 못했습니다.")); // 없으면 EntityNotFoundException throw
    }

    public List<Product> findAll(){
        return products;
    }
    public List<Product> findByNameContaining(String name){
        return products.stream()
                .filter(product -> product.containsName(name))  // name을 포함한 객체들만 선별
                .toList();
    }

    public Product update(Product product){
        Integer indexToModify = products.indexOf(product);  // 수정할 정보의 인덱스 번호 가져오기
        products.set(indexToModify, product);   // 리스트에서 해당 인덱스 정보 수정
        return products.get(indexToModify);
    }
    public void delete(Long id){
        Product product = this.findById(id);    // 삭제할 상품을 검색해
        products.remove(product);               // 목록에서 삭제
    }
}
