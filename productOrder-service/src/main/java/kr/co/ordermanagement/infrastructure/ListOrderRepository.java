package kr.co.ordermanagement.infrastructure;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ListOrderRepository implements OrderRepository {
    private List<Order> orderList = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);
    @Override
    public Order add(Order order) {
        order.setId(sequence.getAndAdd(1L));

        orderList.add(order);
        return order;
    }

    @Override
    public Order findById(Long orderId) {
        return orderList.stream()
                .filter(order -> order.sameId(orderId))
                .findFirst()
                .orElseThrow(()->new EntityNotFoundException("Order를 찾지 못했습니다."));
    }

    @Override
    public List<Order> findByOrderState(String state) {
        return orderList.stream()
                .filter(order -> order.sameState(state))
                .toList();
    }

}
