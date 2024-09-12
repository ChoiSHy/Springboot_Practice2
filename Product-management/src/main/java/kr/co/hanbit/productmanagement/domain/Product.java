package kr.co.hanbit.productmanagement.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public Product(String name, Integer price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean sameId(Long id) {
        // Getter 없이 이름 비교를 위해 존재
        return this.id.equals(id);
    }

    public Boolean containsName(String name) {
        // Getter 없이 이름 비교를 위해 존재
        return this.name.contains(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;
        Product product = (Product) obj;
        return Objects.equals(id, product.id);  // 두 객체의 id만 같아도 같은 정보라고 인식
    }
}
