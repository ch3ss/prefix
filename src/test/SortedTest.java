package test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import algo.SortedSearch;

public class SortedTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void nextWordTest() {
		
		Assert.assertEquals("AAC", SortedSearch.nextWord("AAB"));
		
		Assert.assertEquals("", SortedSearch.nextWord(""));
		
		Assert.assertEquals("AB", SortedSearch.nextWord("AA" + (char)127));
		
		Assert.assertEquals("", SortedSearch.nextWord(""+(char)127));
	}

}
