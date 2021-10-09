package rpcDemo;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2021-10-08 20:27
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {
    public static void main(String[] args) {
//        HelloWorldService helloWorldService = new HelloWorldServiceImpl();
//        helloWorldService.sayHello("test");
        System.out.println(TenantEnum.nameOf("promoter"));
        HelloWorldService helloWorldService1 = (HelloWorldService) RPCProxyClient.getProxy(HelloWorldService.class);
        helloWorldService1.sayHello("test");
    }
}
