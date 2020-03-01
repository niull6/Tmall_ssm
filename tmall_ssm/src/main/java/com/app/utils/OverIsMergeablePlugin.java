package com.app.utils;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;

public class OverIsMergeablePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {

        try {
            Field fileid = sqlMap.getClass().getDeclaredField("isMergeable");
            fileid.setAccessible(true);
            fileid.setBoolean(sqlMap,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
