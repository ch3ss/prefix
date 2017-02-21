package algo;

import java.util.ArrayList;
import java.util.List;

import algo.prefixtree.Node;

public class PrefixTreeAlgorithm implements StringSearch{

	Node root;
	
	@Override
	public void precompute(List<String> data) {
		root = new Node(data, "", 0, 0, 128);
		root.compressPath();
	}

	@Override
	public List<String> search(String pattern) {
		if (pattern == null) throw new IllegalArgumentException("Pattern shouldn't be null");
		return root.appendToList(new ArrayList<>(), pattern, "", 0);
	}

	@Override
	public String getName() {
		return "PrefixTree";
	}
	
	@Override
	public void append(String word) {
		root.append(word, 0, 0);
	}
}
