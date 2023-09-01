package com.spring.cloud.common.mybatis.plus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class MybatisPlusNewCodeGenerator {

    @Test
    public void test() {
        String url = "jdbc:mysql://192.168.242.129:3306/seata_point?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "PassW0rd321";
        String author = "rock";

        String projectPath = System.getProperty("user.dir");
        projectPath = projectPath.substring(0, projectPath.lastIndexOf("\\"));
        String modulePath = "/spring-cloud-point-service";
        String outputDir = projectPath + modulePath + "/src/main/java";
        System.out.println("outputDir: " + outputDir);

        String parent = "com.spring.cloud.point.service";
        String controller = "controller";
        String service = "service";
        String serviceImpl = "service.impl";
        String entity = "entity";
        String mapper = "mapper";
        String xml = "mapper.xml";
        String mapperXml = projectPath + modulePath + "/src/main/resources/mapper";
        System.out.println("mapperXml: " + mapperXml);

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .controller(controller)
                            .service(service)
                            .serviceImpl(serviceImpl)
                            .entity(entity)
                            .mapper(mapper)
                            .xml(xml)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperXml)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("point_tbl") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .addTableSuffix("_table", "_tbl") // 设置过滤表后缀
                            .controllerBuilder()
                            .serviceBuilder().convertServiceFileName(entityName -> entityName + ConstVal.SERVICE) // 去掉默认生成的以I为开头的名称
                            .entityBuilder().enableLombok() // 【实体】是否为lombok模型（默认 false）
                            .mapperBuilder();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
