package com.mx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: springbootdemo
 * @description: 各种注解测试
 * @author: maxin
 * @create: 2020-05-26 14:55
 * @Modified By:
 * @Version: 1.0
 **/
@Slf4j
@Component
public class SomeAnnotationTest {


/**
 * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
 * 类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
 *
 * 需要加上 @Component  注解 才能生效
 */

//    @PostConstruct
    public  @PostConstruct void testPostConstruct(){
       log.info("*******testPostConstruct*********");
    }

  /**  被@PreConstruct修饰的方法会在服务器卸载Servlet的时候运行，
    并且只会被服务器调用一次，类似于Servlet的destroy()方法。
    被@PreConstruct修饰的方法会在destroy()方法之后运行，在Servlet被彻底卸载之前。
   */
//    @PreConstruct
    public void testPreConstruct(){
        log.info("*******testPostConstruct*********");
    }
}
