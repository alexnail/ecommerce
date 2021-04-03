package com.ecommerce.product.web.controller;


import com.ecommerce.product.dao.ProductDao;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.web.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping(value = "/products")
    public List<Product> findAll(){
        List<Product> products = productDao.findAll();
        if(products.isEmpty()) throw new ProductNotFoundException("No Product is available for sale");
        log.info("Retrieving the list of products");
        return products;
    }

    //Récuperer un produit par son id
    @GetMapping( value = "/products/{id}")
    public Optional<Product> findById(@PathVariable int id) {
        Optional<Product> product = productDao.findById(id);
        if(!product.isPresent())  throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");
        return product;
    }
}

