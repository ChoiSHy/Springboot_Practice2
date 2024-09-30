package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.presentation.dto.OrderRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.OrderStateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRestController {
    private SimpleOrderService simpleOrderService;

    @Autowired
    public OrderRestController(SimpleOrderService simpleOrderService){
        this.simpleOrderService = simpleOrderService;
    }
    @RequestMapping(value = "/orders",method = RequestMethod.POST)
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody List<OrderRequestDto> orderDtos){
        OrderResponseDto responseDto = simpleOrderService.createOrder(orderDtos);
        return ResponseEntity.ok(responseDto);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderResponseDto> findOrderById(@PathVariable Long orderId){
        OrderResponseDto responseDto = simpleOrderService.findOrderById(orderId);
        return ResponseEntity.ok(responseDto);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PATCH)
    public ResponseEntity<OrderResponseDto> changeOrderStatusById(@PathVariable Long orderId, @RequestBody OrderStateRequestDto requestDto){
        OrderResponseDto responseDto = simpleOrderService.changeOrderStateById(orderId, requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<OrderResponseDto>> findOrdersByOrderState(@RequestParam State state){
        List<OrderResponseDto> orderList = simpleOrderService.findOrdersByOrderState(state);
        return ResponseEntity.ok(orderList);
    }

    @RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.PATCH)
    public ResponseEntity<OrderResponseDto> cancelOrderById(@PathVariable Long orderId){
        OrderResponseDto responseDto = simpleOrderService.cancelOrderById(orderId);
        return ResponseEntity.ok(responseDto);
    }
}
