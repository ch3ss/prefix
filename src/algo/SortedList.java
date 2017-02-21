package algo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedList implements StringSearch{

	private List<String> data;
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

	@Override
	public List<String> search(String pattern) {
		if (pattern.length() == 0) {
			return data;
		}		
		
		int idxL = Collections.binarySearch(data, pattern);
		int size = pattern.length();
		String pattern2 = pattern.substring(0, size - 1) + (char)((int)pattern.charAt(size -1) +1);
		int idxR = Collections.binarySearch(data, pattern2);
		return this.data.subList(Math.max(0, idxL < 0 ? -idxL-1 : idxL), Math.max(Math.min(idxR < 0 ? -idxR-1 : idxR, data.size()),0));
	}

	@Override
	public String getName() {
		return "SortedArray";
	}

}
