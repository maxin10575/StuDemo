package com.mx.sort;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-11-02 15:59
 * @Modified By:
 * @Version: 1.0
 **/
public class SortTest {
    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("osw.txt")));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(SortTest.class.getClassLoader().getResourceAsStream("sort.txt")));
        String line = null;
        while ((line = reader.readLine()) != null) {
            list.add(Integer.parseInt(line));
        }
        reader.close();
        Integer[] a = new Integer[list.size()];
        list.toArray(a);
        testShell(a);

    }


    public static void testShell(Integer[] a){
        long start = System.currentTimeMillis();
        ShellSort.sort(a);
        long end = System.currentTimeMillis();
        System.out.println( "shellSort:"+(end - start)  +"ms");
    }

    public static void testInsertSort(Integer[] a){
        long start = System.currentTimeMillis();
        InsertSort.sort(a);
        long end = System.currentTimeMillis();
        System.out.println( "InsertSort:"+(end - start)  +"ms");
    }

}
