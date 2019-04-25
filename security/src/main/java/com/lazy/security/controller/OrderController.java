package com.lazy.security.controller;

import com.lazy.security.dto.Order;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Controller
public class OrderController {

    @Autowired
    private AccessToken accessToken;

    @Autowired
    private KeycloakSecurityContext securityContext;


    @GetMapping({"/orders"})
    public String orders( Map<String, Object> model) {
        model.put("orders", getOrderList());
        model.put("name", accessToken.getName());
        model.put("token", securityContext.getTokenString());
        return "orders";
    }

    @GetMapping({"/"})
    public String home(Map<String, Object> model) {
        return "home";
    }

    @GetMapping({"/logout"})
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "home";
    }

    private List<Order> getOrderList() {
        return IntStream.range(1, 4)
                .mapToObj(i -> new Order(UUID.randomUUID().toString(), "product " + i, "customer " + i, "$ " + i * 10))
                .collect(toList());
    }


}