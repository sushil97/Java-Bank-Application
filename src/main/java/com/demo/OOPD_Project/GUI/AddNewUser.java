package com.demo.OOPD_Project.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class AddNewUser {

	private JFrame addNewUser;

	/**
	 * Launch the application.
	 */
	public static void callNewUser() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewUser window = new AddNewUser();
					window.addNewUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddNewUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addNewUser = new JFrame();
		addNewUser.setBounds(100, 100, 450, 300);
		addNewUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
