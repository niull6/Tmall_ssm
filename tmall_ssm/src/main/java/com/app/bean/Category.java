package com.app.bean;

import java.util.List;

/**
 * 分类
 */
public class Category {
    private Integer id;

    private String name;

    /*非数据库字段--主页遍历显示产品的分类*/
    private List<Product> products;

    /*主页遍历显示该分类下所有产品*/
    private List<List<Product>> productsByRow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }
}