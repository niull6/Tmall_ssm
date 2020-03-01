package com.app.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisGenerator {
    public static void main(String[] args) throws Exception {
        String today = "2020-02-20";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = simpleDateFormat.parse(today);
        Date date = new Date();

        /*一天的24小时内有效*/
        if (date.getTime() > now.getTime() + 1000 * 60 * 60 * 24) {
            System.err.println("----------未成功运行------------");
            System.err.println("----------未成功运行------------");
            System.err.println("本程序具有破坏性，只能运行一次，如强制运行，将today改为今天，如："
                    + simpleDateFormat.format(new Date()));
            return;
        }

        if(false) return;

        List<String> warnings = new ArrayList<>();
        boolean overwrite=true;
        InputStream is = MybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").openStream();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        System.out.println("生成代码成功，只能执行一次，以后执行会覆盖掉mapper,pojo,xml 等文件上做的修改");


    }
}
