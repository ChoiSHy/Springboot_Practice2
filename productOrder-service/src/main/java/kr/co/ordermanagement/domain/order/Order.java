package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.exception.NotCancelableException;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class Order {
    private Long id;
    private List<Product> orderedProducts;
    private Integer totalPrice;
    private State state;

    public Order(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
        this.totalPrice = calculateTotalPrice(orderedProducts);
        this.state = State.CREATED;
    }

    private Integer calculateTotalPrice(List<Product> orderedProducts) {
        return orderedProducts.stream()
                .mapToInt(orderedProduct -> orderedProduct.getPrice() * orderedProduct.getAmount())
                .sum();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public Boolean sameState(State state) {
        return this.state.equals(state);
    }

    public void cancel() {
        if(!this.state.equals(State.CREATED))
            throw new NotCancelableException("이미 취소되었거나 취소할 수 없는 주문상태입니다.");
        this.state = State.CANCELED;
    }
}
