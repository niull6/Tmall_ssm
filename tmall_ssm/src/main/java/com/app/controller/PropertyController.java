package com.app.controller;

import com.app.bean.Category;
import com.app.bean.Property;
import com.app.service.CategoryService;
import com.app.service.PropertyService;
import com.app.utils.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "")
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "admin_property_add",method = RequestMethod.POST)
    public String add(Property property){
        propertyService.add(property);
        return "redirect:/admin_property_list?cid="+property.getCid();
    }

    @RequestMapping(value = "admin_property_delete")
    public String delete(int id){
        Property property = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:/admin_property_list?cid="+property.getCid();
    }

    @RequestMapping(value = "admin_property_edit")
    public String edit(Model model,int id){
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCid());
        property.setCategory(category);
        model.addAttribute("pro",property);
        return "admin/editProperty";
    }

    @RequestMapping(value = "admin_property_update",method = RequestMethod.POST)
    public String update(Property property){
        propertyService.update(property);
        return "redirect:/admin_property_list?cid="+property.getCid();
    }

    @RequestMapping(value = "admin_property_list")
    public String list(int cid, Model model, Page page){
        Category category = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+category.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("c",category);
        model.addAttribute("page",page);

        return "admin/listProperty";

    }

}
