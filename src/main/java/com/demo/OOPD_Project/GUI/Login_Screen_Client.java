package com.demo.OOPD_Project.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.demo.OOPD_Project.Bean.AccountBean;
import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.dao.*;
import com.demo.OOPD_Project.exception.OOPDException;
/*
 * This class provides the login UI for users/clients of the bank
 */

public class Login_Screen_Client {

	private JFrame user_login;
	private JTextField account_number;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */

	public static void callUserLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Screen_Client window = new Login_Screen_Client();
					window.user_login.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.user_login.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.user_login.getWidth()) / 2);
					window.user_login.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_Screen_Client() throws OOPDException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws OOPDException {
		user_login = new JFrame();
		user_login.getContentPane().setBackground(Color.GRAY);
		user_login.setBounds(100, 100, 450, 300);
		user_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user_login.getContentPane().setLayout(null);
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 24));
		lblLogin.setBounds(170, 12, 108, 31);
		user_login.getContentPane().add(lblLogin);
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAccountNumber.setBounds(25, 69, 148, 40);
		user_login.getContentPane().add(lblAccountNumber);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setBounds(25, 130, 87, 25);
		user_login.getContentPane().add(lblPassword);
		account_number = new JTextField();
		
		/* Disabling all the invalid inputs like special characters into the account number field*/
		account_number.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if ((c>=0 && c<=47) || (c>=58 && c<=64) || (c>=91 && c<=96) || (c>122)) { 
	                e.consume(); 
	            }
	        }
	    });
		account_number.setBounds(204, 73, 169, 31);
		user_login.getContentPane().add(account_number);
		account_number.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		user_login.getRootPane().setDefaultButton(btnLogin);   //Sets default button as login button so that when we hit enter it automatically pops in 
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = account_number.getText();
				/* If account number input is too long */
				if(username.length()>20)
				{
					JOptionPane.showMessageDialog(null, "Account number is too long","Invalid account number",JOptionPane.ERROR_MESSAGE);
					account_number.setText(null);
					passwordField.setText(null);
				}
				String password = String.valueOf(passwordField.getPassword());
				AccountHolderBean client = new AccountHolderBean();
				AccountHolderDAO service = new AccountHolderDAO();
				AccountBean account = new AccountBean();
				AccountDAO service_acc = new AccountDAO();
				client.setAccountNumber(username);
				client.setPassword(password);
				try {
				int status = service.ClientLogin(client);
				if(status == 1)
				{
					JOptionPane.showMessageDialog(null, "User is already logged in","Login Error",JOptionPane.ERROR_MESSAGE);
					account_number.setText(null);
					passwordField.setText(null);
				}
				else if( status == 2 )
				{
					account.setCustomer(client);
					service_acc.getBalanceFromDB(account);
					user_login.setVisible(false);
					Welcome_user.setUser(client);
					Welcome_user.setService(service);
					Welcome_user.setAccount(account);
					Welcome_user.callUserWelcomeScreen();
				}
				else if((password.isBlank() || username.isBlank()))
				{
					SwingUtilities.updateComponentTreeUI(user_login);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
					account_number.setText(null);
					passwordField.setText(null);
					client.setAccountNumber(null);
					client.setPassword(null);
				}
				}
				catch (Exception e) {
					try {
						throw new OOPDException("UI Error"+e);
					} catch (OOPDException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnLogin.setBounds(20, 202, 114, 30);
		user_login.getContentPane().add(btnLogin);
		
		JButton btnClear = new JButton("Reset");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account_number.setText(null);
				passwordField.setText(null);
			}
		});
		btnClear.setBounds(168, 202, 114, 30);
		user_login.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Home_Screen.main(null);
					user_login.setVisible(false);		
			}
		});
		btnExit.setBounds(314, 202, 114, 30);
		user_login.getContentPane().add(btnExit);
		
		passwordField = new JPasswordField();

		/* Disabling all space as input in password*/
		passwordField.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c==127 || c==32) { 
	                e.consume(); 
	            }
	        }
	    });
		passwordField.setBounds(204, 125, 169, 31);
		user_login.getContentPane().add(passwordField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 170, 400, 2);
		user_login.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 50, 400, 2);
		user_login.getContentPane().add(separator_1);
	}
}
