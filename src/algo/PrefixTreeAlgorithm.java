package algo;

import java.util.ArrayList;
import java.util.List;

import algo.prefixtree.Node;

public class PrefixTreeAlgorithm implements StringSearch{

	Node root;
	
	@Override
	public void precompute(List<String> data) {
		root = new Node(data, "", 0, 0, 255);
	}

	@Override
	public List<String> lookFor(String pattern) {
		return root.appendToList(new ArrayList<>(), pattern, "");
	}

	@Override
	public String getName() {
		return "PrefixTree";
	}

}
