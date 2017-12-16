package com.liangyi.controller;

import com.liangyi.entity.Goods;
import com.liangyi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * @return map
     */
    @RequestMapping(value = "/goods_count", method = RequestMethod.POST)
    public Map<String, Map<String, Integer>> goodsCount() {
        return shopService.getNums();
    }

    /**
     * 查询所有商品
     * @return
     */
    @RequestMapping(value = "/goods_list", method = RequestMethod.POST)
    public Map<String,List<Goods>> goodsList(){
        Map<String,List<Goods>> map = new HashMap<>();
        map.put("result",shopService.goodsAll());
        return map;
    }
}
