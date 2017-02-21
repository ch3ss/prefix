package algo;

import java.util.List;

public abstract class AbstractPrimitiv implements StringSearch {

	protected List<String> data;

	@Override
	public void precompute(List<String> data) {
		this.data = data;
	}

	@Override
	public void append(String word) {
		this.data.add(word);
	}
	
}
