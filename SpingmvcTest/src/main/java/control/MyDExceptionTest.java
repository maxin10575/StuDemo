package control;

import exceptionhandler.PasswordException;

/**
 * @program: jsp
 * @description: 自定义异常测试
 * @author: maxin
 * @create: 2020-03-30 10:31
 * @Modified By:
 * @Version: 1.0
 **/
public interface MyDExceptionTest {
    void login(String name, String password)  throws PasswordException;
}
