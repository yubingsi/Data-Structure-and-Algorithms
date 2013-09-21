import java.util.*;

/**
 * This class implements Tire, which is an ordered tree data structure 
 * that is used to store strings.Each node has an array keeps pointers 
 * to child node and the associated value.
 * @author yubingsi
 *
 */
public class Trie {
	private static int SIZE = 256; 
	TrieNode root;
	
	private static class TrieNode {
		TrieNode[] next = new TrieNode[SIZE];
		Object value;
	}
	
	/**
	 * Get value of the specific string
	 * @param key
	 * @return 
	 */
	public Object getValue(String key) {
		TrieNode node = get(root,key,0);
		if(node == null) {
			return null;
		}
		return node.value;
	}
	
	private TrieNode get(TrieNode node,String key, int depth) {
		if(node == null) {
			return null;
		}
		if(depth == key.length()) {
			return node;
		}
		return get(node.next[key.charAt(depth)],key,depth);
	}
	
	/**
	 * Insert key value into Tire
	 * @param key
	 * @param value
	 */
	public void insert(String key,Object value) {
		root = insert(root,key,value,0);
	}
	
	private TrieNode insert(TrieNode node,String key,Object value, int depth) {
		if(depth == key.length()) {
			node.value = value;
			return node;
		}
		if(node == null) {
			node = new TrieNode();
		}
		int index = key.charAt(depth);
		insert(node.next[index],key,value,depth+1);
		return node;
	}
	
	/**
	 * Takes a string as argument and returns all the keys
	 * in the symbol table having that string as prefix.
	 * @param prefix
	 * @return all strings with the specific prefix 
	 */
	public List<String> search(String prefix) {
		List<String> words = new LinkedList<String>();
		TrieNode startNode = get(root,prefix,0);
		collect(startNode,words,prefix);
		return words;
	}
	
	private void collect(TrieNode node, List<String> list,String prefix) {
		if(node == null) {
			return;
		}
		if(node.value != null) {
			list.add(prefix);
			for(char c = 0; c<SIZE;c++) {
				collect(node.next[c],list,prefix + c);
			}
		}
	}
	
	/**
	 * Takes a string as argument and returns the longest 
	 * key in the Trie that is a prefix of that string.
	 * @param pre - input prefix
	 * @return - longest prefix
	 */
	public String longestPrefixOf(String pre) {
		int length = searchPrefix(root,pre,0,0);
		return pre.substring(length);
	}
	
	private int searchPrefix(TrieNode node,String pre,int depth, int length){
		if(node == null) {
			return length;
		}
		if(depth == pre.length()) {
			return length;
		}
		if(root.value != null) {
			length = depth;
		}
		return searchPrefix(node.next[pre.charAt(depth)],pre,depth+1,length);
	}
	
	/**
	 * Delete specific key
	 * @param key
	 */
	public void delete(String key) {
		delete(root,key,0);
	}
	
	private TrieNode delete(TrieNode node,String key,int depth){
		if(node == null) {
			return null;
		}
		if(depth == key.length()){
			node.value = null;
		} else {
			node.next[key.charAt(depth)] = delete(node.next[key.charAt(depth)],key,depth+1);
		}
		for(char c = 0; c < SIZE; c++) {
			if(node.next[c] != null) {
				return node;
			}
		}
		return null;	
	}

}
