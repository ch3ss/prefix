package algo;

import java.util.ArrayList;
import java.util.List;

public class PrimitivSC extends AbstractPrimitiv {
	@Override
	public List<String> search(String pattern) {
		if (pattern == null) throw new IllegalArgumentException("Pattern shouldn't be null");
		List<String> result = new ArrayList<>(data.size());
		for (String string : data) {
			if (string.startsWith(pattern)) {
				result.add(string);
			}
		}
		return result;
	}
	@Override
	public String getName() {
		return "Primitiv Sequential";
	}
}
