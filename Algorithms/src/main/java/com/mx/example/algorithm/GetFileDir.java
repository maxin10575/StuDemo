package com.mx.example.algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:给定一个文件夹，获取文件夹路径和包含关键字的文件夹名和文件名
 *
 * @author mx
 * @version 1.0, 2019/2/21/11:38
 */
public class GetFileDir {
    public static void main(String[] args) {
        File file = new File("D:/test1");
        List<File> list = new ArrayList<>();
        String str = "aaa";
        list = searchFileDir(file, str);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getAbsolutePath());
            System.out.println(list.get(i).getName());
        }
    }

    public static List<File> searchFileDir(File folder, String str) {
        List<File> result = new ArrayList<>();
        if (folder.isFile()) {
            result.add(folder);
            findContainWordFile(folder, result, str);
        }
        File[] subFolders = folder.listFiles(
                new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        }
                        if (file.getName().toLowerCase().contains(str)) {
                            return true;
                        }
                        if (file.isFile()) {
                            findContainWordFile(file, result, str);
                        }
                        return false;
                    }
                }
        );
        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isDirectory()) {
                    result.add(file);
                }
                if (file.isFile()) {
                    result.add(file);
                } else {
                    result.addAll(searchFileDir(file, str));
                }
            }
        }
        return result;
    }

    public static void findContainWordFile(File file, List<File> result, String str) {
        try {
            InputStream in = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String lineTxt;
            while ((lineTxt = br.readLine()) != null) {
                if (lineTxt.contains(str)) {
                    result.add(file);
                    break;
                }
            }
            br.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
