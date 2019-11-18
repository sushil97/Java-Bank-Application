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
/*
 * This will be the first interface user will see after the login
 */
public class Welcome_user {

	private JFrame userScreen;
	private static AccountHolderBean user;
	
	public static void setUser(AccountHolderBean client)
	{
		user = client;
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
	 */
	public Welcome_user() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		userScreen = new JFrame();
		userScreen.setBounds(1500, 900, 1200, 900);
		userScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userScreen.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to Logout","Logout Alert",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
				Home_Screen.main(null);
				userScreen.setVisible(false);
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
		NameLable.setText(user.getFname()+" "+user.getLname());
	}
}
