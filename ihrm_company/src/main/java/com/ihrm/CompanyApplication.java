package com.ihrm;

import com.ihrm.common.utils.IdWorker;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EntityScan("com.ihrm")
@CrossOrigin
public class CompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class,args);
        DocsConfig config = new DocsConfig();
        config.setProjectPath("E:\\project\\ihrm_parent"); // 项目根目录
        config.setProjectName("ProjectName"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("E:\\project\\ihrm_parent"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
