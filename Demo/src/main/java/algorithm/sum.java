package algorithm;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Godxi
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 */
public class sum {
	
	public static void main(String[] args) {
		int[] nums = {2,11,15,7};
		int target =  9;
		int [] backarr = twoSum3(nums,target);
		for(int a : backarr){
			System.out.println(a);
		}
	}

	//暴力方式
    public static int[] twoSum(int[] nums, int target) {
    	int[] backarr=new int[2];
        for(int i=0; i < nums.length;i++){
            for(int j =i+1; j<nums.length;j++){
                if(i != j){
                    int sum = nums[i]+nums[j];
                    if(target == sum){
                    	backarr[0] = i;
                    	backarr[1] = j;
                    }
                }
            }
        }
        return backarr;
    }
    
    //两遍hash表
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
    //一遍hash表
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
}
