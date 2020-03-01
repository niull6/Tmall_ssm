package com.app.service;

import com.app.bean.Property;

import java.util.List;

public interface PropertyService {
    public void add(Property property);

    public void delete(int id);

    public void update(Property property);

    public Property get(int id);

    /*根据分类查该分类下的所有属性*/
    public List list(int cid);
}
