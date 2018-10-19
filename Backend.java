/**
 * 
 */
package budgetPlanner;

import java.util.ArrayList;

/**
*Assignment: 1410 Final Project
*Class: Backend.java
*Programmer: Brandon Robinson
*Date: Apr 11, 2017
*/
public class Backend {
	
	/**
	 * array holding all the category names
	 * ArrayList<String> nameArray
	 */
	private ArrayList<String> nameArray = new ArrayList<String>();
	/**
	 * array holding all the month data 
	 * ArrayList<ArrayList<MonthData>> dataArray
	 */
	private ArrayList<ArrayList <MonthData>> dataArray = new ArrayList<ArrayList <MonthData>>();
	/**
	 * instance of FileManagement
	 * FileManagement file
	 */
	private FileManagement file = new FileManagement();
	
	/**
	 * passes data from the MainGUI to the FileManagement to be saved
	 * @param d
	 * @param n
	 */
	public void saveFile(ArrayList<ArrayList <MonthData>> d, ArrayList<String> n){
		file.writer(d, n);
	}
	
	/**
	 * empties the arrays of data and calls FileManagement to delete file
	 */
	public void deleteFile(){
		file.delete();
		nameArray = new ArrayList<String>();
		dataArray = new ArrayList<ArrayList <MonthData>>();
	}
	
	/**
	 * adds a new category to the arrays
	 */
	public void addCatagory(){
		
		ArrayList <MonthData> temp = new ArrayList <MonthData>();
		for (int i = 0; i < 12; i++) {
			temp.add(new MonthData(0.00, 0.00, 0.00));
		}
		dataArray.add(temp);
		nameArray.add("category");
	}
	
	/**
	 * @param nameArray the nameArray to set
	 */
	public void setNameArray(ArrayList<String> nameArray) {
		this.nameArray = nameArray;
	}

	/**
	 * @param dataArray the dataArray to set
	 */
	public void setDataArray(ArrayList<ArrayList<MonthData>> dataArray) {
		this.dataArray = dataArray;
	}

	/**
	 * @return the nameArray
	 */
	public ArrayList<String> getNameArray() {
		return nameArray;
	}

	/**
	 * @return the dataArray
	 */
	public ArrayList<ArrayList<MonthData>> getDataArray() {
		return dataArray;
	}
	
	/**
	 * gets the data from FileManagement and puts it into the arrays
	 */
	public void readFile(){
		file.reader();
		dataArray.addAll(file.getDataArray());
		nameArray.addAll(file.getNameArray());
		
	}

}
