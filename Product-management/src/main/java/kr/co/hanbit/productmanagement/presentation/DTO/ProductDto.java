package kr.co.hanbit.productmanagement.presentation.DTO;

import jakarta.validation.constraints.NotNull;
import kr.co.hanbit.productmanagement.domain.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private Integer amount;

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto(String name, Integer price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public static Product toEntity(ProductDto productDto){
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getAmount()
        );

        return product;
    }
    public static ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAmount()
        );

        return productDto;
    }
}
