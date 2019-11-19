package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.dao.AdminDAO;
import com.demo.OOPD_Project.exception.OOPDException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

/*
 * Login UI for employee/administrator
 */
public class Admin_Login {

	private JFrame adminLogin;
	private JPasswordField passwordField;
	private JTextField Username;

	/**
	 * Launch the application.
	 */
	public static void callAdminLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Login window = new Admin_Login();
					window.adminLogin.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.adminLogin.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.adminLogin.getWidth()) / 2);
					window.adminLogin.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin_Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		adminLogin = new JFrame();
		adminLogin.setBounds(100, 100, 450, 300);
		adminLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminLogin.getContentPane().setLayout(null);
		
		JLabel lblWelcomeUser = new JLabel("Welcome Admin");
		lblWelcomeUser.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWelcomeUser.setBounds(120, 12, 200, 40);
		adminLogin.getContentPane().add(lblWelcomeUser);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(41, 76, 92, 25);
		adminLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(30, 135, 92, 15);
		adminLogin.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 130, 200, 30);
		adminLogin.getContentPane().add(passwordField);
		
		Username = new JTextField();
		Username.setBounds(165, 72, 200, 30);
		adminLogin.getContentPane().add(Username);

		/* Disabling all the invalid inputs like special characters into the account number field*/
		Username.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if ((c>=0 && c<=47) || (c>=58 && c<64) || (c>=91 && c<=96) || (c>122)) { 
	                e.consume(); 
	            }
	        }
	    });
		
		Username.setColumns(10);
		
		JButton btnAdminLogin = new JButton("Login");
		adminLogin.getRootPane().setDefaultButton(btnAdminLogin);   //Sets default button as login button so that when we hit enter it automatically pops in
		
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = Username.getText();
				/* If account number input is too long */
				if(username.length()>20)
				{
					JOptionPane.showMessageDialog(null, "Account number is too long","Invalid account number",JOptionPane.ERROR_MESSAGE);
					Username.setText(null);
					passwordField.setText(null);
				}
				String password = String.valueOf(passwordField.getPassword());
				AdminBean admin = new AdminBean();
				AdminDAO service = new AdminDAO();
				admin.setUsername(username);
				admin.setPassword(password);
				try {
				int status = service.adminLogin(admin);
				if(status == 1)							//Admin is already logged in from separate window 
				{
					JOptionPane.showMessageDialog(null, "User is already logged in","Login Error",JOptionPane.ERROR_MESSAGE);
					Username.setText(null);
					passwordField.setText(null);
				}
				else if( status == 2 )
				{
					adminLogin.setVisible(false);
					AdminWelcomeScreen.setAdmin(admin);
					AdminWelcomeScreen.setService(service);
					AdminWelcomeScreen.callAdminWelcomeScreen();
				}
				else if((password.isBlank() || username.isBlank()))
				{
					SwingUtilities.updateComponentTreeUI(adminLogin);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
					Username.setText(null);
					passwordField.setText(null);
					admin.setUsername(null);
					admin.setPassword(null);
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
		btnAdminLogin.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAdminLogin.setBounds(21, 220, 114, 25);
		adminLogin.getContentPane().add(btnAdminLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Username.setText(null);
				passwordField.setText(null);
			}
		});
		btnClear.setFont(new Font("Dialog", Font.BOLD, 15));
		btnClear.setBounds(165, 220, 114, 25);
		adminLogin.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Home_Screen.main(null);
					adminLogin.setVisible(false);
			}
		});
		btnExit.setFont(new Font("Dialog", Font.BOLD, 15));
		btnExit.setBounds(310, 220, 114, 25);
		adminLogin.getContentPane().add(btnExit);
	}

}
