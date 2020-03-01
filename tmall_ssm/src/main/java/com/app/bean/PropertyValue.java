package com.app.bean;

/**
 * 属性值
 */
public class PropertyValue {
    private Integer id;

    /*产品ID*/
    private Integer pid;

    /*属性ID*/
    private Integer ptid;

    private String value;

    /*属性--一个属性对应多个属性值，一个属性值对应多个属性*/
    private Property property;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPtid() {
        return ptid;
    }

    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}