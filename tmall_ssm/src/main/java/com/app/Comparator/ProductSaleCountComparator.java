package com.app.Comparator;

import com.app.bean.Product;

import java.util.Comparator;

/**
 * 按照产品销量排序   销量高的排前面  比较器
 */
public class ProductSaleCountComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()-p1.getSaleCount();
    }
}
