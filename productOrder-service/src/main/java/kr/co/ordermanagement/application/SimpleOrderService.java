package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.exception.NotCancelableException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.OrderStateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleOrderService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    /* 주문 생성 */
    public OrderResponseDto createOrder(List<OrderRequestDto> orderDtos) {
        List<Product> orderedProducts = makeOrderedProducts(orderDtos); // OrderRequestDto -> Product( 재고 체크 )
        decreaseProductsAmount(orderedProducts);    // 주문량 만큼 재고 감소

        Order order = new Order(orderedProducts);
        Order savedOrder = orderRepository.add(order);  // 주문 내역 저장

        OrderResponseDto responseDto = OrderResponseDto.toDto(savedOrder);  // Entity -> Dto
        return responseDto;


    }
    /* 재고가 충분한 지 확인한 뒤에, 주문한 상품 반환 */
    private List<Product> makeOrderedProducts(List<OrderRequestDto> orderRequestDtos) {
        return orderRequestDtos.stream()
                .map(orderRequestDto -> {
                    Long productId = orderRequestDto.getId();
                    Product product = productRepository.findById(productId);
                    Integer orderedAmount = orderRequestDto.getAmount();

                    product.checkEnoughAmount(orderedAmount);   // 재고량 검사

                    return new Product(
                            productId,
                            product.getName(),
                            product.getPrice(),
                            orderRequestDto.getAmount()
                    );
                }).toList();
    }
    /* 상품의 재고 감소 */
    private void decreaseProductsAmount(List<Product> orderedProducts) {
        orderedProducts.stream()
                .forEach(orderedProduct->{
                    Long productId = orderedProduct.getId();
                    Product product = productRepository.findById(productId);

                    Integer orderAmount = orderedProduct.getAmount();
                    product.decreaseAmount(orderAmount);

                    //productRepository.update(product);
                });
    }
    /* id를 통해 주문 조회 */
    public OrderResponseDto findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId);

        OrderResponseDto responseDto = OrderResponseDto.toDto(order);
        return responseDto;
    }

    public OrderResponseDto changeOrderStateById(Long orderId, OrderStateRequestDto requestDto) {
        Order order = orderRepository.findById(orderId);
        State state = requestDto.getState();
        order.setState(state);

        OrderResponseDto responseDto = OrderResponseDto.toDto(order);
        return responseDto;
    }

    public List<OrderResponseDto> findOrdersByOrderState(State state) {
        List<Order> orderList = orderRepository.findByOrderState(state);
        List<OrderResponseDto> orderResponseDtoList = orderList.stream()
                .map(order -> OrderResponseDto.toDto(order))
                .toList();
        return orderResponseDtoList;
    }

    public OrderResponseDto cancelOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();

        OrderResponseDto responseDto = OrderResponseDto.toDto(order);
        return responseDto;
    }
}
