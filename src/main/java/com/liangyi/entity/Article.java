package com.liangyi.entity;

/**
 * 图文类
 */
public class Article {

    private int id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * '作者'
     */
    private String author;
    /**
     * 添加时间
     */
    private String time;
    /**
     * 图片地址
     */
    private String img;
    /**
     * 类型
     */
    private int type;
    /**
     * '浏览数量'
     */
    private int browse;
    /**
     * 打赏人数
     */
    private String bounty;
    /**
     * 内嵌商品Id
     */
    private int goods;

    private int state;

    private String zw;

    private String zws;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public String getBounty() {
        return bounty;
    }

    public void setBounty(String bounty) {
        this.bounty = bounty;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getZws() {
        return zws;
    }

    public void setZws(String zws) {
        this.zws = zws;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                ", browse=" + browse +
                ", bounty='" + bounty + '\'' +
                ", goods=" + goods +
                ", state=" + state +
                '}';
    }
}
