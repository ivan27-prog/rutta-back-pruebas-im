package com.example.store.controllers;

import com.example.store.dtos.OrderRequest;
import com.example.store.models.Order;
import com.example.store.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/order") // http://localhost:8080/api/order
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(location).body(order);
    }

    @PutMapping(path = "{orderId}")
    public ResponseEntity<Order> updateOrderById(
            @PathVariable("orderId") Long id,
            @RequestBody OrderRequest orderRequest) {
        Order order = orderService.updateOrder(id, orderRequest);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping(path = "{orderId}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable("orderId") Long id) {
        Order order = orderService.deleteOrderById(id);
        return ResponseEntity.ok(order);
    }
}