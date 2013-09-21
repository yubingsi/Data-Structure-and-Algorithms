import java.util.Arrays;

/**
 * Merge Sort O(nlogn)
 */
public class MergeSort {

	public static void mergerSort(int[] array) {
		if(array == null || array.length == 0) {
			return;
		}
		sort(array,0,array.length-1);
		System.out.println(Arrays.toString(array));
	}
	
	public static void sort(int[] array, int low, int high) {
		if(low == high) {
			return;
		}
		int mid = (low + high) / 2;
		sort(array, low, mid);
		sort(array,mid+1,high);
		merge(array,low,mid,high);
		
	}
	
	public static void merge(int[] array, int low, int mid, int high) {
		int length = high - low + 1;
		int[] result = new int[length];
		
		int leftIndex = low;
		int rightIndex = mid+1;
		for(int i=0; i<length;i++) {
			if(leftIndex <= mid && rightIndex<= high){
				if(array[leftIndex] < array[rightIndex]){
					result[i] = array[leftIndex];
					leftIndex++;
				} else {
					result[i] = array[rightIndex];
					rightIndex++;
				}
			} else if(leftIndex <= mid) {
				result[i] = array[leftIndex];
				leftIndex++;
			} else {
				result[i] = array[rightIndex];
				rightIndex++;
			}
		}
		for(int i=low,j=0; i<=high;i++,j++) {
			array[i] = result[j];
		}
	}
}
