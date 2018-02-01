package com.liangyi.mapper;

import com.liangyi.entity.Article;
import com.liangyi.entity.Goods;
import com.liangyi.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 图文mapper
 */
@Mapper
public interface ArticleMapper {

    /**
     * 热门文章查询
     *
     * @return
     */
    @Select("SELECT id,img,title,author,browse from article where type = 0 and browse >= 100 and state = 1 ORDER BY browse DESC")
    List<Article> article();

    /**
     * 按类型查询文章
     * @param type
     * @return
     */
    @Select("SELECT id,img,title,author,browse from article where type = #{type} and state = 1 ORDER BY browse DESC")
    List<Article> articleType(int type);

    /**
     * 文章详情
     * @param id
     * @return
     */
    @Select("SELECT id,title,author,time,img,content,bounty from article where id = #{id}")
    Article articleInfo(int id);

    /**
     * 文章中显示的商品
     * @param id
     * @return
     */
    @Select("SELECT g.id,g.img,g.price from article a,goods g where a.id = #{id} and a.goods = g.id")
    Goods goods(int id);

    /**
     *
     * @param id
     * @return
     */
    @Select("SELECT u.avatar from ua a,user u where a.article_id = #{id} and u.id = a.user_id")
    List<User> avatar(int id);

    /**
     * 浏览量加1
     * @param id
     */
    @Select("UPDATE article set browse = browse+1 where id = #{id}")
    void browse(int id);

    /**
     * 打赏人数加1
     * @param id
     */
    @Select("UPDATE article set bounty = bounty+1 where id = #{id}")
    void bounty(int id);
    /**
     * 查询用户是否浏览过
     * @param userId
     * @param article_id
     * @return
     */
    @Select("SELECT id FROM ua where user_id = #{userId} and article_id = #{article_id}")
    Integer isUserBrowse(@Param("userId") int userId,@Param("article_id") int article_id);

    /**
     * 添加浏览过的用户
     * @param userId
     * @param article_id
     */
    @Insert("INSERT INTO ua (user_id,article_id) VALUES (#{userId},#{article_id})")
    void userBrowse(@Param("userId") int userId,@Param("article_id") int article_id);

    /**
     * 后台查询所有图文
     * @return
     */
    @Select("SELECT * from article")
    List<Article> articleList();

    /**
     * 图文添加
     * @param article
     */
    @Insert("INSERT INTO article (title,content,author,time,img,type,goods) VALUES (#{article.title},#{article.content},#{article.author},#{article.time},#{article.img},#{article.type},#{article.goods}) ")
    void saveArticle(@Param("article") Article article);

    /**
     * 更新图文
     * @param article
     */
    @Update("UPDATE article SET title = #{article.title},content = #{article.content},author = #{article.author},time = #{article.time},img = #{article.img},goods = #{article.goods} WHERE id = #{article.id}")
    void updateArticle(@Param("article") Article article);
    /**
     * 删除图文
     * @param id
     */
    @Delete("DELETE from article where id = #{id}")
    void delArticle(int id);

    /**
     * 下架和发布
     * @param state
     * @param id
     */
    @Update("UPDATE article SET state = #{state} where id = #{id}")
    void ArticleState(@Param("id")int id,@Param("state")int state);

    /**
     * 编辑查询
     * @param id
     * @return
     */
    @Select("select * from article where id = #{id}")
    Article articleEdit(int id);
}
