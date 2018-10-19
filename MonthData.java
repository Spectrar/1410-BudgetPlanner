/**
 * 
 */
package budgetPlanner;

/**
*Assignment: 1410 Final Project
*Class: MonthData.java
*Programmer: Stephanie Bryant
*Date: Apr 13, 2017
*/
public class MonthData {
	
	/**
	 * double goal
	 */
	private double goal;
	/**
	 * double spent
	 */
	private double spent;
	/**
	 * double remaining
	 */
	private double remaining;
	/**
	 * @param goal
	 * @param spent
	 * @param remaining
	 */
	public MonthData(double goal, double spent, double remaining) {
		super();
		this.goal = goal;
		this.spent = spent;
		this.remaining = remaining;
	}
	/**
	 * @return the goal
	 */
	public double getGoal() {
		return goal;
	}
	/**
	 * @param goal the goal to set
	 */
	public void setGoal(double goal) {
		this.goal = goal;
	}
	/**
	 * @return the spent
	 */
	public double getSpent() {
		return spent;
	}
	/**
	 * adds the received spent to the total spent value
	 * @param spent the spent to set
	 */
	public void setSpent(double spent) {
		this.spent += spent;
	}
	/**
	 * @return the remaining
	 */
	public double getRemaining() {
		return remaining;
	}
	/**
	 * calculates the remaining value based on the goal and spent
	 * @param remaining the remaining to set
	 */
	public void setRemaining() {
		remaining = goal-spent;
	}
	
	

}
