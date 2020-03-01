package com.app.service;

import com.app.bean.Product;
import com.app.bean.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    public void init(Product product);

    public void update(PropertyValue propertyValue);

    public PropertyValue get(int ptid,int pid);

    public List<PropertyValue> list(int pid);
}
