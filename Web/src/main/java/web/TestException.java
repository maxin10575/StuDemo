package web;

public class TestException {
		public static void main(String[] args)   {
			try{
			int arr[] = {1,2,3,4,5};
			test(arr);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		public static void test(int[] arr)  {
			System.out.println(arr[6]);
		}
}
