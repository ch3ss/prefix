package algo;

import java.util.List;
import java.util.stream.Collectors;

public class PrimitivSS implements StringSearch{

	List<String> data;

	@Override
	public void precompute(List<String> data) {
		this.data = data;
	}

	@Override
	public List<String> search(String pattern) {
		return data.stream()
				.filter((String t) -> t.startsWith(pattern))
				.collect(Collectors.toList());
	}

	@Override
	public String getName() {
		return "Primitiv Sequential Streams";
	}
}
