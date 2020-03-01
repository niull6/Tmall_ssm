package com.app.Comparator;

import com.app.bean.Product;

import java.util.Comparator;

/**
 * 按照产品价格排序  价格低的排前面
 */
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice()-p2.getPromotePrice());
    }
}
