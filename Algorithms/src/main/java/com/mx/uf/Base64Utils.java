package com.mx.uf;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Description：
 * @Author ：huangliangjia
 * @Date ：2020/12/4
 */
public class Base64Utils {

    /***
     * decode by Base64
     */
    public static String decodeBase64ToStr(String input) {
        if (StringUtils.isBlank(input)){
            return null;
        }
        return new String(Base64.getDecoder().decode(input), StandardCharsets.UTF_8);
    }

    public static String encodeBase64ToStr(String input) {
        if (StringUtils.isBlank(input)){
            return null;
        }
        return new String(Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 图片Url转Byte数组
     * @param imgUrl 图片Url
     * @return Byte数组
     */
    public static byte[] imageUrlToBase64(String imgUrl) {
        URL url;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;

        try {
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();

            is = httpUrl.getInputStream();
            outStream = new ByteArrayOutputStream();

            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = -1;
            //使用输入流从buffer里把数据读取出来
            while( (len = is.read(buffer)) != -1 ){
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }

            return outStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null) {
                    is.close();
                }
                if(outStream != null) {
                    outStream.close();
                }
                if(httpUrl != null) {
                    httpUrl.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
