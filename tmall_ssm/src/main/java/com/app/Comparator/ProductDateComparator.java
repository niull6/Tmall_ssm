package com.app.Comparator;

import com.app.bean.Product;

import java.util.Comparator;

/**
 * 按照添加产品时间来排序
 */

public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }
}
