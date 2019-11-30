package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.dao.AdminDAO;
import com.demo.OOPD_Project.exception.OOPDException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class setTaxGUI {

	private JFrame taxFrame;
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
	public static void runSetTaxGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setTaxGUI window = new setTaxGUI();
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.taxFrame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.taxFrame.getWidth()) / 2);
					window.taxFrame.setLocation(x, y);
					window.taxFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public setTaxGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		taxFrame = new JFrame();
		taxFrame.setBounds(100, 100, 450, 300);
		taxFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		taxFrame.getContentPane().setLayout(null);
		
		JLabel lblSetTax = new JLabel("Set Tax");
		lblSetTax.setBounds(161, 12, 66, 15);
		taxFrame.getContentPane().add(lblSetTax);
		
		JLabel lblEnterTax = new JLabel("Enter tax");
		lblEnterTax.setBounds(60, 72, 66, 15);
		taxFrame.getContentPane().add(lblEnterTax);
		
		textField = new JTextField();
		textField.setBounds(160, 68, 200, 30);
		taxFrame.getContentPane().add(textField);
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
					service.setTaxDB(admin);
					AdminWelcomeScreen.setAdmin(admin);
					AdminWelcomeScreen.setService(service);
					AdminWelcomeScreen.callAdminWelcomeScreen();
					taxFrame.setVisible(false);
					}}}
				} catch (OOPDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(60, 157, 114, 25);
		taxFrame.getContentPane().add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminWelcomeScreen.setAdmin(admin);
				AdminWelcomeScreen.setService(service);
				AdminWelcomeScreen.callAdminWelcomeScreen();
				taxFrame.setVisible(false);
			}
		});

		taxFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(taxFrame, 
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
		btnBack.setBounds(246, 157, 114, 25);
		taxFrame.getContentPane().add(btnBack);
	}

}
