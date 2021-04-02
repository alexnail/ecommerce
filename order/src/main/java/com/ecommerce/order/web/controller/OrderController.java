package com.ecommerce.order.web.controller;

import com.ecommerce.order.dao.OrderDao;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.web.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    private final OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @PostMapping (value = "/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order newOrder = orderDao.save(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping(value = "/orders/{id}")
    public Optional<Order> findById(@PathVariable int id){
        Optional<Order> order = orderDao.findById(id);
        if(!order.isPresent()) throw new OrderNotFoundException("This order doesn't exist");
        return order;
    }

    @PutMapping(value = "/orders")
    public void updateOrder(@RequestBody Order order) {
        orderDao.save(order);
    }
}
