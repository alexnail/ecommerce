package com.ecommerce.client.controller;


import com.ecommerce.client.dto.OrderDTO;
import com.ecommerce.client.dto.ProductDTO;
import com.ecommerce.client.proxies.MicroserviceOrderProxy;
import com.ecommerce.client.proxies.MicroserviceProductsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private MicroserviceProductsProxy productsProxy;

    @Autowired
    private MicroserviceOrderProxy ordersProxy;

    @GetMapping("/products")
    public List<ProductDTO> findAll(){
        return productsProxy.findAll();
    }

    @GetMapping("/findById/{id}")
    public ProductDTO findById(@PathVariable int id){
        return productsProxy.findById(id);
    }

    @PostMapping(value = "/Order-product")
    public OrderDTO addOrder(@RequestBody ProductDTO productDTO){
        OrderDTO order = new OrderDTO();
        order.setProductId(productDTO.getId());
        order.setQuantity(1);
        order.setDate(new Date());

        return ordersProxy.addOrder(order);
    }


}
