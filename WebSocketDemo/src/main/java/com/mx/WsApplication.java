package com.mx.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 启动程序
 *
 * @author wonlycloud
 */
@EnableAsync
@EnableScheduling
@ServletComponentScan(basePackages = "com.mx.ws")
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WsApplication.class);
        application.run();
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
