package springmvc.salt;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @program: jsp
 * @description: 加密加盐
 * @author: maxin
 * @create: 2020-03-30 10:42
 * @Modified By:
 * @Version: 1.0
 **/
public class md5salt {

    private static final String salt = "加盐内容";

    public static String crypt(String pwd){
        return DigestUtils.md5Hex(pwd+salt);

    }

    public void test(){
    String password = "root11111";
    String encryptedpwd = crypt(password);
    System.out.println("encryptedpwd==="+encryptedpwd);
    }
}
