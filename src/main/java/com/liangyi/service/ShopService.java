package com.liangyi.service;

import com.liangyi.entity.Goods;
import com.liangyi.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询首页Tab显示的数字
     * @return
     */
    public Map<String, Map<String, Integer>> getNums() {
        Map<String, Map<String, Integer>> maps = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();
        //查询所有商品数量给首页“Tab全部”显示
        map.put("all", goodsMapper.queryGoodsAll());
        //查询上新商品数量给首页“Tab上新”显示
        map.put("new", goodsMapper.queryGoodsNew());
        //查询热销商品数量给首页“Tab热销”显示
        map.put("hot", goodsMapper.queryGoodsHot());
        maps.put("result",map);
        return maps;
    }

    /**
     * 查询所有商品
     * @return
     */
    public List<Goods> goodsAll(){
        return goodsMapper.queryGoods(0,10);
    }
}
