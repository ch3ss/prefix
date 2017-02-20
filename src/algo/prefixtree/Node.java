package algo.prefixtree;

import java.util.List;

public class Node {

	private Node[] childs;
	private boolean isWord = false;
	final private String str;

	public Node(String str, int childSize) {
		childs = new Node[childSize];
		this.str = str;
	}

	public Node(List<String> words, String str, int character, int offset, int childSize) {
		this(str, childSize);
		append(words, character, offset);
	}

	public void append(List<String> words, int character, int offset) {
		for (String string : words) {
			append(string, character, offset);
		}
	}

	public void append(String word, int character, int offset) {
		if (word.length() == character) {
			isWord = true;
		} else {
			int idx = (int) word.charAt(character) - offset;
			if (childs[idx] == null) {
				childs[idx] = new Node("" + word.charAt(character), childs.length);
			}
			childs[idx].append(word, character + 1, offset);
		}
	}
	
	public String getStr() {
		return str;
	}
	
	public boolean isWord() {
		return isWord;
	}
	
	public List<String> appendToList(List<String> list, String prefix,  String word) {

		// assume str has length 1
		
		
		String thisWord = word + str; 
		if (isWord) {
			list.add(thisWord); 
		}
		
		for (Node node : childs) {
			node.appendToList(list, thisWord);
		}
		return list;
	}
	
	public List<String> appendToList(List<String> list, String word) {
		
		String thisWord = word + str; 
		if (isWord) {
			list.add(thisWord); 
		}
		
		// TODO that could be parallel
//		list.addAll(Arrays.asList(childs).parallelStream().map((Node n) -> n.appendToList(new ArrayList<>(), thisWord)).collect(Collectors.toList()));
		for (Node node : childs) {
			node.appendToList(list, thisWord);
		}
		return list;
	}

}
