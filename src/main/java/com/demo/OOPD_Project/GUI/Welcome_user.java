package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.dao.AccountHolderDAO;
import com.demo.OOPD_Project.exception.OOPDException;
/*
 * This will be the first interface user will see after the login
 */
public class Welcome_user {

	private JFrame userScreen;
	private static AccountHolderBean userbean;
	private static AccountHolderDAO service;
	
	public static void setUser(AccountHolderBean client)
	{
		userbean = client;
	}
	public static void setService(AccountHolderDAO dao)
	{
		service = dao;
	}
	/**
	 * Launch the application.
	 */
	public static void callUserWelcomeScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome_user window = new Welcome_user();
					window.userScreen.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.userScreen.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.userScreen.getWidth()) / 2);
					window.userScreen.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws OOPDException 
	 */
	public Welcome_user() throws OOPDException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws OOPDException 
	 */
	private void initialize() throws OOPDException {
		userScreen = new JFrame();
		userScreen.setBounds(1500, 900, 1200, 900);
		userScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		userScreen.getContentPane().setLayout(null);
		
		/* IF user tries to cancel the screen driectly we will log them out */
		userScreen.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(userScreen, 
		            "Are you sure you want to close this window?\n You will be logged out", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						service.ClientLogout(userbean);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            System.exit(0);
		        }
		    }
		});

		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to Logout","Logout Alert",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					Home_Screen.main(null);
					userScreen.setVisible(false);
					try {
						service.ClientLogout(userbean);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		btnLogout.setBounds(159, 161, 114, 25);
		userScreen.getContentPane().add(btnLogout);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(30, 12, 66, 15);
		userScreen.getContentPane().add(lblWelcome);
		
		JLabel NameLable = new JLabel("");
		NameLable.setBounds(95, 12, 200, 15);
		userScreen.getContentPane().add(NameLable);
		NameLable.setText(userbean.getFname()+" "+userbean.getLname());
	}
}
