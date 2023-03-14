package test;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-05-28 17:08
 * @Modified By:
 * @Version: 1.0
 **/
public class iotest {
    public static void main(String[] args) {
        String fileName = "/Users/maxin/Desktop/yiwu.txt";
        File file = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file,true);
            fos.write("哈哈".getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        String cmdStr = "5E010903";
            Long cmdLong = 0L;
            int cmdStrLen = cmdStr.length() / 2;
            for (int i = 0; i < cmdStrLen; i++) {
                String sCmd = cmdStr.substring(2 * i, 2 * i + 2);
                cmdLong += Long.parseLong(sCmd, 16);
            }
           System.out.println(Long.toHexString(cmdLong));
        }
    }

