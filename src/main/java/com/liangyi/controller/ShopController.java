package com.liangyi.controller;

import com.liangyi.entity.Address;
import com.liangyi.entity.Goods;
import com.liangyi.entity.UserComment;
import com.liangyi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Shop")
public class ShopController {

    @Autowired
    private ShopService shopService;


    /**
     * 查询首页Tab显示的数字
     *
     * @return map
     */
    @RequestMapping(value = "/goods_count", method = RequestMethod.POST)
    public Map<String, Map<String, Integer>> goodsCount() {
        return shopService.getNums();
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping(value = "/goods_list", method = RequestMethod.POST)
    public Map<String, List<Goods>> goodsList(HttpServletRequest request) {
        //分类
        int type = Integer.valueOf(request.getParameter("type"));
        System.out.println(type);
        Map<String, List<Goods>> map = new HashMap<>();
        if (type == 1 || type == 2) {
            //分类查询
            map.put("result", shopService.goodsList(type));
        } else {
            //全部商品查询
            map.put("result", shopService.goodsList());
        }
        return map;
    }

    /**
     * 按 id查询商品详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/goods_info", method = RequestMethod.POST)
    public Map<String, Goods> goodsInfo(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        return shopService.goodsContent(id);
    }

    /**
     * 查询商品评论
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/goods_comment", method = RequestMethod.POST)
    public Map<String, List<UserComment>> goodsComment(int id) {
        return shopService.goodsComment(id);
    }

    /**
     * 订单确认初始化
     * @param request
     * @return
     */
    @PostMapping("conf_order")
    public Map<String, Object> confOrder(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        String sessionId = request.getParameter("session_id");
        //得到商品id
        String cartSel = request.getParameter("cart_sel");

        System.out.println("cart_sel" + cartSel);
        Address address = shopService.addrSel(sessionId);
        List<Goods> goods = shopService.cartListSel(cartSel, sessionId);
        map.put("isError", true);
        map.put("addr_sel", address);
        map.put("cart_list_sel", goods);
        return map;
    }
}
