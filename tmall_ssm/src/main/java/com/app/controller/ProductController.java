package com.app.controller;

import com.app.bean.Category;
import com.app.bean.Product;
import com.app.service.CategoryService;
import com.app.service.ProductService;
import com.app.utils.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_add")
    public String add(Model model, Product product){
        product.setCreateDate(new Date());
        productService.add(product);
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequestMapping(value = "admin_product_delete")
    public String delete(int id){
        Product product = productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequestMapping(value = "admin_product_update")
    public String update(Product product){
        productService.update(product);
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequestMapping("admin_product_edit")
    public String edit(int id,Model model){
        Product product = productService.get(id);
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
        model.addAttribute("p",product);
        return "admin/editProduct";
    }

    @RequestMapping(value = "admin_product_list")
    public String list(Model model, int cid, Page page){
        Category category = categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> products = productService.list(cid);

        int total = (int) new PageInfo<>(products).getTotal();

        page.setTotal(total);
        page.setParam("&cid="+category.getId());

        model.addAttribute("ps",products);
        model.addAttribute("c",category);
        model.addAttribute("page",page);

        return "admin/listProduct";
    }
}
