package algo;

import java.util.Collections;
import java.util.List;

public class SortedCachedSearch extends SortedSearch {

	int lastLIdx = -1, lastRIdx = -1;
	String lastQuery = null;
	
	@Override
	public void precompute(List<String> data) {
		lastQuery = null;
		super.precompute(data);
	}
	
	@Override
	public List<String> search(String pattern) {
		if (pattern == null) throw new IllegalArgumentException("Pattern shouldn't be null");
		if (pattern.length() == 0) {
			return data;
		}
		int idxL, idxR;
		if (lastQuery != null && pattern.startsWith(lastQuery)) {
			List<String> tmpList = this.data.subList(lastRIdx, lastLIdx);
			idxL = Collections.binarySearch(tmpList, pattern);
			idxR = Collections.binarySearch(data, nextWord(pattern));
		} else {
			idxL = Collections.binarySearch(data, pattern);
			idxR = Collections.binarySearch(data, nextWord(pattern));
		}
		idxL = Math.max(0, idxL < 0 ? -idxL-1 : idxL);
		idxR = Math.max(Math.min(idxR < 0 ? -idxR-1 : idxR, data.size()),0);
		lastLIdx = idxL;
		lastRIdx = idxR;
		lastQuery = pattern;
		return this.data.subList(idxL, idxR);
	}

	@Override
	public String getName() {
		return "Sorted Cached";
	}
}
