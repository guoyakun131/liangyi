package com.liangyi.controller;

import com.liangyi.entity.Address;
import com.liangyi.entity.Goods;
import com.liangyi.entity.Order;
import com.liangyi.entity.UserComment;
import com.liangyi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 首页通告
     *
     * @return
     */
    @PostMapping("g")
    public List<String> g() {
        return shopService.g();
    }

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
     *
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
        try {
            Address address = shopService.addrSel(sessionId);
            List<Goods> goodsList = shopService.cartListSel(cartSel, sessionId);
            double price = 0;
            //double expressSum = 0;
            for (Goods goods : goodsList) {
                price += goods.getPrice() * goods.getNums();
                // expressSum += goods.getExpress();
            }
            System.out.println("price" + price);
            map.put("isError", true);
            map.put("addr_sel", address);
            map.put("cart_list_sel", goodsList);
            map.put("goods_sum", price);
            //map.put("express_sum", expressSum);
            map.put("all_sum", price);
        } catch (Exception e) {
            map.put("isError", false);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 收货地址列表
     *
     * @param session_id
     * @return
     */
    @PostMapping("addr_list")
    public Map<String, Object> addrList(String session_id) {
        return shopService.findAddressList(session_id);
    }

    /**
     * 收货详情
     *
     * @param addr_id
     * @return
     */
    @PostMapping("get_addr_info")
    public Map<String, Object> getAddrInfo(int addr_id) {
        return shopService.addrInfo(addr_id);
    }

    /**
     * 保存修改的收货详情
     *
     * @param address
     * @param session_id
     * @return
     */
    @PostMapping("edit_addr")
    public Map<String, Object> editAddr(Address address, String session_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("isError", shopService.editAddr(address, session_id));
        return map;
    }

    /**
     * 新增收货详情
     *
     * @param address
     * @param session_id
     * @return
     */
    @PostMapping("add_addr")
    public Map<String, Object> addAddr(Address address, String session_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("isError", shopService.addAddress(session_id, address));
        return map;
    }

    /**
     * 删除收货详情
     *
     * @param address_id
     * @param session_id
     * @return
     */
    @PostMapping("del_address")
    public Map<String, Object> delAddress(int address_id, String session_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("isError", shopService.delAddress(address_id, session_id));
        return map;
    }

    /**
     * 生成订单
     *
     * @param order
     * @param session_id
     * @param cart_sel
     * @return
     */
    @PostMapping("add_order")
    public Map<String, Object> addOrder(Order order, String session_id, String cart_sel) {
        System.out.println(cart_sel);
        System.out.println(order);
        Map<String, Object> map = new HashMap<>();
        map.put("isError", shopService.addOrder(order, session_id, cart_sel));
        return map;
    }


}
