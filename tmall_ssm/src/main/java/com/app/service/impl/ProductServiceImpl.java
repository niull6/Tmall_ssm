package com.app.service.impl;

import com.app.bean.Category;
import com.app.bean.Product;
import com.app.bean.ProductExample;
import com.app.bean.ProductImage;
import com.app.dao.ProductMapper;
import com.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ReviewService reviewService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        setFirstProductImage(product);
        setCategory(product);
        return product;
    }

    @Override
    public List list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(productExample);
        setCategory(products);
        setFirstProductImage(products);
        return products;
    }


    public void setCategory(List<Product> ps) {
        for (Product product : ps) {
            setCategory(product);
        }
    }

    public void setCategory(Product product) {
        Integer cid = product.getCid();
        Category category = categoryService.get(cid);
        product.setCategory(category);
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }

    public void setFirstProductImage(List<Product> p) {
        for (Product pp : p) {
            setFirstProductImage(pp);
        }
    }


    @Override
    public void fill(List<Category> cs) {
        for (Category category : cs) {
            fill(category);
        }
    }

    @Override
    public void fill(Category c) {
        List<Product> products = list(c.getId());
        c.setProducts(products);
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;  //显示分类下的推荐产品为8个一行，超过在下一行显示
        for (Category category:cs) {
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for(int i=0;i<=products.size();i+=productNumberEachRow){
                int size=i+productNumberEachRow;
                size=size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }


    /*为产品设置销量和评价数量的方法*/
    @Override
    public void setSaleAndReviewNumber(Product p) {
        int saleCount = orderItemService.getSaleCount(p.getSaleCount());
        p.setSaleCount(saleCount);

        int reviewcount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewcount);

    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for(Product product:ps){
            setSaleAndReviewNumber(product);
        }
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andNameLike("%"+keyword+"%");
        productExample.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(productExample);
        setFirstProductImage(products);
        setCategory(products);
        return products;
    }


}
