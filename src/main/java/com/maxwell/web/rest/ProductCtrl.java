package com.maxwell.web.rest;

import com.maxwell.domain.Product;
import com.maxwell.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by matexo on 22.05.16.
 */
@RestController
@RequestMapping(value="api/product")
public class ProductCtrl {

    @Inject
    private ProductService productService;

    @RequestMapping(value="" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.getProductsList() , HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        return productService.addProduct(product)
                .map(addedProduct -> new ResponseEntity<>(addedProduct.getId() , HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
