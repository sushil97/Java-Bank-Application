package com.demo.OOPD_Project.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.dao.AdminDAO;
import com.demo.OOPD_Project.exception.OOPDException;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminWelcomeScreen {

	private JFrame AdminWelcome;
	private static AdminBean admin;
	private static AdminDAO service;
	
	public static void setAdmin(AdminBean a)
	{
		admin = a;
	}
	public static void setService(AdminDAO s)
	{
		service = s;
	}
	/**
	 * Launch the application.
	 */
	public static void callAdminWelcomeScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWelcomeScreen window = new AdminWelcomeScreen();
					window.AdminWelcome.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.AdminWelcome.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.AdminWelcome.getWidth()) / 2);
					window.AdminWelcome.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminWelcomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AdminWelcome = new JFrame();
		AdminWelcome.setBounds(1500, 900, 1200, 900);
		AdminWelcome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		AdminWelcome.getContentPane().setLayout(null);
		
		/* If admin tries to cancel the screen directly we will log them out */
		AdminWelcome.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(AdminWelcome, 
		            "Are you sure you want to close this window?\n You will be logged out", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						service.adminLogout(admin);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            System.exit(0);
		        }
		    }
		});
		
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome Admin");
		lblWelcomeAdmin.setFont(new Font("Dialog", Font.BOLD, 21));
		lblWelcomeAdmin.setBounds(550, 20, 200, 25);
		AdminWelcome.getContentPane().add(lblWelcomeAdmin);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to Logout","Logout Alert",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					Home_Screen.main(null);
					AdminWelcome.setVisible(false);
					try {
						service.adminLogout(admin);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		btnLogout.setBounds(573, 377, 114, 25);
		AdminWelcome.getContentPane().add(btnLogout);
	}
}
