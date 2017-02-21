package test;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import algo.PrefixTreeAlgorithm;
import algo.PrimitivPS;
import algo.PrimitivSC;
import algo.PrimitivSS;
import algo.PrimitvPExec;
import algo.SortedParallelSearch;
import algo.SortedSearch;
import algo.StringSearch;


public class IntegrationTest {

	static List<String> testData;
	static StringSearch[] algorithms;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testData = Util.getStdInstance(false);
		algorithms = new StringSearch[] { 
				new PrimitivSS(), new PrimitivSC(), new PrimitivPS(), 
				new PrimitvPExec(), new SortedSearch(), new PrefixTreeAlgorithm(),
				new SortedParallelSearch()}; 
	}	

	@Test
	public void testPrimitive() {
		
		for (StringSearch algo : algorithms) {
			algo.precompute(testData);
			Assert.assertEquals(algo.getName(), 1, algo.search("AAAA").size());			
			Assert.assertTrue(algo.toString() + ": result=" +  algo.search("AAAA").get(0), algo.search("AAAA").get(0).equals("AAAA"));
			
			Assert.assertEquals(algo.getName(),  26, algo.search("AAA").size());
			
			Assert.assertEquals(algo.getName(), 26*26, algo.search("AA").size());
			
			Assert.assertEquals(algo.getName(), 26*26*26, algo.search("A").size());
			
			Assert.assertEquals(algo.getName(), 26*26*26, algo.search("A").size());
			
			Assert.assertEquals(algo.getName(), testData.size(), algo.search("").size());
			
			Assert.assertEquals(algo.getName(), 0, algo.search("AAAAA").size());
			
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
