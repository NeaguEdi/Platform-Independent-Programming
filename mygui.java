
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class gui {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		
		
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
						//    PARTEA DE FUNCTIONALITATE A PROIECTULUI
	
	
	
	
	
	// METODA CE TRECE PRINTR-O FILA SI RETUNEAZA CODUL DIN ACEASTA
	
	
	private static Set<String> obtinereCod(String path) throws IOException
	{
		
		Set<String> codul = new HashSet<>();
		
		Files.walk(Paths.get(path))
					.filter(Files::isRegularFile)
					.filter(file -> file.toString().endsWith(".java"))
					.forEach( file -> {
					
						
						try
						{
							String cod = new String(Files.readAllBytes(file));
							codul.add(cod);
						}
						catch(IOException e)
						{
							JDialog jd = new JDialog(frame, "EROARE",true);
							jd.setLayout(new BorderLayout()); // Set layout to BorderLayout to center the label

							// Create a JLabel with the text content
							JLabel label = new JLabel("EROARE LA DESCHIDERE FISIER !!! ");
							label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
							jd.add(label, BorderLayout.CENTER); // Add the label to the center of the dialog
							label.setPreferredSize(new Dimension(400, 200));

							jd.pack(); // Size the dialog to fit its contents
							jd.setLocationRelativeTo(frame); // Center the dialog relative to the frame
							jd.setVisible(true); // Show the dialog
							
							
							
							
							
							System.out.println("The program encountered an error while trying to read from a file !");    
							e.printStackTrace();
						}
					
					
					});
		
		
		return codul;
	}
	
	
	
	
	
	// METODA CE COMPARA 2 FISIERE (RETURNEAZA PROCENTUL DE PLAGIERE DINTRE CELE 2)
	
	
	private static double procesare(Set<String> s1, Set<String> s2) {
	    int codTotal = s1.size() + s2.size();
	    if (codTotal == 0) return 0; 
	    int codComun = 0;
	    for (String s : s1) {
	        if (s2.contains(s)) {
	            ++codComun;
	        }
	    }
	    return ((double) (codComun / codTotal) * 100); 
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
				//            PARTEA DE UI EFECTIVA
	
	

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Plagiarism detection application");
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("LOAD");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(535, 38, 129, 45);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setToolTipText("Select project to be analysed");
		
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fc = new JFileChooser();
		        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Set selection mode to directories only
		        
		        int val = fc.showOpenDialog(null);
		        
		        if (val == JFileChooser.APPROVE_OPTION) {
		            File folder = fc.getSelectedFile();
		            textField.setText(folder.getAbsolutePath());
		        }
		    }
		});

		
		
		JLabel lblNewLabel = new JLabel("Database");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(24, 146, 153, 100);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(260, 35, 220, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setToolTipText("Path to the original project");
		textField.setEditable(false);
		
		
		JLabel lblNewLabel_1 = new JLabel("Original Project");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(24, 10, 215, 100);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_10 = new JLabel("Suspect Project");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_10.setBounds(24, 75, 215, 100);
		frame.getContentPane().add(lblNewLabel_10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(260, 175, 220, 41);
		frame.getContentPane().add(textField_1);
		textField_1.setToolTipText("Path to database");
		textField_1.setEditable(false);
		
		
		
		//////////////////////////////
		
		JButton btnNewButton5 = new JButton("LOAD");
		btnNewButton5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton5.setBounds(535, 105, 129, 45);
		frame.getContentPane().add(btnNewButton5);
		btnNewButton5.setToolTipText("Select project to be analysed");
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(260, 105, 220, 41);
		frame.getContentPane().add(textField_2);
		textField_2.setToolTipText("Path to the suspect project");
		textField_2.setEditable(false);
		
		
		
		btnNewButton5.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fc = new JFileChooser();
		        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Set selection mode to directories only
		        
		        int val = fc.showOpenDialog(null);
		        
		        if (val == JFileChooser.APPROVE_OPTION) {
		            File folder = fc.getSelectedFile();
		            textField_2.setText(folder.getAbsolutePath());
		        }
		    }
		});

		
		
		
		///////////////////////////////////////
		
		JButton btnNewButton_1 = new JButton("LOAD");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(535, 176, 129, 45);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setToolTipText("Select DB to be overwritten");
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fc = new JFileChooser();
		        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Set selection mode to directories only
		        
		        int val = fc.showOpenDialog(null);
		        
		        if (val == JFileChooser.APPROVE_OPTION) {
		            File folder = fc.getSelectedFile();
		            textField_1.setText(folder.getAbsolutePath());
		        }
		    }
		});

		
		
		
		
		JButton btnProjectAnalysis = new JButton("Project Analysis");
		btnProjectAnalysis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProjectAnalysis.setBounds(72, 300, 208, 71);
		frame.getContentPane().add(btnProjectAnalysis);
		btnProjectAnalysis.setToolTipText("Execute analysis");
		
		
		
		
		btnProjectAnalysis.addActionListener(
				
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e)
					{
						
						
						int ok=0;
						String path1 = textField.getText();
						String path2 = textField_2.getText();
						
						
						if( (textField.getText()).isEmpty() || (textField_2.getText()).isEmpty() )
						{
							
							ok = 1;
							JDialog jd1 = new JDialog(frame, "EROARE",true);
							jd1.setLayout(new BorderLayout()); // Set layout to BorderLayout to center the label

							// Create a JLabel with the text content
							JLabel label = new JLabel("SELECTATI 2 PROIECTE MAI INTAI");
							label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
							jd1.add(label, BorderLayout.CENTER); // Add the label to the center of the dialog
							label.setPreferredSize(new Dimension(400, 200));

							jd1.pack(); // Size the dialog to fit its contents
							jd1.setLocationRelativeTo(frame); // Center the dialog relative to the frame
							jd1.setVisible(true); // Show the dialog
						}
						
						
						Set<String> ss = new HashSet<>();
						Set<String> ss1 = new HashSet<>();
						
						// LUAM CODUL DIN PRIMUL PROIECT
						try {
							ss = obtinereCod(path1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						// LUAM CODUL DIN AL 2-LEA PROIECT
						
						try {
							ss1 = obtinereCod(path2);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						
						
						
						// PARTEA DE AFISARE
						
						if(ok == 0)
						{
						
						JDialog jd1 = new JDialog(frame, "Rezultat Plagiere",true);
						jd1.setLayout(new BorderLayout()); // Set layout to BorderLayout to center the label

						// Create a JLabel with the text content
						JLabel label = new JLabel("Procentajul de plagiere este de: " + procesare(ss, ss1));
						label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
						jd1.add(label, BorderLayout.CENTER); // Add the label to the center of the dialog
						label.setPreferredSize(new Dimension(400, 200));

						jd1.pack(); // Size the dialog to fit its contents
						jd1.setLocationRelativeTo(frame); // Center the dialog relative to the frame
						jd1.setVisible(true); // Show the dialog

						System.out.println("Procentajul de plagiere este de :" + procesare(ss,ss1));
						
						}
						
					}
					
				}
			
			);
		
		
		
		
		
		
		
		
		
		
		
		
		JButton btnDatabaseExport = new JButton("Database Export (.xlsx)");
		btnDatabaseExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDatabaseExport.setToolTipText("Execute export");
		btnDatabaseExport.setBounds(467, 300, 208, 71);
		frame.getContentPane().add(btnDatabaseExport);
	}
	
	
	
}
