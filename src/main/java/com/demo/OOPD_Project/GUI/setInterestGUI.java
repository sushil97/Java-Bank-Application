package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.dao.AdminDAO;
import com.demo.OOPD_Project.exception.OOPDException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class setInterestGUI {

	private JFrame setInterestFrame;
	private static AdminBean admin;
	private static AdminDAO service;
	private JTextField textField;
	public static void adminBean(AdminBean ad)
	{
		admin = ad;
	}
	public static void adminDao(AdminDAO ser)
	{
		service = ser;
	}
	/**
	 * Launch the application.
	 */
	public static void callSetInterest() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setInterestGUI window = new setInterestGUI();
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.setInterestFrame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.setInterestFrame.getWidth()) / 2);
					window.setInterestFrame.setLocation(x, y);
					window.setInterestFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public setInterestGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setInterestFrame = new JFrame();
		setInterestFrame.setBounds(100, 100, 450, 300);
		setInterestFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setInterestFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Set Interest");
		lblNewLabel.setBounds(165, 12, 150, 15);
		setInterestFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Interest");
		lblNewLabel_1.setBounds(78, 81, 100, 15);
		setInterestFrame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(202, 75, 200, 30);
		setInterestFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		/* Disabling all the invalid inputs like special characters into the First Name field*/
		textField.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c<46|| c==47 || c>57) { 
	                e.consume(); 
	            }
	        }
	    });
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String str = textField.getText();
					int i = 0,count = 0;
					if(str.length()==0)
					{
						JOptionPane.showMessageDialog(null, "Invalid entry","Error",JOptionPane.ERROR_MESSAGE);
						textField.setText(null);
					}else {
					while(i<str.length())
					{
						if(str.charAt(i)=='.')
							count++;
						i++;
					}
					if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Invalid entry","Error",JOptionPane.ERROR_MESSAGE);
						textField.setText(null);
					}	
					else {
					double sum = Double.parseDouble(textField.getText());
					if(sum<0)
					{
						JOptionPane.showMessageDialog(null, "Invalid entry","Error",JOptionPane.ERROR_MESSAGE);
						textField.setText(null);
					}
					else
					{
					admin.setInterest(sum);
					service.setInterestDB(admin);
					AdminWelcomeScreen.setAdmin(admin);
					AdminWelcomeScreen.setService(service);
					AdminWelcomeScreen.callAdminWelcomeScreen();
					setInterestFrame.setVisible(false);
					}}}
				} catch (OOPDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(109, 134, 114, 25);
		setInterestFrame.getContentPane().add(btnSave);
		
		JButton btnBacj = new JButton("Back");
		btnBacj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminWelcomeScreen.setAdmin(admin);
				AdminWelcomeScreen.setService(service);
				AdminWelcomeScreen.callAdminWelcomeScreen();
				setInterestFrame.setVisible(false);
			}
		});
		btnBacj.setBounds(263, 134, 114, 25);
		setInterestFrame.getContentPane().add(btnBacj);
		
		setInterestFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(setInterestFrame, 
		            "Are you sure you want to close this window?\n You will be logged out", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						service.adminLogout(admin);
						System.exit(0);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            System.exit(0);
		        }
		    }
		});
	}

}
