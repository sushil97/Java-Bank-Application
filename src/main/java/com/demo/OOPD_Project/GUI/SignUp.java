package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.dao.AccountHolderDAO;
import com.demo.OOPD_Project.exception.OOPDException;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class SignUp {

	private JFrame SignUpFrame;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JLabel lblLastName;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JButton btnNewButton;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private AccountHolderBean client = new AccountHolderBean();
	private AccountHolderDAO service = new AccountHolderDAO();
	private JLabel hidelbl1;
	private JLabel hidelbl2;
	private JLabel hidelblAccountNumber;
	private JLabel hidelbl3;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void CallSignUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.SignUpFrame.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.SignUpFrame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.SignUpFrame.getWidth()) / 2);
					window.SignUpFrame.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SignUpFrame = new JFrame();
		SignUpFrame.setBounds(1500, 900, 1200, 900);
		SignUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SignUpFrame.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(500, 12, 66, 15);
		SignUpFrame.getContentPane().add(lblNewLabel);
		
		final JLabel lblName = new JLabel("First Name");
		lblName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblName.setBounds(100, 100, 100, 30);
		SignUpFrame.getContentPane().add(lblName);
	
		txtFirstName = new JTextField();
		txtFirstName.setBounds(280, 100, 300, 30);
		SignUpFrame.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		/* Disabling all the invalid inputs like special characters into the First Name field*/
		txtFirstName.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c<65 || (c>90 && c<97) || c>122) { 
	                e.consume(); 
	            }
	        }
	    });
		
		txtLastName = new JTextField();
		txtLastName.setBounds(280, 170, 300, 30);
		SignUpFrame.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLastName.setBounds(100, 169, 100, 30);
		SignUpFrame.getContentPane().add(lblLastName);
		
		/* Disabling all the invalid inputs like special characters into the Last name field*/
		txtLastName.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c<65 || (c>90 && c<97) || c>122) { 
	                e.consume(); 
	            }
	        }
	    });
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setBounds(100, 238, 100, 30);
		SignUpFrame.getContentPane().add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblConfirmPassword.setBounds(100, 310, 200, 30);
		SignUpFrame.getContentPane().add(lblConfirmPassword);
		
		btnNewButton = new JButton("Sign Up");
		SignUpFrame.getRootPane().setDefaultButton(btnNewButton);   //Sets default button as SingUp button so that when we hit enter it automatically pops in 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fname = txtFirstName.getText();
				String lname = txtLastName.getText();
				String password = String.valueOf(passwordField.getPassword());
				String confirm_password = String.valueOf(passwordField_1.getPassword());
				if(fname.length()>40 || lname.length()>40)
				{
					JOptionPane.showMessageDialog(null, "Name cannot be greater than 40 characters","Invalid name",JOptionPane.ERROR_MESSAGE);
					txtFirstName.setText(null);
					txtLastName.setText(null);
					passwordField.setText(null);
					passwordField_1.setText(null);
				}
				else if(fname.isBlank() || lname.isBlank() || password.isBlank() || confirm_password.isBlank())
					SwingUtilities.updateComponentTreeUI(SignUpFrame);
				else if(!password.equals(confirm_password))
				{
					JOptionPane.showMessageDialog(null, "Password and Confirm password not matching","Invalid passwords",JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
					passwordField_1.setText(null);
				}
				else if(password.length()<8)
				{
					JOptionPane.showMessageDialog(null, "Password should be of 8 character or more","Invalid passwords",JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
					passwordField_1.setText(null);
				}
				else
				{
					client.setFname(fname);
					client.setLname(lname);
					client.setPassword(password);
					try {
						service.addClient(client);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					hidelbl1.setVisible(true);
					hidelbl2.setVisible(true);
					hidelbl3.setVisible(true);
					btnLogin.setVisible(true);
					lblName.setVisible(false);
					lblNewLabel.setVisible(false);
					lblConfirmPassword.setVisible(false);
					lblLastName.setVisible(false);
					lblPassword.setVisible(false);
					btnNewButton.setVisible(false);
					hidelblAccountNumber.setText(client.getAccountNumber());
					hidelblAccountNumber.setVisible(true);
					txtFirstName.setVisible(false);
					txtLastName.setVisible(false);
					passwordField.setVisible(false);
					passwordField_1.setVisible(false);
					System.out.println("registration done");
				}
			}
		});
		btnNewButton.setBounds(261, 389, 114, 25);
		SignUpFrame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(280, 240, 300, 30);
		SignUpFrame.getContentPane().add(passwordField);
		
		/* Disabling all the invalid inputs like special characters into the First Name field*/
		passwordField.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c==32 || c==127 || c==9) { 
	                e.consume(); 
	            }
	        }
	    });
		
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(280, 310, 300, 30);
		SignUpFrame.getContentPane().add(passwordField_1);
		
		/* Disabling all the invalid inputs like special characters into the First Name field*/
		passwordField_1.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c==32 || c==127 || c==9) { 
	                e.consume(); 
	            }
	        }
	    });
		
		hidelbl1 = new JLabel("Thankyou For registring!");
		hidelbl1.setFont(new Font("Dialog", Font.BOLD, 16));
		hidelbl1.setBounds(450, 53, 250, 15);
		SignUpFrame.getContentPane().add(hidelbl1);
		hidelbl1.setVisible(false);
		
		hidelbl2 = new JLabel("Your Account number is :");
		hidelbl2.setFont(new Font("Dialog", Font.BOLD, 15));
		hidelbl2.setBounds(350, 142, 230, 15);
		SignUpFrame.getContentPane().add(hidelbl2);
		hidelbl2.setVisible(false);
		
		hidelblAccountNumber = new JLabel("n");
		hidelblAccountNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		hidelblAccountNumber.setBounds(610, 142, 150, 15);
		SignUpFrame.getContentPane().add(hidelblAccountNumber);
		hidelblAccountNumber.setVisible(false);
		
		hidelbl3 = new JLabel("Please note it down this will be your username as well");
		hidelbl3.setFont(new Font("Dialog", Font.BOLD, 15));
		hidelbl3.setBounds(300, 196, 500, 35);
		SignUpFrame.getContentPane().add(hidelbl3);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login_Screen_Client.callUserLogin();
				SignUpFrame.setVisible(false);
			}
		});
		btnLogin.setBounds(429, 389, 114, 25);
		SignUpFrame.getContentPane().add(btnLogin);
		hidelbl3.setVisible(false);
	}
}
