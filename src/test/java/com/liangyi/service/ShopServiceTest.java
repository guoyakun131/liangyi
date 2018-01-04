package com.liangyi.service;

import com.liangyi.entity.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ShopServiceTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void goodsComment() {
        shopService.goodsComment(19);
        //System.out.println(map);
    }
}