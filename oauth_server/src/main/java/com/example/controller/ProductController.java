package com.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john
 * @date 2020/1/11 - 19:07
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/findAll")
   // @Secured("ROLE_PRODUCT")
    public String hello() {
        return "product-list";
    }
}
