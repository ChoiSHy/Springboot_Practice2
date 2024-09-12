package kr.co.hanbit.productmanagement.presentation.DTO;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
    public void setId(Long id) {
        this.id = id;
    }
}
