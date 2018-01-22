package com.liangyi.controller.admin;

import com.liangyi.entity.Goods;
import com.liangyi.service.admin.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/admin/Goods"})
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品列表
     *
     * @param goodsName
     * @param model
     * @return
     */
    @GetMapping("index")
    public String goodsList(String goodsName, Model model) {
        model.addAttribute("goodsList", goodsService.goodsList(goodsName));
        return "views/goods/index";
    }

    /**
     * 商品轮播图
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("img_list")
    public String imglist(Integer id, Model model) {
        model.addAttribute("imgList", goodsService.imgLsit(id));
        return "views/goods/img_list";
    }

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    @PostMapping("del_img")
    @ResponseBody
    public String delImg(Integer id) {
        return goodsService.delImg(id);
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @PostMapping("del_goods")
    @ResponseBody
    public String delGoods(Integer id) {
        return goodsService.delGoods(id);
    }

    /**
     * 编辑商品
     *
     * @param id
     * @return
     */
    @GetMapping("edit_goods")
    public String editGoods(int id, Model model) {
        model.addAttribute("goods", goodsService.goods(id));
        return "views/goods/edit_goods";
    }

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @PostMapping("save_Goods")
    @ResponseBody
    public String addGoods(Goods goods) {
        return goodsService.addGoods(goods);
    }
}
