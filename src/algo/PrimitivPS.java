package algo;

import java.util.List;
import java.util.stream.Collectors;

public class PrimitivPS implements StringSearch {

	List<String> data;

	@Override
	public void precompute(List<String> data) {
		this.data = data;
	}

	@Override
	public List<String> search(String pattern) {
		return data.parallelStream()
				.filter((String t) -> t.startsWith(pattern))
				.collect(Collectors.toList());
	}

	@Override
	public String getName() {
		return "Primitiv Parallel Streams";
	}
}
