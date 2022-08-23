package com.jonefywang.blog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description MybatisPlusConfig 代码生成器
 * @Author 19285
 * @Date 2022/8/23 22:58
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.jonefywang.blog.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
