package rpcDemo;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2021-10-08 20:27
 * @Modified By:
 * @Version: 1.0
 **/
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String msg) {
        String result = "hello world " + msg;
        System.out.println(result);
        return result;
    }
}
