package com.mx.example.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 *
 * @author mx
 * @version 1.0, 2019/1/11/11:45
 */
public class MajorityElement {
    public static void main(String[] args) {
        int arr[]={1,3,3,2,3};
        int a = getMajorityElement(arr);
        System.out.println(a);
    }

    private static int getMajorityElement(int[] arr) {
        Map<Integer,Integer> map = new HashMap();
        for(int i=0;i<arr.length;i++){
            map.put(i,arr[i]);
        }
        Map<Integer,Integer> respMap = new HashMap<>();
        for(Map.Entry entry:map.entrySet()){
            if(respMap.containsKey(entry.getValue())){
                respMap.put((Integer) entry.getValue(),respMap.get(entry.getValue())+1);
            }else{
                respMap.put((Integer) entry.getValue(),1);
            }
        }
        int maxNum =0;
        int maxId = 0;
        for(Map.Entry entry:respMap.entrySet()){
            if((Integer)entry.getValue()>maxNum){
                maxNum = (Integer) entry.getValue();
                maxId = (Integer) entry.getKey();
            }
        }

        return maxId;
    }


}
