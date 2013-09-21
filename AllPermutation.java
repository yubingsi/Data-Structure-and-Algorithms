import java.util.*;

public class AllPermutation {

        /**
	 * Retuen all permutations of the string s
	 */
	public List<String> getPerms(String s) {
		if(s == null) {
			return null;
		}
		List<String> result = new LinkedList<String>();
		
		if(s.length() == 0) {
			result.add("");
			return result;
		}
		char c = s.charAt(0);
		String reminder = s.substring(1);
		List<String> newStrings = getPerms(reminder);
		for(String r : newStrings) {
			for(int i=0; i<=r.length();i++) {
				String newStr = r.substring(0,i) + c + r.substring(i);
				result.add(newStr);
			}
		}
		
		return result;
	}
}
