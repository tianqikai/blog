package com.tqk.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2020-12-15 23:22
 *
 **/
@Data
@Component
@ConfigurationProperties("spring.upload")
public class UploadConfig {
    private String baseUrl;
    private List<String> allowTypes;
}
