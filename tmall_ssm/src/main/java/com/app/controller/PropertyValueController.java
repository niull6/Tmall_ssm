package com.app.controller;

import com.app.bean.Product;
import com.app.bean.PropertyValue;
import com.app.service.ProductService;
import com.app.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "")
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "admin_propertyValue_edit")
    public String edit(Model model,int pid){
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product.getId());

        model.addAttribute("pvs",propertyValues);
        model.addAttribute("p",product);
        return "admin/editPropertyValue";
    }

    @RequestMapping(value = "admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue){
        propertyValueService.update(propertyValue);
        return "success";
    }
}
