package com.app.controller;

import com.app.bean.User;
import com.app.service.UserService;
import com.app.utils.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> users = userService.list();

        int total = (int) new PageInfo<>(users).getTotal();
        page.setTotal(total);

        model.addAttribute("users",users);
        model.addAttribute("page",page);

        return "admin/listUser";

    }
}
