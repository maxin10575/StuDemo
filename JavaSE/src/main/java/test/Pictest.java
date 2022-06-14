package test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @description:
 * @author: maxin
 * @create: 2022-02-25 19:13
 **/
public class Pictest {
    public static void main(String[] args) {

    }

    public void getImg(String u, String saveFile) {
        URL url;
        try {
            url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream in = conn.getInputStream();
            byte[] data = readInputStream(in);
            File f = new File(saveFile);
            FileOutputStream out = new FileOutputStream(f);
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] readInputStream(InputStream ins) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = ins.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        ins.close();
        return out.toByteArray();
    }
}