package kr.co.hanbit.productmanagement.presentation.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
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
}
