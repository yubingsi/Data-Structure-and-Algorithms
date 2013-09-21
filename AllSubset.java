import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AllSubset {

        /*
         * Retuen all subset of the input set
         * time : Power(2,n)
         */
	public List<Set<Integer>> computeAllSubset(Set<Integer> set) {
		LinkedList<Integer> list = new LinkedList<Integer>(set);
		List<Set<Integer>> subsets = new LinkedList<Set<Integer>>();
		subsets = computeAllSubset(list,list.size()-1,subsets);
		return subsets;
	}
	
	private List<Set<Integer>> computeAllSubset(LinkedList<Integer> list, int end,List<Set<Integer>> subsets) {
		if(end>list.size() || end < 0) {
			Set<Integer> noneElement = new HashSet<Integer>();
			subsets.add(noneElement);
			return subsets;
		}
		List<Set<Integer>> moreSubsets = new LinkedList<Set<Integer>>();
		Integer lastElement = list.get(end);
		
		List<Set<Integer>> subSetForShorterList = computeAllSubset(list,end-1,subsets);
		for(Set<Integer> subset : subSetForShorterList) {
			Set<Integer> newsubSet = new HashSet<Integer>();
			newsubSet.addAll(subset);
			newsubSet.add(lastElement);
			moreSubsets.add(newsubSet);
		}
		subsets.addAll(moreSubsets);
		return subsets;
	}
}
