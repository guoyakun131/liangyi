package com.liangyi.service.admin;

import com.liangyi.config.Config;
import com.liangyi.entity.Goods;
import com.liangyi.entity.Img;
import com.liangyi.mapper.GoodsMapper;
import com.liangyi.mapper.ImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ImgMapper imgMapper;

    /**
     * 后台查询商品列表
     *
     * @return
     */
    public List<Goods> goodsList(String goodsName) {
        if (goodsName == null) {
            return goodsMapper.goodsList();
        } else {
            System.out.println(goodsName);
            return goodsMapper.goodsListByName(goodsName);
        }
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @Transactional
    public String addGoods(Goods goods) {
        try {
            goodsMapper.addGoods(goods);
            return "成功";
        } catch (Exception e) {
            return "失败";
        }

    }

    /**
     * 后台商品图片查询
     *
     * @param id
     * @return
     */
    public List<Img> imgLsit(int id) {
        List<Img> imgList = imgMapper.imgLsit(id);
        String imgUrl = Config.url;
        for (Img img : imgList) {
            imgUrl += img.getImg();
            img.setImg(imgUrl);
        }
        return imgList;
    }

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    @Transactional
    public String delImg(int id) {
        try {
            imgMapper.delImg(id);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @Transactional
    public String delGoods(int id) {
        try {
            goodsMapper.delGoods(id);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 编辑商品查询
     *
     * @param id
     * @return
     */
    public Goods goods(int id) {
        Goods goods = goodsMapper.goodsContent(id);
        goods.setImg(Config.url + goods.getImg());
        return goods;
    }
}
