package mx;

import org.junit.jupiter.api.Test;

/**
 * @program: StuDemo
 * @description: asm字节码操纵测试
 * @author:
 * @create: 2020-07-03 14:05
 * @Modified By: maxin
 * @Version: 1.0
 **/
public class ATest{
    @Test
    public void testASM() {
        ASMTest.redefineHelloWorldClass();
        new HelloWorld().sayHello();
    }
}
