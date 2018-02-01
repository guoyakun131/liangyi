package com.liangyi.service.admin;

import com.liangyi.Utils.FileUtil;
import com.liangyi.config.Config;
import com.liangyi.entity.Article;
import com.liangyi.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArticleAdminService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 后台图文列表
     *
     * @return
     */
    public List<Article> articleList() {
        return articleMapper.articleList();
    }

    /**
     * 图文添加
     *
     * @param article
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveArticle(Article article, MultipartFile file) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        article.setTime(sdf.format(date).toString());
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                FileUtil.uploadFile(file.getBytes(), Config.filePath, fileName);
                article.setImg("image/upload/" + fileName);
                articleMapper.saveArticle(article);
                return "添加成功";
            } else {
                articleMapper.updateArticle(article);
                return "成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }
    }

    /**
     * 删除图文
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String delArticle(int id) {
        try {
            articleMapper.delArticle(id);
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 文章发布和下架
     *
     * @param id
     * @param state
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String state(int id, int state) {
        try {
            articleMapper.ArticleState(id, state);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }

    }

    /**
     * 编辑查询
     *
     * @param id
     * @return
     */
    public Article articleEdit(int id) {
        return articleMapper.articleEdit(id);
    }

}
