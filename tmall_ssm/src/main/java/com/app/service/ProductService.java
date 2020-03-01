package com.app.service;

import com.app.bean.Category;
import com.app.bean.Product;
import com.app.bean.Property;

import java.util.List;

public interface ProductService {
    public void add(Product product);

    public void delete(int id);

    public void update(Product product);

    public Product get(int id);

    /*根据分类查该分类下的所有属性*/
    public List list(int cid);

    void setFirstProductImage(Product p);


    /*主页为所有分类填充产品*/
    void fill(List<Category> cs);

    /*为该分类填充产品*/
    void fill(Category c);

    /*为多个分类填充推荐产品*/
    void fillByRow(List<Category> cs);

    /*为产品设置销量和评价数量的方法*/
    void setSaleAndReviewNumber(Product p);

    void setSaleAndReviewNumber(List<Product> ps);

    /*通过关键字进行模糊查询*/
    List<Product> search(String keyword);
}
