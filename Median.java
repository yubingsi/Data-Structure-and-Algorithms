/**
 * This class is to find and maintain the median value 
 * as new values are generated.
 * Numbers are randomly genertated
 */
import java.util.Comparator;
import java.util.PriorityQueue;

public class Median {

	Comparator<Integer> minHeapComparator = new Comparator<Integer>() {
		public int compare(Integer num1, Integer num2) {
			return num1 - num2;
		}
	};
	
	Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {
		public int compare(Integer num1, Integer num2) {
			return num2 - num1;
		}
	};

	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(10,minHeapComparator);
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10,maxHeapComparator);
	
        /*
	 * This method keeps two heaps one is Max-Heap and one is Min-Heap.
         * MaxHeap's size will be equal to MinHeap or only bigger than 1.
	 * Numbers are randomly genertated and passed to this method.
         */
	public void addNum(int randomNum) {
		
		if(maxHeap.size() == minHeap.size()) {
			if(minHeap.peek()!= null && randomNum > minHeap.peek()) {
				int tem = minHeap.poll();
				minHeap.offer(randomNum);
				maxHeap.offer(tem);
			} else {
				maxHeap.offer(randomNum);
			}
		} else if(maxHeap.size() > minHeap.size()) {
			if(randomNum <= maxHeap.peek()) {
				int tem = maxHeap.poll();
				maxHeap.offer(randomNum);
				minHeap.offer(tem);
			} else {
				minHeap.offer(randomNum);
			}
		}
	}
	
	public int getMedian() {
		if(maxHeap.isEmpty()) {
			return 0;
		}
		if(maxHeap.size() == minHeap.size()) {
			return (maxHeap.peek() + minHeap.peek()) / 2;
		} else {
			return maxHeap.peek();
		}
	}
}
