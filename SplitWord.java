import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Break up a string into seperate words such that
 * as few characters as possible are not in dictionary  
 */
public class SplitWord {
	public static String sperate (String str, Set<String> dictionary) {
		HashMap<String, Result2> cache = new HashMap<String,Result2>();
		Result2 result = sperate(str,dictionary,0,cache);
		if(result == null) {
		    return null;
		}
		return result.toString();
	}
	
	public static Result2 sperate(String str,Set<String> dictionary, int index, HashMap<String,Result2> cache) {
		if(cache.containsKey(str)) {
			return cache.get(str).clone();
		}
		if(str == null || str.length() == 0) {
			return null;
		}
		if(index == str.length() -1) {
			if(!dictionary.contains(str)) {
				str = str.toUpperCase();
			} 
			int invalid = dictionary.contains(str) ? 0 : str.length();
			return new Result2(str+" ",invalid);		
		}
		
		String sub1 = str.substring(0,index+1);
		String sub2 = str.substring(index + 1);
		Result2 part1 = sperate(sub2,dictionary,0,cache);
		if(dictionary.contains(sub1)) {
			part1.parsed = sub1 + " " + part1.parsed;
		} else {
			part1.invalid += sub1.length();
			part1.parsed = sub1.toUpperCase() + " " + part1.parsed;
		}
		Result2 part2 = sperate(str,dictionary,index+1,cache);	
		Result2 min = part1.compareTo(part2)<0 ? part1 : part2;	
		Result2 old = cache.containsKey(str) ? (min.compareTo(cache.get(str)) < 0 ? min : cache.get(str)) : min;
		cache.put(str, old.clone());
		return min;
	}
	
	public static void main(String[] args) {
		Set<String> dic = new HashSet<String>();
		dic.add("looked");
		dic.add("look");
		dic.add("just");
		dic.add("her");
		dic.add("brother");
		dic.add("hit");
		dic.add("like");
		
		sperate("jesslookedjusttimlikeherbrotherloolook",dic);
		// out put shoud be Jess looked just TIM like her brother LOO look
	}
}

class Result2 {
	String parsed;
	int invalid;
	
	Result2(String parsed,int invalid) {
		this.parsed = parsed;
		this.invalid = invalid;
	}
	
	public int compareTo(Result2 obj) {
		return this.invalid - obj.invalid;
	}
	
	public String toString() {
		return this.parsed + "    " + this.invalid;
	}
	
	public Result2 clone() {
		return new Result2(this.parsed,this.invalid);
	}
}
