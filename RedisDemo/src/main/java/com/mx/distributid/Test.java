package com.mx.distributid;

/**
 * @program: StuDemo
 * @description: test
 * @author: maxin
 * @create: 2020-04-10 16:17
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {

    @org.junit.Test
    public void getTest(){
        FormNoGenerateServiceImpl formNoGenerateService = new FormNoGenerateServiceImpl();
        String formNo = formNoGenerateService.generateFormNo(FormNoTypeEnum.YF_ORDER);
        System.out.println("-========"+formNo);
    }
}
