package algo;

import java.util.List;
import java.util.stream.Collectors;

public class PrimitivSS extends AbstractPrimitiv {
	
	@Override
	public List<String> search(String pattern) {
		if (pattern == null) throw new IllegalArgumentException("Pattern shouldn't be null");
		return data.stream()
				.filter((String t) -> t.startsWith(pattern))
				.collect(Collectors.toList());
	}

	@Override
	public String getName() {
		return "Primitiv Sequential Streams";
	}
}
