package com.example.store.services;

import com.example.store.dtos.OrderRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Order;
import com.example.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido con el id " + id + " no encontrado")
        );
    }

    public Order createOrder(OrderRequest newOrder) {
        Order order = new Order();
        order.setOrderDate(newOrder.orderDate());
        order.setOrderStatus(newOrder.orderStatus());
        order.setOrderAmount(newOrder.orderAmount());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, OrderRequest updateOrder) {
        Order oldOrder = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido con el id " + id + " no encontrado")
        );
        if (updateOrder.orderDate() != null) {
            oldOrder.setOrderDate(updateOrder.orderDate());
        }
        if (updateOrder.orderStatus() != null) {
            oldOrder.setOrderStatus(updateOrder.orderStatus());
        }
        if (updateOrder.orderAmount() != null) {
            oldOrder.setOrderAmount(updateOrder.orderAmount());
        }
        return orderRepository.save(oldOrder);
    }

    public Order deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido con el id " + id + " no encontrado")
        );
        orderRepository.delete(order);
        return order;
    }
}