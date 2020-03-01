package com.app.service.impl;

import com.app.bean.Product;
import com.app.bean.Property;
import com.app.bean.PropertyValue;
import com.app.bean.PropertyValueExample;
import com.app.dao.PropertyValueMapper;
import com.app.service.PropertyService;
import com.app.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyValueMapper propertyValueMapper;

    @Autowired
    PropertyService propertyService;

    @Override
    public void init(Product p) {
        List<Property> propertys = propertyService.list(p.getCid());
        for(Property pt:propertys){
            PropertyValue propertyValue = get(pt.getId(), p.getId());
            if(null==propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setPid(p.getId());
                propertyValue.setPtid(pt.getId());
                propertyValueMapper.insert(propertyValue);
            }

        }

    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    /*根据产品ID，属性ID来获取特定的属性值*/
    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample propertyValueExample = new PropertyValueExample();
        propertyValueExample.createCriteria().andPtidEqualTo(ptid).andPtidEqualTo(pid);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(propertyValueExample);
        /*为每一个属性值赋予属性*/
        if(propertyValues.isEmpty()){
            return null;
        }
        return propertyValues.get(0);
    }

    /*根据产品ID来获取该产品的所有属性值*/
    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv : result) {
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return result;
    }
}
