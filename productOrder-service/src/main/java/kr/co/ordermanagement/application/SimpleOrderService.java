package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
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


    public OrderResponseDto createOrder(List<OrderRequestDto> orderDtos) {
        List<Product> orderedProducts = makeOrderedProducts(orderDtos); // OrderRequestDto -> Product( 재고 체크 )
        decreaseProductsAmount(orderedProducts);    // 주문량만큼 재고 감소

        Order order = new Order(orderedProducts);
        Order savedOrder = orderRepository.add(order);

        OrderResponseDto responseDto = OrderResponseDto.toDto(savedOrder);
        return responseDto;


    }

    private List<Product> makeOrderedProducts(List<OrderRequestDto> orderRequestDtos) {
        return orderRequestDtos.stream()
                .map(orderRequestDto -> {
                    Long productId = orderRequestDto.getId();
                    Product product = productRepository.findById(productId);
                    Integer orderedAmount = orderRequestDto.getAmount();

                    product.checkEnoughAmount(orderedAmount);

                    return new Product(
                            productId,
                            product.getName(),
                            product.getPrice(),
                            orderRequestDto.getAmount()
                    );
                }).toList();
    }
    private void decreaseProductsAmount(List<Product> orderedProducts) {
        orderedProducts.stream()
                .forEach(orderedProduct->{
                    Long productId = orderedProduct.getId();
                    Product product = productRepository.findById(productId);

                    Integer orderAmount = orderedProduct.getAmount();
                    product.decreaseAmount(orderAmount);

                    productRepository.update(product);
                });
    }
}
