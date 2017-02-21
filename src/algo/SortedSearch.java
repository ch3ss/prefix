package algo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedSearch implements StringSearch{

	protected volatile List<String> data;
	static final Comparator<String> STRCMP = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};

	
	@Override
	public void precompute(List<String> data) {		
		String[] dataA = new String[data.size()];
		dataA = data.toArray(dataA);
		Arrays.parallelSort(dataA);
		this.data = Arrays.asList(dataA);		
	}

	static public String nextWord(String pattern) {
//		String pattern2 = pattern.substring(0, size - 1) + (char)((int)pattern.charAt(size -1) +1);
		int l = pattern.length();
		
		for (int i = l-1; i >= 0; i--) {
			// 127 is last used position in ascii
			byte val = pattern.getBytes()[i];
			if (val > 126) {
				l--;
			} else {
				break;
			}
		}
		
		if (l == 0) {
			return "";
		} 
		
		int tmpL = Math.max(l - 1,0);
		return pattern.substring(0, tmpL) + (char)((int)pattern.charAt(tmpL) +1);
	}
	
	@Override
	public List<String> search(String pattern) {
		if (pattern == null) throw new IllegalArgumentException("Pattern shouldn't be null");
		if (pattern.length() == 0) {
			return data;
		}		
		
		int idxL = Collections.binarySearch(data, pattern);
		int idxR = Collections.binarySearch(data, nextWord(pattern));
		return this.data.subList(Math.max(0, idxL < 0 ? -idxL-1 : idxL), Math.max(Math.min(idxR < 0 ? -idxR-1 : idxR, data.size()),0));
	}

	@Override
	public String getName() {
		return "Sorted";
	}
	
	@Override
	public void append(String word) {
		int idx = Collections.binarySearch(data, word);
		if (idx < 0) {
			data.add(-idx, word);
		}
	}

}
