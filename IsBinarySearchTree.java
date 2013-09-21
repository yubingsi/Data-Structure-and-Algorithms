import java.util.LinkedList;
import java.util.List;

public class IsBinarySearchTree {
    
        /*
	 * Check if the input root is Binary Search Tree
	 */
	public boolean isBinarySearchTree(Node root) {
		return isBinarySearchTree(root, Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	private boolean isBinarySearchTree(Node root,int low, int high) {
		if(root == null) {
			return true;
		}
		return root.getValue() <= high && root.getValue() >= low && 
					isBinarySearchTree(root.getLeftChild(),low,root.getValue()) && 
					isBinarySearchTree(root.getRightChild(),root.getValue(),high);
	}
}
