package test;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @description:
 * @author: maxin
 * @create: 2022-02-25 19:09
 **/
public class GetPic {
    public static void main(String[] args) {
        String picurl ="https://img1.linkiebuy.com/picture/985ae064c38c4b9fba0b60678506705a.jpeg,https://img1.linkiebuy.com/picture/17c17eb24ff34b988a78bdb0093f6ae9.png,https://img1.linkiebuy.com/picture/17c17eb24ff34b988a78bdb0093f6ae9.png,https://img1.linkiebuy.com/picture/17c17eb24ff34b988a78bdb0093f6ae9.png,https://img1.linkiebuy.com/picture/17c17eb24ff34b988a78bdb0093f6ae9.png,https://img1.linkiebuy.com/picture/17c17eb24ff34b988a78bdb0093f6ae9.png,https://img1.linkiebuy.com/picture/6d3fa6b273c74331a872d6a6cba93611.jpeg,https://img1.linkiebuy.com/picture/207ae5300ae34139bd0a444d173fe35d.jpg,https://img1.linkiebuy.com/picture/17c17eb24ff34b988a78bdb0093f6ae9.png,https://img1.linkiebuy.com/picture/207ae5300ae34139bd0a444d173fe35d.jpg,https://img1.linkiebuy.com/picture/207ae5300ae34139bd0a444d173fe35d.jpg";

        String[] picarr = picurl.split(",");
        for (int i = 0; i < picarr.length; i++) {
            String path = "";
            String url = picarr[i];
            String[] urlArr = url.split("1");
            if(url.contains("png")) {
                 path = "/Users/maxin/Documents/pic/大丸松坂屋百货/"+ "https:\\//img1.linkiebuy.com\\/picture\\/" ;
            }
            if(url.contains("jpg")){
                path = "/Users/maxin/Documents/pic/大丸松坂屋百货/" + i + "pic.jpg";
            }
            if(url.contains("jpeg")){
                path = "/Users/maxin/Documents/pic/大丸松坂屋百货/" + i + "pic.jpeg";
            }
            downloadPicture(url,path);
        }
    }
    //链接url下载图片
    private static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(buffer);//返回Base64编码过的字节数组字符串
            System.out.println(encode);
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
