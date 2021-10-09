package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author mx
 * @create 2 2021/04/08 14:14
 * @description java类作用描述
 */
public class IOUtils {

    private static Logger logger = LoggerFactory.getLogger(IOUtils.class);

    public static String input(){
        HttpServletRequest request = ServletUtils.getRequest();
        InputStream in = null;
        StringBuilder result = new StringBuilder();
        try {
            in = request.getInputStream();
            //已HTTP请求输入流建立一个BufferedReader对象
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            //读取HTTP请求内容
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            logger.error("IOUtils接收数据发生错误:{}",e.getMessage());
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    public static void output(String message){
        HttpServletResponse response = ServletUtils.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(message);
            out.flush();
        } catch (IOException e) {
            logger.error("IOUtils发送数据发生错误:{}",e.getMessage());
        } finally {
            out.close();
        }
    }

}
