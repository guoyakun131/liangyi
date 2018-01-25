package com.liangyi.service.admin;

import com.liangyi.Utils.FileUtil;
import com.liangyi.config.Config;
import com.liangyi.entity.Goods;
import com.liangyi.entity.Img;
import com.liangyi.mapper.GoodsMapper;
import com.liangyi.mapper.ImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
     *
     * @param goods
     * @return
     */
    @Transactional
    public String addGoods(MultipartFile file, Goods goods) {
        String fileName = file.getOriginalFilename();
        try {
            if (!file.isEmpty()) {
                FileUtil.uploadFile(file.getBytes(), Config.filePath, fileName);
                goods.setImg("image/upload/" + fileName);
                goodsMapper.addGoods(goods);
                return "成功";
            } else {
                goodsMapper.addGoods(goods);
                return "保存成功，请及时添加商品图片";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 商品轮播图添加
     * @param file
     * @param goods_id
     * @return
     */
    public String saveImg(MultipartFile file, int goods_id) {
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                FileUtil.uploadFile(file.getBytes(), Config.filePath, fileName);
                imgMapper.editImg(goods_id,"image/upload/"+fileName);
                return "添加成功";
            } else {
                return "文件不得为空！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 保存编辑商品
     *
     * @param file
     * @param goods
     * @return
     */
    @Transactional
    public String editGoods(MultipartFile file, Goods goods) {
        String fileName = file.getOriginalFilename();
        try {
            if (!file.isEmpty()) {
                FileUtil.uploadFile(file.getBytes(), Config.filePath, fileName);
                goods.setImg("image/upload/" + fileName);
                goodsMapper.editGoods(goods);
                return "成功";
            } else {
                goodsMapper.editGoodsNoImg(goods);
                return "成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        for (Img img : imgList) {
            img.setImg(Config.url + img.getImg());
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
