package com.liangyi.service;

import com.liangyi.entity.Comment;
import com.liangyi.entity.Goods;
import com.liangyi.entity.UserComment;
import com.liangyi.mapper.CommentMapper;
import com.liangyi.mapper.GoodsMapper;
import com.liangyi.mapper.ImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ImgMapper imgMapper;

    @Autowired
    private CommentMapper commentMapper;
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
     * 按分类查询商品
     * @param type
     * @return
     */
    public List<Goods> goodsList(int type){
        return goodsMapper.queryGoods(type);
    }

    /**
     * 全部商品查询
     * @return
     */
    public List<Goods> goodsList(){
        return goodsMapper.queryGoodsList();
    }

    /**
     * 按id查询商品详情
     * @param id
     * @return
     */
    public Map<String,Goods> goodsContent(int id){
        Map<String,Goods> goodsMap = new HashMap<String,Goods>();
        //查询出商品详情
       Goods goods = goodsMapper.goodsContent(id);
       //查询出商品对应的轮播图
       goods.setImgs(imgMapper.imgLsit(id));
       //放入Map
        goodsMap.put("result",goods);
      return goodsMap;
    }

    /**
     * 查询商品评论
     * @param id
     * @return
     */
    public Map<String,List<UserComment>> goodsComment(int id){
        Map<String,List<UserComment>> map = new HashMap<>();
        map.put("result",commentMapper.goodsComment(id));
        return map;
    }
}
