package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayIntersection {

	public static void main(String[] args) {
		Long time1 = System.currentTimeMillis();
		System.out.println("time1===="+time1);
		int arr1[] = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
		int arr2[] = {31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,19,20,21};
		List<Integer> intersectionlist = findArrIntersection1(arr1,arr2);
		for(int a:intersectionlist){
			System.out.println("===="+a);
		}	
		Long time2 = System.currentTimeMillis();
		System.out.println("time2===="+time2);
		System.out.println("==="+(time2-time1));
		
		 int[] arr3={4,5,6,8,9,1,2,3};
	        System.out.println("binarySearch==="+binarySearch(arr3,3));
	}
	
	static List<Integer> findArrIntersection(int[] arr1,int[]arr2){
		List<Integer> list = new ArrayList<>();
		for(int arrone:arr1){
			for(int arrtwo:arr2){
				if(arrone == arrtwo){
					list.add(arrtwo);
				}
			}
		}
		return list;
	}
	
    public static List<Integer> findArrIntersection1(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        boolean flag = true;
        for (int i = 0; i < arr2.length; i++) {
            flag = set.add(arr2[i]);
            if (!flag) {
                list.add(arr2[i]);
            }
        }
        return list;
    }
    
    public static int binarySearch(int[] arr,int target){
        int low=0;
        int high=arr.length-1;
        while(low<=high){

            int mid=(low+high)/2;

            if(target==arr[mid]){
                return mid;
            }

            if(arr[low]<=arr[mid]){
                if(target<arr[mid]&&target>=arr[low]){
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }

            if(arr[mid]<=arr[high]){
                if(target>arr[mid]&&target<=arr[high]){
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }

        }
        return -1;
    }

}
