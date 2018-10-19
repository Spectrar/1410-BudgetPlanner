/**
 * 
 */
package budgetPlanner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Assignment: 1410 Final Project Class: MonthDataTest.java Programmer: Brandon
 * Robinson Date: Apr 19, 2017
 */
public class MonthDataTest {

	/**
	 * Test method for
	 * {@link budgetPlanner.MonthData#MonthData(double, double, double)}.
	 */

	@Test
	public void testMonthData() {
		MonthData tester = new MonthData(100.00, 50.00, 50.00);

		assertEquals(100.0, tester.getGoal(), 0.0);
		assertEquals(50.0, tester.getSpent(), 0.0);
		assertEquals(50.0, tester.getRemaining(), 0.0);
	}

	/**
	 * Test method for {@link budgetPlanner.MonthData#setSpent(double)}.
	 */
	@Test
	public void testSetSpent() {
		MonthData tester = new MonthData(100.00, 50.00, 50.00);

		tester.setSpent(25.0);

		assertEquals(75.0, tester.getSpent(), 0.0);
	}

	/**
	 * Test method for {@link budgetPlanner.MonthData#setRemaining()}.
	 */
	@Test
	public void testSetRemaining() {
		MonthData tester = new MonthData(100.00, 10.00, 90.00);

		tester.getRemaining();
		
		assertEquals(90.0, tester.getRemaining(), 0.0);
	}

}
