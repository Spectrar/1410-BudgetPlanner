/**
 * 
 */
package budgetPlanner;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
 * Assignment: 1410 Final Project Class: 
 * Catagory.java 
 * Programmer: Brandon Robinson 
 * Date: Apr 15, 2017
 */
public class Category extends JPanel {
	/**
	 * array of all the goal textfields for each month
	 * JTextField[] txtGvalue
	 */
	private JTextField[] txtGvalue = new JTextField[12];
	/**
	 * array of all the spent textfields for each month
	 * JTextField[] txtSvalue
	 */
	private JTextField[] txtSvalue = new JTextField[12];
	/**
	 * array of all the remaining labels for each month
	 * JLabel[] lblRvalue
	 */
	private JLabel[] lblRvalue = new JLabel[12];
	/**
	 * the category name text field
	 * JTextField txtCatagoryName
	 */
	private JTextField txtCategoryName;
	/**
	 * stores the category name
	 * String cataName
	 */
	private String cateName;
	/**
	 * array of the MonthData that corresponds with each month
	 * ArrayList<MonthData> data
	 */
	private ArrayList<MonthData> data;
	/**
	 * array of all the spent labels for each month
	 * JLabel[] lblSpent
	 */
	private JLabel[] lblSpent = new JLabel[12];
	/**
	 * array that holds each months panel
	 * JPanel[] monthPnls
	 */
	private JPanel[] monthPnls;

