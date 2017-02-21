package algo.prefixtree;

import java.util.List;

public class Node {

	private Node[] childs;
	private boolean isWord = false;
	private String str;

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
	
	public void compressPath() {
		int ctr = 0, lastIdx = -1;
		for (int i = 0; i < childs.length; i++) {
			if (childs[i] != null) {
				ctr++;
				lastIdx = i;
			}
		}		
		if (ctr == 1 && !this.isWord) {
			// we can reduce
			this.str += childs[lastIdx].str;
			this.isWord = childs[lastIdx].isWord;
			this.childs = childs[lastIdx].childs;
			
			// maybe possible
			compressPath();
		} else if (ctr > 1) {
			for (Node node : childs) {
				if (node != null)
					node.compressPath();
			}
		}
			
	}
	
	/**
	 * THis is the search function. TODO RENAME.  
	 * @param list
	 * @param prefix
	 * @param word
	 * @return
	 */
	public List<String> appendToList(List<String> list, String prefix,  String word, int offset) {

		// assume str has length 1
		String thisWord = word + str; 
		if (isWord && prefix.equals(str)) {
			list.add(thisWord); 
		}
		String remainingPrefix = prefix.substring(str.length()); 
		if (remainingPrefix.length() > 0) {
			int idx = remainingPrefix.charAt(0) - offset;
			if (childs[idx] != null) {
				childs[idx].appendToList(list, remainingPrefix, thisWord, offset);
			}
		} else {
			for (Node node : childs) {
				if (node != null)
					node.appendAllToList(list, thisWord);
			}
		}

		return list;
	}
	
	public List<String> appendAllToList(List<String> list, String word) {
		
		String thisWord = word + str; 
		if (isWord) {
			list.add(thisWord); 
		}
	
		// TODO that could be parallel
//		list.addAll(Arrays.asList(childs).parallelStream().map((Node n) -> n.appendToList(new ArrayList<>(), thisWord)).collect(Collectors.toList()));
		for (Node node : childs) {
			if (node != null)
				node.appendAllToList(list, thisWord);
		}
		return list;
	}

}
