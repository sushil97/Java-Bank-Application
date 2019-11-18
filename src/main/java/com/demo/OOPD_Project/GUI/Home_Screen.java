package com.demo.OOPD_Project.GUI;

import java.awt.Component;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

/*
 * This is the home-screen for the application
 */
public class Home_Screen {

	private JFrame home_screen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_Screen window = new Home_Screen();
					window.home_screen.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.home_screen.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.home_screen.getWidth()) / 2);
					window.home_screen.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home_Screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		home_screen = new JFrame();
		home_screen.setBounds(200, 200, 500, 300);
		home_screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home_screen.getContentPane().setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin_Login.callAdminLogin();
				home_screen.setVisible(false);
			}
		});
		btnAdmin.setBounds(46, 125, 169, 31);
		home_screen.getContentPane().add(btnAdmin);
		
		JButton btnAccountHolder = new JButton("Account Holder");
		btnAccountHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login_Screen_Client.callUserLogin();
				home_screen.setVisible(false);
				
			}
		});
		btnAccountHolder.setBounds(265, 125, 169, 31);
		home_screen.getContentPane().add(btnAccountHolder);
		
		JLabel lblLoginAs = new JLabel("LOGIN AS");
		lblLoginAs.setFont(new Font("Dialog", Font.BOLD, 23));
		lblLoginAs.setBounds(187, 39, 157, 25);
		home_screen.getContentPane().add(lblLoginAs);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit","Banking System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton.setBounds(186, 188, 114, 35);
		home_screen.getContentPane().add(btnNewButton);
	}
}
