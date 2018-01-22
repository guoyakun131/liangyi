package com.liangyi.controller.admin;

import com.liangyi.service.admin.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.soap.Addressing;

@Controller
@RequestMapping({"/admin/Login"})
public class OrderController {

    @Addressing
    private OrderService orderService;
}
