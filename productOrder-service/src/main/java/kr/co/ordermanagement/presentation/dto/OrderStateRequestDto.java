package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.State;
import lombok.Getter;

@Getter
public class OrderStateRequestDto {
    State state;
}
