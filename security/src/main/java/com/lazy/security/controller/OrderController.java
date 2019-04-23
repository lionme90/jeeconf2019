package com.lazy.security.controller;

import com.lazy.security.dto.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Controller
public class OrderController {


    @GetMapping({"/","/orders"})
    public String welcome(Map<String, Object> model) {

        model.put("orders", getOrderList());
        return "orders";
    }

    private List<Order> getOrderList(){

        return IntStream.range(1,4)
                .mapToObj(i-> new Order(UUID.randomUUID().toString(),"product "+i,"customer "+i,"$ "+i*10))
        .collect(toList());
    }



}