package com.skillsunion.shoppingcartapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/*
    Assignment:
    - Apply @RestController to the class.
    - Apply @GetMapping and @PostMapping to the methods.
    - Apply @PathVariable to the method parameter of get(int).

    Test:
    - Use a client HTTP Tool like YARC to consume the API you have just created.
    E.g. https://chrome.google.com/webstore/detail/yet-another-rest-client/ehafadccdcdedbhcbddihehiodgcddpl?hl=en
*/

@RestController
@RequestMapping("/cart")
public class CartController {

    @GetMapping("")
    public String list() {
        return "GET /carts ok";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id) {
        return "GET /carts/" + id + " ok";
    }

    @PostMapping("")
    public String create() {
        return "POST /carts ok";
    }
}

@Configuration
class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins("http://localhost:8080") // You can specify your frontend's origin here
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*"); // Allow all headers
    }
}