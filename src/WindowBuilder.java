import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class WindowBuilder extends Playfair {

	JFrame frame;
	public static JTextField keyField;
	public static JTextField textField;
	public static String keyA = null;
	public static String textA = null;
	private JTextPane txtpnKey;
	private JTextPane txtpnEnterTheText;
	private JTextField finalText;
	private JTextPane txtpnFinaleTextIs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilder window = new WindowBuilder();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public WindowBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 512, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		keyField = new JTextField();
		keyField.setBounds(10, 56, 262, 43);
		frame.getContentPane().add(keyField);
		keyField.setColumns(10);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(10, 144, 331, 49);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JTextArea tableArea = new JTextArea();
		tableArea.setBounds(367, 45, 125, 85);
		frame.getContentPane().add(tableArea);

		JButton btnNewButton = new JButton("Encript");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String keyA = null;
				String textA = null;
				try {
					keyA = keyField.getText();
					textA = textField.getText();

					char[] key = insertingKey(keyA);
					char[][] table = makingTheTableFromTheKey(key);
					String showsTheTable = showTable(table);
					char[] text = insertingText(textA);
					String encodedText = makeTheEncriptedText(table, text);
					tableArea.setText(showsTheTable);
					finalText.setText(encodedText);
				} catch (Exception e) {
					if(textA.length()==0){
						JOptionPane.showMessageDialog(null, "Write some text!");
					}else if(! Pattern.matches(".*[a-zA-Z]+.*", textA)){
						JOptionPane.showMessageDialog(null, "You are only allowed to write letters ");
					}
					if(keyA.length()>25){
						JOptionPane.showMessageDialog(null, "The length of the key cant be more than 25!");
					}
					if(! Pattern.matches(".*[a-zA-Z]+.*", keyA)){
						JOptionPane.showMessageDialog(null, "You are only allowed to write letters ");
					}
					
				}

			}
		});

		btnNewButton.setBounds(367, 159, 112, 34);
		frame.getContentPane().add(btnNewButton);

		txtpnKey = new JTextPane();
		txtpnKey.setEditable(false);
		txtpnKey.setText("/if you hit Enter ,the table will become the alphabet/ Write the key:");
		txtpnKey.setBounds(10, 11, 280, 34);
		frame.getContentPane().add(txtpnKey);

		txtpnEnterTheText = new JTextPane();
		txtpnEnterTheText.setBackground(Color.WHITE);
		txtpnEnterTheText.setEditable(false);
		txtpnEnterTheText.setText("Enter the text:");
		txtpnEnterTheText.setBounds(10, 110, 88, 20);
		frame.getContentPane().add(txtpnEnterTheText);

		finalText = new JTextField();
		finalText.setEditable(false);
		finalText.setBounds(10, 235, 428, 43);
		frame.getContentPane().add(finalText);
		finalText.setColumns(10);

		txtpnFinaleTextIs = new JTextPane();
		txtpnFinaleTextIs.setEditable(false);
		txtpnFinaleTextIs.setText("The encrypted text is:");
		txtpnFinaleTextIs.setBounds(10, 204, 170, 20);
		frame.getContentPane().add(txtpnFinaleTextIs);

		JTextPane txtpnTable = new JTextPane();
		txtpnTable.setText("Table:");
		txtpnTable.setEditable(false);
		txtpnTable.setBounds(367, 14, 101, 20);
		frame.getContentPane().add(txtpnTable);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.setBounds(367, 201, 89, 23);
		frame.getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keyField.setText(null);
				textField.setText(null);
				finalText.setText(null);
				tableArea.setText(null);
			}
			});
		

	}
}
