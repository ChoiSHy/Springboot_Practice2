package kr.co.ordermanagement.presentation.dto;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    Long id;
    Integer amount;

    public OrderRequestDto(Long id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }
}
