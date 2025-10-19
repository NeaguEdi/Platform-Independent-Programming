package testu11;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class representing the graphical user interface of the application.
 */
public class GUI {

	private static JFrame frame; // The Graphical User Interface window
	private JTextField pathOriginal; // The path to the original project
	private JTextField pathDatabase; // The Path to the Excel Database
	private JTextField pathSuspect; // The path to the suspicious project
	String rezultat_global; // Here we store the percentage result of the plagiarism test
	// Previous imports and class definition...

	/**
	 * Launches the application.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                GUI window = new GUI();
	                window.frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}


	/**
	 * Constructor for GUI class.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initializes the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Plagiarism detection application");
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblOriginalProject = new JLabel("Original Project");
		lblOriginalProject.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblOriginalProject.setBounds(24, 10, 215, 100);
		frame.getContentPane().add(lblOriginalProject);

		pathOriginal = new JTextField();
		pathOriginal.setBounds(260, 35, 220, 41);
		frame.getContentPane().add(pathOriginal);
		pathOriginal.setColumns(10);
		pathOriginal.setToolTipText("Path to the original project");
		pathOriginal.setEditable(false);

		JButton btnLoadOriginal = new JButton("LOAD");
		btnLoadOriginal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoadOriginal.setBounds(535, 38, 129, 45);
		frame.getContentPane().add(btnLoadOriginal);
		btnLoadOriginal.setToolTipText("Select project to be analysed");

		btnLoadOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int val = fc.showOpenDialog(null);
				if (val == JFileChooser.APPROVE_OPTION) {
					File folder = fc.getSelectedFile();
					pathOriginal.setText(folder.getAbsolutePath());
				}
			}
		});

		JLabel lblSuspectProject = new JLabel("Suspect Project");
		lblSuspectProject.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSuspectProject.setBounds(24, 75, 215, 100);
		frame.getContentPane().add(lblSuspectProject);

		pathSuspect = new JTextField();
		pathSuspect.setColumns(10);
		pathSuspect.setBounds(260, 105, 220, 41);
		frame.getContentPane().add(pathSuspect);
		pathSuspect.setToolTipText("Path to the suspect project");
		pathSuspect.setEditable(false);

		JButton btnLoadSuspect = new JButton("LOAD");
		btnLoadSuspect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoadSuspect.setBounds(535, 105, 129, 45);
		frame.getContentPane().add(btnLoadSuspect);
		btnLoadSuspect.setToolTipText("Select project to be analysed");

		btnLoadSuspect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int val = fc.showOpenDialog(null);
				if (val == JFileChooser.APPROVE_OPTION) {
					File folder = fc.getSelectedFile();
					pathSuspect.setText(folder.getAbsolutePath());
				}
			}
		});

		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDatabase.setBounds(24, 146, 153, 100);
		frame.getContentPane().add(lblDatabase);

		pathDatabase = new JTextField();
		pathDatabase.setColumns(10);
		pathDatabase.setBounds(260, 175, 220, 41);
		frame.getContentPane().add(pathDatabase);
		pathDatabase.setToolTipText("Path to database");
		pathDatabase.setEditable(false);

		JButton btnLoadDatabase = new JButton("LOAD");
		btnLoadDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoadDatabase.setBounds(535, 176, 129, 45);
		frame.getContentPane().add(btnLoadDatabase);
		btnLoadDatabase.setToolTipText("Select DB to be overwritten");

		btnLoadDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
				fc.setFileFilter(filter);

				int val = fc.showOpenDialog(null);
				if (val == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					pathDatabase.setText(selectedFile.getAbsolutePath());
				}
			}
		});

		JButton btnProjectAnalysis = new JButton("Project Analysis");
		btnProjectAnalysis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProjectAnalysis.setBounds(72, 300, 208, 71);
		frame.getContentPane().add(btnProjectAnalysis);
		btnProjectAnalysis.setToolTipText("Execute analysis");

		btnProjectAnalysis.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (pathOriginal.getText().isEmpty() || pathSuspect.getText().isEmpty()) {
		            JDialog jd1 = new JDialog(frame, "ERROR", true);
		            jd1.setLayout(new BorderLayout());
		            JLabel label = new JLabel("PLEASE SELECT 2 PROJECTS FIRST");
		            label.setHorizontalAlignment(SwingConstants.CENTER);
		            jd1.add(label, BorderLayout.CENTER);
		            label.setPreferredSize(new Dimension(400, 200));
		            jd1.pack();
		            jd1.setLocationRelativeTo(frame);
		            jd1.setVisible(true);
		            return;
		        }

		        List<String> originalCode = null;
		        List<String> suspectCode = null;

		        try {
		            originalCode = FileUtils.obtinereCod(pathOriginal.getText());
		            suspectCode = FileUtils.obtinereCod(pathSuspect.getText());

		            if (originalCode != null && suspectCode != null) {
		                rezultat_global = Double.toString(PlagiarismDetector.procesare(originalCode, suspectCode));
		                /**Replace commas with periods in the result string*/ 
		                rezultat_global = rezultat_global.replace(',', '.');
		                /**Use the result as needed*/ 
		            } else {
		                /**Handle the case where one or both lists are null*/ 
		                System.out.println("One or both word lists are null");
		            }
		        } catch (IOException e1) {
		            /**Handle the IOException appropriately*/ 
		            e1.printStackTrace();
		        }

		        if (rezultat_global == null) {
		            /**Assign a default value if rezultat_global is still null*/ 
		            rezultat_global = "0.00";
		        }

		        JDialog jd = new JDialog(frame, "RESULT", true);
		        jd.setLayout(new BorderLayout());
		        JLabel label = new JLabel("Plagiarism result is: " + rezultat_global);
		        label.setHorizontalAlignment(SwingConstants.CENTER);
		        jd.add(label, BorderLayout.CENTER);
		        label.setPreferredSize(new Dimension(400, 200));
		        jd.pack();
		        jd.setLocationRelativeTo(frame);
		        jd.setVisible(true);
		    }
		});




		JButton btnDatabaseAnalysis = new JButton("Database Analysis");
		btnDatabaseAnalysis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDatabaseAnalysis.setBounds(396, 300, 208, 71);
		frame.getContentPane().add(btnDatabaseAnalysis);
		btnDatabaseAnalysis.setToolTipText("Execute analysis");

		btnDatabaseAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatabaseHandler.updateDatabase(pathDatabase.getText(), pathOriginal.getText(), pathSuspect.getText(),
						rezultat_global);

			}
		});
	}

	protected List<String> obtinereCod(String text) {
		return null;
	}
}
