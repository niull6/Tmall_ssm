package com.app.service;

import com.app.bean.Category;
import com.app.utils.Page;

import java.util.List;

public interface CategoryService {
    public List<Category> list();

    public void add(Category category);

    public void delete(int id);

    public Category get(int id);

    public void update(Category category);
}
