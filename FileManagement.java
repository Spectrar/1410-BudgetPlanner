/**
 * 
 */
package budgetPlanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
*Assignment: 1410 Final Project
*Class: MonthData.java
*Programmer: Whitney Cahoon
*Date: Apr 13, 2017
*/
public class FileManagement {

	/**
	 * array holding all the month data 
	 * ArrayList<ArrayList<MonthData>> dataArray
	 */
	private ArrayList<ArrayList<MonthData>> dataArray = new ArrayList<ArrayList<MonthData>>();
	/**
	 * array holding all the category names
	 * ArrayList<String> nameArray
	 */
	private ArrayList<String> nameArray = new ArrayList<String>();

	/**
	 * reads in data from the csv file and saves it to arrays
	 */
	public void reader() {

		String line = null;

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("C:\\BudgetPlanner.csv"));
		
			while ((line = reader.readLine()) != null) {
				// reads in line from file and sends it to getMonthData
				ArrayList<MonthData> md = getMonthData(line);

				if (md != null) {
					dataArray.add(md);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("file not read");
			
		}
		

	}

	/**
	 * writes data from arrays to a csv file
	 * @param dataArray
	 * @param nameArray
	 */
	public void writer(ArrayList<ArrayList<MonthData>> dataArray, ArrayList<String> nameArray) {
		
		
			
				File file = new File("C:\\BudgetPlanner.csv");
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("file not created");
					
					
				}
				
			
		
		int size = nameArray.size();
		
		StringBuilder str = new StringBuilder();
		// loops through all the data in the arrays and puts it in a string through a string builder
		for (int i = 0; i < size; i++) {
			str.append(nameArray.get(i)+",");
			for (int j = 0; j < 12; j++) {
						
				str.append(Double.toString(dataArray.get(i).get(j).getGoal()) + ','
						+ Double.toString(dataArray.get(i).get(j).getSpent()) + ','
						+ Double.toString(dataArray.get(i).get(j).getRemaining()) + ',');

			}
			str.append("\n");
		}
		// converts/formats the string builder to a string that can be properly used by the writer
		String fullText= str.toString().replaceAll("\n", System.lineSeparator());
		
		BufferedWriter writer;
		try {
			//writes the string to the file
			
			writer = new BufferedWriter(new FileWriter(file));		
			writer.write(fullText);
			writer.flush();
			writer.close();
			
			
		
		} catch (IOException e1) {
			System.out.println(e1);
			System.out.println("file did not save");
			
		}
	}

	/**
	 * deletes the contents of the arrays and the csv file
	 */
	public void delete() {
		dataArray.removeAll(dataArray);
		nameArray.removeAll(nameArray);

		writer(dataArray, nameArray);

	}

	/**
	 * @return
	 */
	public ArrayList<ArrayList<MonthData>> getDataArray() {
		return dataArray;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getNameArray() {
		return nameArray;
	}

	/**
	 * takes lines read in by reader and separates the info as needed into a array
	 * @param nextLine
	 * @return
	 */
	private ArrayList<MonthData> getMonthData(String nextLine) {
		//splits the line into a array
		String[] line = nextLine.split(",");
		ArrayList<MonthData> md = new ArrayList<MonthData>();
		// loops through the array with the counter to fill the MonthData Array
		try {
			int counter = 0;
			nameArray.add(line[counter++]);
			for (int i = 0; i < 12; i++) {

				md.add(new MonthData(Double.parseDouble(line[counter++]), Double.parseDouble(line[counter++]),
						Double.parseDouble(line[counter++])));
			}

		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			System.err.println(nextLine + "...Could not be read in");

		}
		return md;
	}

}
