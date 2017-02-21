package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import algo.prefixtree.Node;

public class PrefixTreeTest {
	static List<String> testData;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testData = Util.getStdInstance(false);
	}

	@Test
	public void testConstructor() {
		ArrayList<String> words = new ArrayList<>();
		Node n = new Node(words, "", 0, 0, 255);
		Assert.assertEquals(0, n.appendAllToList(new ArrayList<>(), "").size());
		
		words.add("Test");
		
		n = new Node(words, "", 0, 0, 255);
		
		Assert.assertEquals(1, n.appendAllToList(new ArrayList<>(), "").size());
		Assert.assertEquals("Test", n.appendAllToList(new ArrayList<>(), "").get(0));
		Assert.assertEquals("", n.getStr());
		n.compressPath();
		Assert.assertEquals("Test", n.getStr());
		words.add("TestWE");
		n = new Node(words, "", 0, 0, 255);
		Assert.assertEquals(2, n.appendAllToList(new ArrayList<>(), "").size());
		n.compressPath();
		
		Assert.assertEquals("Test", n.getStr());
		
	}

}