	/**
	 * Create the panel
	 */
	public Category(String c, ArrayList<MonthData> d) {
		setBackground(new Color(240, 248, 255));
		cateName = c;
		data = d;

		setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 205), new Color(128, 128, 128),
				new Color(30, 144, 255), null));


		txtCategoryName = new JTextField();
		txtCategoryName.setText(cateName);
		add(txtCategoryName);
		txtCategoryName.setColumns(10);

		JPanel allMonths = new JPanel();
		createAllMonthsPnl(allMonths);
		add(allMonths);

		JButton btnUpdate = new JButton("Update");
		createBtnUpdate(btnUpdate);

	}

	/**
	 * saves any info inputed into a textfield and updates the affected labels
	 * @param btnUpdate
	 */
	private void createBtnUpdate(JButton btnUpdate) {
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//takes the info in the text fields and updates the corresponding info
				for (int i = 0; i < 12; i++) {
					data.get(i).setGoal(Double.parseDouble(txtGvalue[i].getText()));
					data.get(i).setSpent(Double.parseDouble(txtSvalue[i].getText()));
					data.get(i).setRemaining();
					lblSpent[i].setText(String.format("Spent= $%.2f + $", data.get(i).getSpent()));
					lblRvalue[i].setText(String.format("%.2f", data.get(i).getRemaining()));

				}

			}
		});
		add(btnUpdate);
	}

	/**
	 * @return ArrayList<MonthData>
	 */
	public ArrayList<MonthData> getData() {

		return data;

	}

	/**
	 * @return String
	 */
	public String getCategoryName() {
		return txtCategoryName.getText();
	}

	/**
	 * makes all the individual month panels
	 * @param allMonths
	 */
	private void createAllMonthsPnl(JPanel allMonths) {
		allMonths.setLayout(new GridLayout(3, 4, 5, 5));
		allMonths.setBounds(100, 100, 974, 200);
		String[] theMonths = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		monthPnls = new JPanel[12];
		//loops 12 times to add all the individual month panels
		for (int i = 0; i < monthPnls.length; i++) {
			monthPnls[i] = new JPanel();
			createMonthPnl(monthPnls[i], theMonths[i], data.get(i), i);
			allMonths.add(monthPnls[i]);
		}
	}

	/**
	 * makes all labels and text fields that go into a single month panel
	 * @param panel
	 */
	private void createMonthPnl(JPanel panel, String theMonth, MonthData info, int counter) {
		
		panel.setBorder(new LineBorder(new Color(64, 64, 64), 2));
		add(panel);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 66, 56, 0 };
		gbl_panel_1.rowHeights = new int[] { 20, 20, 20, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel_1);

		JLabel lblGoal = new JLabel(theMonth + "   Goal= $");
		createLblGoal(panel, lblGoal);

		txtGvalue[counter] = new JTextField();
		createTxtGValue(panel, info, counter);

		JLabel lblEmptyspacer = new JLabel("");
		createLblEmptySpacer(panel, lblEmptyspacer);

		lblSpent[counter] = new JLabel(String.format("Spent= $%.2f + $", info.getSpent()));
		createLblSpent(panel, lblSpent[counter]);

		txtSvalue[counter] = new JTextField();
		createTxtSValue(panel, counter);

		JLabel lblRemaining = new JLabel("Remaining= $");
		createLblRemaining(panel, lblRemaining);

		lblRvalue[counter] = new JLabel();
		createLblRvalue(panel, info, counter);
	}

	/**
	 * makes label for remaining value
	 * @param panel
	 * @param info
	 */
	private void createLblRvalue(JPanel panel, MonthData info, int counter) {
		lblRvalue[counter].setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRvalue[counter].setHorizontalAlignment(SwingConstants.CENTER);
		lblRvalue[counter].setText(String.format("%.2f", info.getRemaining()));
		GridBagConstraints gbc_lblRvalue = new GridBagConstraints();
		gbc_lblRvalue.fill = GridBagConstraints.VERTICAL;
		gbc_lblRvalue.insets = new Insets(0, 0, 0, 5);
		gbc_lblRvalue.gridx = 1;
		gbc_lblRvalue.gridy = 2;
		panel.add(lblRvalue[counter], gbc_lblRvalue);
	}

	/**
	 * makes label for remaining
	 * @param panel
	 * @param lblRemaining
	 */
	private void createLblRemaining(JPanel panel, JLabel lblRemaining) {
		lblRemaining.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRemaining.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRemaining = new GridBagConstraints();
		gbc_lblRemaining.fill = GridBagConstraints.BOTH;
		gbc_lblRemaining.insets = new Insets(0, 0, 0, 5);
		gbc_lblRemaining.gridx = 0;
		gbc_lblRemaining.gridy = 2;
		panel.add(lblRemaining, gbc_lblRemaining);
	}

	/**
	 * makes textfield for spent
	 * @param panel
	 */
	private void createTxtSValue(JPanel panel, int counter) {
		txtSvalue[counter].setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSvalue[counter].setHorizontalAlignment(SwingConstants.CENTER);
		txtSvalue[counter].setText("0.00");
		GridBagConstraints gbc_txtSvalue = new GridBagConstraints();
		gbc_txtSvalue.fill = GridBagConstraints.BOTH;
		gbc_txtSvalue.insets = new Insets(0, 0, 5, 5);
		gbc_txtSvalue.gridx = 1;
		gbc_txtSvalue.gridy = 1;
		panel.add(txtSvalue[counter], gbc_txtSvalue);
		txtSvalue[counter].setColumns(3);
	}

	/**
	 * makes label for spent
	 * @param panel
	 * @param lblSpent
	 */
	private void createLblSpent(JPanel panel, JLabel lblSpent) {
		lblSpent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSpent.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblSpent = new GridBagConstraints();
		gbc_lblSpent.fill = GridBagConstraints.BOTH;
		gbc_lblSpent.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpent.gridx = 0;
		gbc_lblSpent.gridy = 1;
		panel.add(lblSpent, gbc_lblSpent);
	}

	/**
	 * an empty lbl to help space out the layout properly
	 * @param panel
	 * @param lblEmptyspacer
	 */
	private void createLblEmptySpacer(JPanel panel, JLabel lblEmptyspacer) {
		lblEmptyspacer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblEmptyspacer = new GridBagConstraints();
		gbc_lblEmptyspacer.fill = GridBagConstraints.BOTH;
		gbc_lblEmptyspacer.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmptyspacer.gridx = 2;
		gbc_lblEmptyspacer.gridy = 0;
		panel.add(lblEmptyspacer, gbc_lblEmptyspacer);
	}

	/**
	 * makes textField for goal
	 * @param panel
	 * @param info
	 */
	private void createTxtGValue(JPanel panel, MonthData info, int counter) {
		txtGvalue[counter].setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtGvalue[counter].setHorizontalAlignment(SwingConstants.CENTER);
		txtGvalue[counter].setText(String.format("%.2f", info.getGoal()));
		GridBagConstraints gbc_txtGvalue = new GridBagConstraints();
		gbc_txtGvalue.fill = GridBagConstraints.BOTH;
		gbc_txtGvalue.insets = new Insets(0, 0, 5, 5);
		gbc_txtGvalue.gridx = 1;
		gbc_txtGvalue.gridy = 0;
		panel.add(txtGvalue[counter], gbc_txtGvalue);
		txtGvalue[counter].setColumns(3);
	}

	/**
	 * makes label for goal
	 * @param panel
	 * @param lblGoal
	 */
	private void createLblGoal(JPanel panel, JLabel lblGoal) {
		lblGoal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGoal.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblGoal = new GridBagConstraints();
		gbc_lblGoal.fill = GridBagConstraints.BOTH;
		gbc_lblGoal.insets = new Insets(0, 0, 5, 5);
		gbc_lblGoal.gridx = 0;
		gbc_lblGoal.gridy = 0;
		panel.add(lblGoal, gbc_lblGoal);
	}

}
