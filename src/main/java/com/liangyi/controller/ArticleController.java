package com.liangyi.controller;

import com.liangyi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图文
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 分类名
     * @return
     */
    @PostMapping("typeNameList")
    public List<Map<String,Object>> typeNameList(){
        return articleService.typeNameList();
    }

    /**
     * 图文列表
     *
     * @param type
     * @return
     */
    @PostMapping("articleList")
    public Map<String, Object> article(int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("articleList", articleService.articlesList(type));
        return map;
    }

    /**
     * 图文详情
     *
     * @param id
     * @return
     */
    @PostMapping("articleInfo")
    public Map<String, Object> articleInfo(int id) {
        return articleService.article(id);
    }

    /**
     * 浏览
     * @param session_id
     * @param id
     */
    @PostMapping("browse")
    public void userBrowse(String session_id, Integer id) {
        articleService.userBrowse(session_id, id);
    }

    @PostMapping("dashang")
    public Map<String, String> dashang(HttpServletRequest request, String session_id, Integer id, String jine) throws Exception {
        System.out.println(jine+id);
       return articleService.order(request,session_id,id,jine);
    }

    @PostMapping("notify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        articleService.wxNotify(request, response);
    }
}
