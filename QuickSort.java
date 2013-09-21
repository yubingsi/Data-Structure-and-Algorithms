import java.util.Arrays;

public class QuickSort {

	public static void quickSort(int[] array) {
		if(array == null || array.length == 0) {
			return;
		}
		sort(array,0,array.length-1);
		System.out.println(Arrays.toString(array));
	}
	
	public static void sort(int[] array, int low, int high) {
		if(low >= high) {
			return;
		}
		int index = partition(array,low,high);
		sort(array,low,index-1);
		sort(array,index+1,high);
	}
	
	public static int partition(int[] array,int low, int high) {
		int pivot = array[low];
		int left = low + 1;
		int right = high;
		
		while(true) {
			while(left <= high && array[left] <= pivot) {
				left++;
			}
			while(right >=low && array[right] > pivot) {
				right--;
			}
			if(right < left) {
				break;
			}
			swap(array,left,right);
		}
		swap(array,low,right);
		return right;
	}
	
	public static void swap(int[] array, int left, int right){
		int tem = array[left];
		array[left] = array[right];
		array[right] = tem;
 	}
}
