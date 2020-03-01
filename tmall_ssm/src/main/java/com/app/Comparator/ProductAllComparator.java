package com.app.Comparator;

import com.app.bean.Product;

import java.util.Comparator;

/**
 * 综合比较器   按照销量*评价高的放在前面
 */
public class ProductAllComparator implements Comparator<Product> {


    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()*p2.getReviewCount()-p1.getSaleCount()*p1.getReviewCount();
    }
}
