package lk.bookshop.bookshopbackend.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.bookshop.bookshopbackend.dto.OrderStatusDto;
import lk.bookshop.bookshopbackend.entity.Order;
import lk.bookshop.bookshopbackend.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderByOrderID(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @Override
    public Order savOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderID, OrderStatusDto order) {
        Order tempOrder = orderRepository.findById(orderID).orElse(null);
        if (tempOrder != null) {
            tempOrder.setStatus(order.getOrderStatus());
            return orderRepository.save(tempOrder);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderID) {
        orderRepository.deleteById(orderID);
    }

    @Override
    public List<Order> getAllOrdersByUserID(Long userID) {
        return orderRepository.findOrdersByUserId(userID);
    }

}
