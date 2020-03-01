package com.app.controller;

import com.app.bean.Category;
import com.app.service.CategoryService;
import com.app.utils.ImageUtil;
import com.app.utils.Page;
import com.app.utils.UploadedImageFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "admin_category_list")
    private String list(Model model, Page page) {
        //重构 使用PageHelper插件来进行分页
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list();
        //通过PageInfo获取数据总条数
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping(value = "admin_category_add", method = RequestMethod.POST)
    private String add(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws Exception {
        categoryService.add(category);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        //对文件进行判空,空则新建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping(value = "admin_category_delete")
    public String delete(int id, HttpSession httpSession) {
        categoryService.delete(id);

        //同时删除保存的图片
        File imgeFolder = new File(httpSession.getServletContext().getRealPath("img/category"));
        File file = new File(imgeFolder, id + ".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    /*编辑*/
    @RequestMapping(value = "admin_category_edit")
    public String edit(Integer cid, Model model) {
        Category category = categoryService.get(cid);
        model.addAttribute("category", category);
        return "admin/editCategory";
    }

    /*更新*/
    @RequestMapping(value = "admin_category_update",method = RequestMethod.POST)
    public String update(Category category, HttpSession HttpSession, UploadedImageFile uploadedImageFile) throws Exception {
        categoryService.update(category);
        MultipartFile image = uploadedImageFile.getImage();
        if (null != image && !image.isEmpty()) {
            File imageFolder = new File(HttpSession.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder,category.getId()+".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img,"jpg",file);
        }
        return "redirect:/admin_category_list";
    }


}
