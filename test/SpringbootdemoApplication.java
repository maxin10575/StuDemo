package com.mx;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



//@MapperScan("com.mx.buyer")
//@EnableConfigurationProperties({Config1.class, Config2.class})
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)//使用多数据源。禁用默认的数据源组件
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

}
