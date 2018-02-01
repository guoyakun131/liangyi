package com.liangyi.controller.admin;

import com.liangyi.entity.Article;
import com.liangyi.service.admin.ArticleAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图文
 */
@Controller
@RequestMapping({"/admin/Article"})
public class ArticleAdminController {

    @Autowired
    private ArticleAdminService articleService;

    /**
     * 图文列表
     *
     * @param model
     * @return
     */
    @GetMapping("index")
    public String articleList(Model model) {
        model.addAttribute("articleList", articleService.articleList());
        return "views/article/index";
    }

    /**
     * 图文添加
     *
     * @param article
     * @param file
     * @return
     */
    @PostMapping("save_Article")
    @ResponseBody
    public String saveArticle(Article article, @RequestParam("file") MultipartFile file) {
        return articleService.saveArticle(article, file);
    }

    /**
     * 图文删除
     *
     * @param id
     * @return
     */
    @PostMapping("del_Article")
    @ResponseBody
    public String delArticle(int id) {
        return articleService.delArticle(id);
    }

    /**
     * 上下架
     *
     * @param id
     * @param state
     * @return
     */
    @PostMapping("State")
    @ResponseBody
    public String state(int id, int state) {
        return articleService.state(id, state);
    }

    /**
     * 编辑查询
     * @param id
     * @param model
     * @return
     */
    @GetMapping("articleEdit")
    public String articleEdit(int id, Model model) {
        model.addAttribute("article", articleService.articleEdit(id));
        return "views/article/edit_article";
    }
}
