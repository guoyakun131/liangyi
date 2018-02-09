package com.liangyi.service.admin;

import com.liangyi.Utils.FileUtil;
import com.liangyi.config.Config;
import com.liangyi.entity.Article;
import com.liangyi.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ArticleAdminService {

    @Autowired
    private ArticleMapper articleMapper;

    /***
     * 后台查询分类名列表
     * @return
     */
    public List<Map<String, Object>> adminTypeNameList() {
        return articleMapper.adminTypeNameList();
    }

    /**
     * 保存分类名
     * @param id
     * @param typeName
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveTypeName(Integer id, String typeName) {
        if (id == null) {
            articleMapper.addTypeName(typeName);
            return "保存成功";
        } else {
            articleMapper.upTypeName(id, typeName);
            return "修改成功";
        }
    }

    /**
     * 删除分类名
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String delTypeName(Integer id){
        try{
            articleMapper.delTypeName(id);
            return "成功";
        }catch (Exception e){
            e.printStackTrace();
            return "失败";
        }

    }

    /**
     * 编辑查询
     * @param id
     * @return
     */
    public String EnditTypeName(Integer id){
        return articleMapper.EnditType(id);
    }

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
                articleMapper.saveArticleOnImg(article);
                return "添加成功,请上传图片！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }
    }

    /**
     * 更新
     *
     * @param article
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveArticleEdit(Article article, MultipartFile file) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        article.setTime(sdf.format(date).toString());
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                FileUtil.uploadFile(file.getBytes(), Config.filePath, fileName);
                article.setImg("image/upload/" + fileName);
                articleMapper.updateArticle(article);
                return "修改成功";
            } else {
                articleMapper.updateArticleOnImg(article);
                return "修改成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
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
