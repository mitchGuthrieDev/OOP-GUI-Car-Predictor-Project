package project1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3505207394378986439L;

	private final int WIDTH = 800, HEIGHT = 500;

	private JPanel controlPanel;
	private JTextField buyingText;
	private JTextField maintText;
	private JTextField trunkText;
	private JTextField safetyText;
	private JList<Integer> doorList = new JList<Integer>();
	private JList<Integer> personList = new JList<Integer>();
	private JButton btnStartProgram = new JButton("Start Program");
	private Vector<String> comboBoxItems = new Vector<String>();

	// -----------------------------------------------------------------
	// Sets up the panel, including the timer for the animation.
	// -----------------------------------------------------------------
	public MainPanel() {

		Car userCar = new Car();
		Predictor predictor = new Predictor();

		controlPanel = new JPanel();
		controlPanel.setBounds(0, 0, 800, 499);
		controlPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		controlPanel.setBackground(Color.PINK);
		controlPanel.setLayout(null);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.white);
		setLayout(null);
		add(controlPanel);

		JProgressBar progressBar = new JProgressBar();

		// creates wallpaper objects
		Image img = new ImageIcon(this.getClass().getResource("wallpaper4.jpg")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("wallpaper2.jpg")).getImage();
		Image img3 = new ImageIcon(this.getClass().getResource("wallpaper3.jpg")).getImage();
		Image img4 = new ImageIcon(this.getClass().getResource("wallpaper1.jpg")).getImage();

		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(img));

		// buttons/radio buttons

		btnStartProgram.setBounds(339, 323, 123, 23);
		controlPanel.add(btnStartProgram);

		buyingText = new JTextField();
		buyingText.setBounds(104, 87, 86, 20);
		controlPanel.add(buyingText);
		buyingText.setColumns(10);

		maintText = new JTextField();
		maintText.setBounds(104, 118, 86, 20);
		controlPanel.add(maintText);
		maintText.setColumns(10);

		trunkText = new JTextField();
		trunkText.setBounds(104, 149, 86, 20);
		controlPanel.add(trunkText);
		trunkText.setColumns(10);

		safetyText = new JTextField();
		safetyText.setBounds(104, 180, 86, 20);
		controlPanel.add(safetyText);
		safetyText.setColumns(10);

		// Adds labels to identify text boxes and creates an image
		JLabel lblBuying = new JLabel("Buying (int):");
		lblBuying.setBounds(38, 90, 68, 14);
		controlPanel.add(lblBuying);

		JLabel lblMaint = new JLabel("Maint:");
		lblMaint.setBounds(38, 121, 46, 14);
		controlPanel.add(lblMaint);

		JLabel lblDoor = new JLabel("Door:");
		lblDoor.setBounds(235, 90, 32, 14);
		controlPanel.add(lblDoor);

		JLabel lblPersons = new JLabel("Persons:");
		lblPersons.setBounds(363, 90, 68, 14);
		controlPanel.add(lblPersons);

		JLabel lblTrunk = new JLabel("Trunk:");
		lblTrunk.setBounds(38, 152, 46, 14);
		controlPanel.add(lblTrunk);

		JLabel lblSafety = new JLabel("Safety:");
		lblSafety.setBounds(38, 183, 46, 14);
		controlPanel.add(lblSafety);

		JLabel lblCarResults = new JLabel("Car Results:");
		lblCarResults.setBounds(610, 304, 76, 14);
		controlPanel.add(lblCarResults);

		JLabel lblReturn = new JLabel("RETURN");
		lblReturn.setBounds(700, 304, 46, 14);
		controlPanel.add(lblReturn);
		JButton btnSaveCar = new JButton("Predict ");

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(comboBoxItems);
		JComboBox<String> comboBox = new JComboBox<String>(model);

		JRadioButton button1 = new JRadioButton("Wallpaper 1");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.setIcon(new ImageIcon(img));
			}
		});
		JLabel lblReturn_1 = new JLabel("RETURN");

		lblReturn_1.setBounds(700, 327, 46, 14);
		controlPanel.add(lblReturn_1);
		button1.setSelected(true);
		button1.setBounds(637, 87, 109, 23);
		controlPanel.add(button1);

		JRadioButton button2 = new JRadioButton("Wallpaper 2");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.setIcon(new ImageIcon(img2));
			}
		});
		button2.setBounds(637, 117, 109, 23);
		controlPanel.add(button2);

		JRadioButton button3 = new JRadioButton("Wallpaper 3");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.setIcon(new ImageIcon(img3));
			}
		});
		button3.setBounds(637, 148, 109, 23);
		controlPanel.add(button3);

		JRadioButton button4 = new JRadioButton("Wallpaper 4");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image.setIcon(new ImageIcon(img4));
			}
		});
		button4.setBounds(637, 179, 109, 23);
		controlPanel.add(button4);

		// Button group for wallpaper changes
		ButtonGroup myButtonGroup = new ButtonGroup();
		myButtonGroup.add(button1);
		myButtonGroup.add(button2);
		myButtonGroup.add(button3);
		myButtonGroup.add(button4);

		// Results from predictor

		// gets num doors from selection
		JLabel doorLabel = new JLabel("Door Selection");
		doorList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					doorLabel.setText(doorList.getSelectedValue().toString());
					int selectedDoorNum = (int) doorList.getSelectedValue();
					userCar.setDoors(selectedDoorNum);
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(637, 87, 109, 116);
		controlPanel.add(panel);
		doorList.setBounds(277, 89, 76, 91);
		controlPanel.add(doorList);
		doorLabel.setBounds(277, 65, 109, 14);
		controlPanel.add(doorLabel);

		// gets num persons from selection
		JLabel personLabel = new JLabel("Person Selection");
		personList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					personLabel.setText(personList.getSelectedValue().toString());
					int selectedDoorNum = (int) personList.getSelectedValue();
					userCar.setPersons(selectedDoorNum);
				}
			}
		});
		personList.setBounds(441, 89, 76, 91);
		controlPanel.add(personList);
		personLabel.setBounds(441, 65, 144, 14);
		controlPanel.add(personLabel);

		// Adds functionality to buttons and adds them to the frame

		btnSaveCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmpty() == true) {
					final String buying = buyingText.getText();
					userCar.setBuying(buying);

					final String maint = maintText.getText();
					userCar.setMaint(maint);

					final String trunk = trunkText.getText();
					userCar.setTrunk(trunk);

					final String safety = safetyText.getText();
					userCar.setSafety(safety);

					final int numPersons = personList.getSelectedValue();
					userCar.setPersons(numPersons);

					final int numDoors = doorList.getSelectedValue();
					userCar.setPersons(numDoors);

					String result = predictor.getPrediction(userCar);
					lblReturn.setText(result);

					String result2 = " 0";

					switch (predictor.getPrediction(userCar)) {
					case "unacc":
						result2 = Double.toString(Integer.parseInt(buying) * .1);
						break;
					case "acc":
						result2 = Double.toString(Integer.parseInt(buying) * .25);
						break;
					case "good":
						result2 = Double.toString(Integer.parseInt(buying) * .5);
						break;
					case "vgood":
						result2 = Double.toString(Integer.parseInt(buying) * .75);
						break;
					default:
						result2 = "0";
					}

					lblReturn_1.setText(result2);

					String comboCar0 = predictor.predict(userCar).get(0).toString();
					model.addElement(comboCar0);
					String comboCar1 = predictor.predict(userCar).get(0).toString();
					model.addElement(comboCar1);
					String comboCar2 = predictor.predict(userCar).get(0).toString();
					model.addElement(comboCar2);

					progressBar.setVisible(false);

				} else {
					JOptionPane.showMessageDialog(null, "ERROR - Please input/select all required data");
				}

			}
		});
		btnSaveCar.setBounds(104, 211, 89, 23);
		controlPanel.add(btnSaveCar);

		comboBox.setBounds(104, 301, 163, 20);
		controlPanel.add(comboBox);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 800, 21);
		controlPanel.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmRestart = new JMenuItem("Home");
		mnFile.add(mntmRestart);

		JMenuItem mntmEndProgram = new JMenuItem("End Program");
		mntmEndProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmEndProgram);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAboutProgram = new JMenuItem("About Program");
		mnHelp.add(mntmAboutProgram);

		JLabel lblSellPrice = new JLabel("Sell Price:");
		lblSellPrice.setBounds(610, 327, 76, 14);
		controlPanel.add(lblSellPrice);

		progressBar.setBounds(104, 332, 163, 14);
		controlPanel.add(progressBar);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(347, 377, 84, 23);
		controlPanel.add(textPane);

		image.setBounds(0, 0, 800, 499);
		controlPanel.add(image);

		btnStartProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// start door list
				DefaultListModel<Integer> listModel = new DefaultListModel<>();
				listModel.addElement(1);
				listModel.addElement(2);
				listModel.addElement(3);
				listModel.addElement(4);
				listModel.addElement(5);
				doorList.setModel(listModel);

				// start person list
				DefaultListModel<Integer> listModel2 = new DefaultListModel<>();
				listModel2.addElement(1);
				listModel2.addElement(2);
				listModel2.addElement(3);
				listModel2.addElement(4);
				listModel2.addElement(5);
				personList.setModel(listModel2);

				btnStartProgram.setVisible(false);
				progressBar.setIndeterminate(true);
			}
		});

		// Menu bar functionality
		mntmRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStartProgram.setVisible(true);
			}
		});

		mntmAboutProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Copyright Mitch Guthrie - 2018 | Car Predictor GUI");
			}
		});

	}

	public boolean isEmpty() {
		if (buyingText.getText() == "")
			return false;
		else if (maintText.getText() == "")
			return false;
		else if (trunkText.getText() == "")
			return false;
		else if (safetyText.getText() == "")
			return false;
		else if (personList.isSelectionEmpty() == true)
			return false;
		else if (doorList.isSelectionEmpty() == true)
			return false;
		else
			return true;
	}
}