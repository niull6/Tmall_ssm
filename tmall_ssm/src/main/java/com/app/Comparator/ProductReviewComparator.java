package com.app.Comparator;

import com.app.bean.Product;

import java.util.Comparator;

/**
 * 产品的评价数量排序  评价多的放前面   比较器
 */
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()-p1.getReviewCount();
    }
}
