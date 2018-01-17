package com.liangyi.entity;

/**
 * 首页通告
 */
public class G {

    private int id;
    /**
     * 通告内容
     */
    private String text;
    /**
     * 通告状态：0为未发布、1 位发布
     */
    private int start;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
