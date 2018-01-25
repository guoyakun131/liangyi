package com.liangyi.controller.admin;

import com.liangyi.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.soap.Addressing;

@Controller
@RequestMapping({"/admin/Order"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 后台查询所有订单列表
     *
     * @param model
     * @return
     */
    @GetMapping("index")
    public String indexOrder(Model model, String orderNum) {
        model.addAttribute("orderList", orderService.orderList(orderNum));
        return "views/order/index";
    }

    /**
     * 订单中的商品
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("order_goods")
    public String orderGoods(Integer id, Model model) {
        System.out.println(id);
        model.addAttribute("orderGoodsList", orderService.orderGoodsList(id));
        return "views/order/order_goods";
    }

    /**
     * 买家信息
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("userInfo")
    public String userInfo(Integer userId, Model model) {
        model.addAttribute("user", orderService.userInfo(userId));
        model.addAttribute("address", orderService.address(userId));
        return "views/order/member-show";
    }

    /**
     * 发货
     *
     * @param orderId
     * @param expressNum
     * @param expCode
     * @return
     */
    @PostMapping("fahuo")
    public String fahuo(int orderId, String expressNum, String expCode) {
        orderService.fahuo(orderId, expressNum, expCode);
        return "redirect:index";
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @PostMapping("del_order")
    @ResponseBody
    public String delOrder(int orderId) {
        return orderService.delOrder(orderId);
    }
}
