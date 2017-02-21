package test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import algo.SortedList;

public class SortedTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void nextWordTest() {
		
		Assert.assertEquals("AAC", SortedList.nextWord("AAB"));
		
		Assert.assertEquals("", SortedList.nextWord(""));
		
		Assert.assertEquals("AB", SortedList.nextWord("AA" + (char)127));
		
		Assert.assertEquals("", SortedList.nextWord(""+(char)127));
	}

}
