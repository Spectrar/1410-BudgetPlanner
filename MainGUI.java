/**
 * 
 */
package budgetPlanner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
*Assignment: 1410 Final Project
*Class: test.java
*Programmer: Alysha Lagaras
*Date: Apr 14, 2017
*/
public class MainGUI extends JFrame {

	/**
	 * JPanel contentPane
	 */
	private JPanel contentPane;
	/**
	 * instance of Backend
	 */
	private Backend end = new Backend();
	/**
	 * array that goes in the comboBox
	 * String[] boxOptions
	 */
	private String[] boxOptions = {"January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	/**
	 * Array that holds all the categories
	 * ArrayList<Catagory> categories
	 */
	private ArrayList <Category> categories = new ArrayList <Category>();
	/**
	 * JPanel mainPanel
	 */
	private JPanel mainPanel;
	/**
	 * Label for the totals
	 * JLabel lblTMonth
	 */
	private JLabel lblTMonth;
	/**
	 * Label for the totals
	 * JLabel lblTGoal
	 */
	private JLabel lblTGoal;
	/**
	 * Label for the totals
	 * JLabel lblTSpent
	 */
	private JLabel lblTSpent;
	/**
	 * Label for the totals
	 * JLabel lblTRemain
	 */
	private JLabel lblTRemain;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		end.readFile();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(135, 206, 235));
		createTopPanel(topPanel);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(new Color(135, 206, 235));
		createSidePanel(sidePanel);
		
		
		mainPanel = new JPanel();	
		mainPanel.setBackground(new Color(192, 192, 192));
		createMainPanel(mainPanel);

	}

	/**
	 * makes the main Panel where all the categories go
	 * @param mainPanel
	 */
	private void createMainPanel(JPanel mainPanel) {
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JScrollPane scrollPane = new JScrollPane(mainPanel, 
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		ArrayList<ArrayList <MonthData>> tempData = end.getDataArray();
		ArrayList<String> tempName = end.getNameArray();
		//loops through any data that was read in from the file and makes the needed categories
		for(int i=0; i< tempData.size(); i++){
			categories.add(new Category(tempName.get(i), tempData.get(i)));
			mainPanel.add(categories.get(i));
			
		}
		mainPanel.setPreferredSize(new Dimension(940, 250*categories.size()));
		contentPane.updateUI();
		
	}

	/**
	 * makes the side panel that holds the save and delete options and the month total info
	 * @param sidePanel
	 */
	private void createSidePanel(JPanel sidePanel) {
		contentPane.add(sidePanel, BorderLayout.EAST);
		GridBagLayout gbl_sidePanel = new GridBagLayout();
		gbl_sidePanel.columnWidths = new int[] {115, 115, 0};
		gbl_sidePanel.rowHeights = new int[]{49, 49, 49, 49, 0, 0, 0, 0, 0, 0};
		gbl_sidePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_sidePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		sidePanel.setLayout(gbl_sidePanel);
		
		JButton btnSaveFile = new JButton("Save Budget");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// pulls all the data/info out of the categories and sends it to backend to be saved
				ArrayList<ArrayList<MonthData>> tempData = new ArrayList<ArrayList<MonthData>>();
				ArrayList<String> tempName = new ArrayList<String>();
				
				for(Category c: categories){
					tempData.add(c.getData());
					tempName.add(c.getCategoryName());
				}
				end.saveFile(tempData, tempName);
			}
		});
		GridBagConstraints gbc_btnSaveFile = new GridBagConstraints();
		gbc_btnSaveFile.fill = GridBagConstraints.BOTH;
		gbc_btnSaveFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveFile.gridx = 0;
		gbc_btnSaveFile.gridy = 0;
		sidePanel.add(btnSaveFile, gbc_btnSaveFile);
		
		JButton btnDeleteFile = new JButton("Delete Budget");
		btnDeleteFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//calls backend to delete content then clears the categories off the main panel
				end.deleteFile();
				categories = new ArrayList <Category>();
				mainPanel.removeAll();
				contentPane.updateUI();
			}
		});
		GridBagConstraints gbc_btnDeleteFile = new GridBagConstraints();
		gbc_btnDeleteFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteFile.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteFile.gridx = 0;
		gbc_btnDeleteFile.gridy = 1;
		sidePanel.add(btnDeleteFile, gbc_btnDeleteFile);
		
		
		createMonthTotalsComboBtn(sidePanel);
	}

	/**
	 * makes the month total btn and comboBox
	 * @param sidePanel
	 */
	private void createMonthTotalsComboBtn(JPanel sidePanel) {
		
		JButton btnGetMonthTotals = new JButton("Get Month Totals for:");
		GridBagConstraints gbc_btnGetMonthTotals = new GridBagConstraints();
		gbc_btnGetMonthTotals.fill = GridBagConstraints.BOTH;
		gbc_btnGetMonthTotals.insets = new Insets(0, 0, 5, 5);
		gbc_btnGetMonthTotals.gridx = 0;
		gbc_btnGetMonthTotals.gridy = 3;
		sidePanel.add(btnGetMonthTotals, gbc_btnGetMonthTotals);
		
		JComboBox comboBox = new JComboBox(boxOptions);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		sidePanel.add(comboBox, gbc_comboBox);
		JLabel lblTotalsFor = new JLabel("Totals For:");
		lblTotalsFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblTotalsFor = new GridBagConstraints();
		gbc_lblTotalsFor.anchor = GridBagConstraints.EAST;
		gbc_lblTotalsFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalsFor.gridx = 0;
		gbc_lblTotalsFor.gridy = 5;
		sidePanel.add(lblTotalsFor, gbc_lblTotalsFor);
		
		btnGetMonthTotals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//based on current selection of the combo box it pulls the data of that specific month out and adds it together
				String n = null;
				double g=0;
				double s=0;
				double r=0;
				
				for(Category c: categories){
					n=boxOptions[comboBox.getSelectedIndex()];
					g+=c.getData().get(comboBox.getSelectedIndex()).getGoal();
					s+=c.getData().get(comboBox.getSelectedIndex()).getSpent();
					r+=c.getData().get(comboBox.getSelectedIndex()).getRemaining();
				}
				lblTMonth.setText(n);
				lblTGoal.setText(String.format("$%.2f", g));
				lblTSpent.setText(String.format("$%.2f", s));
				lblTRemain.setText(String.format("$%.2f", r));
				
			}
		});
		
		createTotalsResults(sidePanel);
		
	}

	/**
	 * makes all the labels that hold the results for the month totals
	 * @param sidePanel
	 */
	private void createTotalsResults(JPanel sidePanel) {
		
		
		lblTMonth = new JLabel(" ");
		lblTMonth.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblTMonth = new GridBagConstraints();
		gbc_lblTMonth.anchor = GridBagConstraints.WEST;
		gbc_lblTMonth.insets = new Insets(0, 0, 5, 0);
		gbc_lblTMonth.gridx = 1;
		gbc_lblTMonth.gridy = 5;
		sidePanel.add(lblTMonth, gbc_lblTMonth);
		
		JLabel lblGoal = new JLabel("Goal:");
		GridBagConstraints gbc_lblGoal = new GridBagConstraints();
		gbc_lblGoal.anchor = GridBagConstraints.EAST;
		gbc_lblGoal.insets = new Insets(0, 0, 5, 5);
		gbc_lblGoal.gridx = 0;
		gbc_lblGoal.gridy = 6;
		sidePanel.add(lblGoal, gbc_lblGoal);
		
		lblTGoal = new JLabel("$0.00");
		GridBagConstraints gbc_lblTGoal = new GridBagConstraints();
		gbc_lblTGoal.anchor = GridBagConstraints.WEST;
		gbc_lblTGoal.insets = new Insets(0, 0, 5, 0);
		gbc_lblTGoal.gridx = 1;
		gbc_lblTGoal.gridy = 6;
		sidePanel.add(lblTGoal, gbc_lblTGoal);
		
		JLabel lblSpent = new JLabel("Spent:");
		GridBagConstraints gbc_lblSpent = new GridBagConstraints();
		gbc_lblSpent.anchor = GridBagConstraints.EAST;
		gbc_lblSpent.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpent.gridx = 0;
		gbc_lblSpent.gridy = 7;
		sidePanel.add(lblSpent, gbc_lblSpent);
		
		lblTSpent = new JLabel("$0.00");
		GridBagConstraints gbc_lblTSpent = new GridBagConstraints();
		gbc_lblTSpent.anchor = GridBagConstraints.WEST;
		gbc_lblTSpent.insets = new Insets(0, 0, 5, 0);
		gbc_lblTSpent.gridx = 1;
		gbc_lblTSpent.gridy = 7;
		sidePanel.add(lblTSpent, gbc_lblTSpent);
		
		JLabel lblRemaining = new JLabel("Remaining:");
		GridBagConstraints gbc_lblRemaining = new GridBagConstraints();
		gbc_lblRemaining.anchor = GridBagConstraints.EAST;
		gbc_lblRemaining.insets = new Insets(0, 0, 0, 5);
		gbc_lblRemaining.gridx = 0;
		gbc_lblRemaining.gridy = 8;
		sidePanel.add(lblRemaining, gbc_lblRemaining);
		
		lblTRemain = new JLabel("$0.00");
		GridBagConstraints gbc_lblTRemain = new GridBagConstraints();
		gbc_lblTRemain.anchor = GridBagConstraints.WEST;
		gbc_lblTRemain.gridx = 1;
		gbc_lblTRemain.gridy = 8;
		sidePanel.add(lblTRemain, gbc_lblTRemain);
		
		
	}

	/**
	 * makes the top panel that contains the title and add Category button
	 * @param topPanel
	 */
	private void createTopPanel(JPanel topPanel) {
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Budget Planner");
		lblTitle.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 44));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(135, 206, 235));
		topPanel.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnCategoryButton = new JButton("Add a new Category");
		btnCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				end.addCatagory();
				categories.add(new Category(end.getNameArray().get(end.getNameArray().size()-1), end.getDataArray().get(end.getDataArray().size()-1)));
				mainPanel.add(categories.get(categories.size()-1));
				contentPane.updateUI();
				mainPanel.setPreferredSize(new Dimension(940, 250*categories.size()));

			}
		});
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnCategoryButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_3.add(btnCategoryButton);
	}

}
