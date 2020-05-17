package com.example.demo.Graduation.Configure.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.zaxxer.hikari.util.DriverDataSource;
import org.apache.catalina.manager.StatusManagerServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

//druid后台监控
@Configuration
public class DruidConfig {
    //绑定数据源
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    //后台监控
    @Bean
    public ServletRegistrationBean creanbean() {
        ServletRegistrationBean<StatViewServlet> druidbean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> initmap = new HashMap<>();
        initmap.put("loginUsername", "adminwuwen");
        initmap.put("loginPassword", "WUwen*admin+-");
        initmap.put("allow", "adminwuwen");
        druidbean.setInitParameters(initmap);//初始化参数
        return druidbean;
    }
}
