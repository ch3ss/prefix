package test;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import algo.PrefixTreeAlgorithm;
import algo.PrimitivPS;
import algo.PrimitivSC;
import algo.PrimitivSS;
import algo.PrimitvPExec;
import algo.SortedList;
import algo.StringSearch;

public class IntegrationTest {

	static List<String> testData;
	static StringSearch[] algorithms;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testData = Util.getStdInstance(false);
		algorithms = new StringSearch[] { new PrimitivSS(), new PrimitivSC(), new PrimitivPS(), new PrimitvPExec(), new SortedList(), new PrefixTreeAlgorithm() }; 
	}	

	@Test
	public void testPrimitive() {
		
		for (StringSearch algo : algorithms) {
			algo.precompute(testData);
			Assert.assertEquals(algo.search("AAAA").size(), 1);			
			Assert.assertTrue(algo.toString() + ": result=" +  algo.search("AAAA").get(0), algo.search("AAAA").get(0).equals("AAAA"));
			
			Assert.assertEquals(algo.search("AAA").size(), 26);
			
			Assert.assertEquals(algo.search("AA").size(), 26*26);
			
			Assert.assertEquals(algo.search("A").size(), 26*26*26);
			
			Assert.assertEquals(algo.search("A").size(), 26*26*26);
			
			Assert.assertEquals(algo.search("").size(), testData.size());
			
			Assert.assertEquals(algo.getName(), algo.search("AAAAA").size(), 0);
			
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void testIndexIllegalArgumentException0() {
		Assert.assertEquals(algorithms[0].search(null).size(), testData.size());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testIndexIllegalArgumentException1() {
		Assert.assertEquals(algorithms[1].search(null).size(), testData.size());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testIndexIllegalArgumentException2() {
		Assert.assertEquals(algorithms[2].search(null).size(), testData.size());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testIndexIllegalArgumentException3() {
		Assert.assertEquals(algorithms[3].search(null).size(), testData.size());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testIndexIllegalArgumentException4() {
		Assert.assertEquals(algorithms[4].search(null).size(), testData.size());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testIndexIllegalArgumentException5() {
		Assert.assertEquals(algorithms[5].search(null).size(), testData.size());
	}	
	
}
