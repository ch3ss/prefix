package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimitvPExec extends AbstractPrimitiv {

	protected ExecutorService executor = Executors.newCachedThreadPool();
	
	@Override
	public List<String> search(String pattern) {
		if (pattern == null) throw new IllegalArgumentException("Pattern shouldn't be null");
		final int cores = Runtime.getRuntime().availableProcessors();
		final List<String> result = new ArrayList<>();
		
		List<Callable<List<String>>> taskList = new ArrayList<>(cores);
		for (int i = 0; i < cores; ++i) {
			final int size = data.size(), begin = size / cores * i, end = size / cores * (i+1), range = end - begin;
						
			taskList.add(new Callable<List<String>>() {
				@Override
				public List<String> call() throws Exception {
					List<String> result = new ArrayList<>(range);
					for (int j = begin; j < end; ++j) {
						String str = data.get(j);
						if (str.startsWith(pattern)) {
							result.add(str);
						}
					}
					return result;
				}
			});
		}
		try {
			List<Future<List<String>>> futures = executor.invokeAll(taskList);
			for (Future<List<String>> future : futures) {
				result.addAll(future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public String getName() {		
		return "Primitiv Parallel Executors";
	}

}
