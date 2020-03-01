package com.app.bean;

/**
 * 属性
 */
public class Property {
    private Integer id;

    private Integer cid;

    private String name;

    /**
     * 在一个属性对应一个分类，一个分类包括多个属性
     */
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}