package algo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SortedParallelSearch extends SortedSearch {

	protected ExecutorService service = Executors.newFixedThreadPool(2);
	
	class BinSearchTask implements Callable<Integer> {

		final String pattern;
		public BinSearchTask(String pattern) {
			this.pattern = pattern;
		}

		@Override
		public Integer call() throws Exception {
			return Collections.binarySearch(data, pattern);
		}
		
	}
	
	@Override
	public List<String> search(String pattern) {
		if (pattern == null) throw new IllegalArgumentException("Pattern shouldn't be null");
		if (pattern.length() == 0) {
			return data;
		}		
	
		try {
			List<Future<Integer>> futures = service.invokeAll(Arrays.asList(new BinSearchTask(pattern), new BinSearchTask(nextWord(pattern))));
			int idxL = futures.get(0).get().intValue();
			int idxR = futures.get(1).get().intValue();	
			return this.data.subList(Math.max(0, idxL < 0 ? -idxL-1 : idxL), Math.max(Math.min(idxR < 0 ? -idxR-1 : idxR, data.size()),0));
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new IllegalStateException("BinSearchs encountered an error!");
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new IllegalStateException("BinSearchs encountered an error!");
		}
		
	}

	@Override
	public String getName() {
		return "Sorted Parallel";
	}

}
