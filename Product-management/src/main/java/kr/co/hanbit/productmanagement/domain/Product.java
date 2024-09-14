package kr.co.hanbit.productmanagement.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@NoArgsConstructor
public class Product {

    private Long id;
    @Size(min = 1, max = 100)
    private String name;
    @Max(1_000_000)
    @Min(0)
    private Integer price;
    @Max(9_999)
    @Min(0)
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
