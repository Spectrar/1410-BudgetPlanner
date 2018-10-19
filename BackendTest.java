/**
 * 
 */
package budgetPlanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Assignment: 1410 Final Project 
 * Class: BackendTest.java
 *  Programmer: Brandon Robinson 
 *  Date: Apr 18, 2017
 */
public class BackendTest {

	@Test
	public void testAddCatagory() {
		Backend tester = new Backend();
		tester.addCatagory();
		ArrayList<ArrayList<MonthData>> dataTest = tester.getDataArray();
		ArrayList<String>nameTest = tester.getNameArray();
		
		assertEquals("Result", 1, dataTest.size());
		assertEquals("Result", 1, nameTest.size());
		
	}
	
	@Test
	public void testDeleteFile(){
		ArrayList<ArrayList<MonthData>> test = new ArrayList<ArrayList<MonthData>>();
		test.add(new ArrayList<MonthData>());
		test.get(0).add(new MonthData(0.0,0.0,0.0));
		
		ArrayList<String> test2 = new ArrayList<String>();
		test2.add("test");
		
		Backend tester = new Backend();
		tester.setDataArray(test);
		tester.setNameArray(test2);
		tester.deleteFile();
		
		assertTrue(tester.getDataArray().isEmpty());
	}

}
