package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.demo.OOPD_Project.Bean.AccountBean;
import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.dao.AccountDAO;
import com.demo.OOPD_Project.dao.AccountHolderDAO;
import com.demo.OOPD_Project.dao.DepositDAO;
import com.demo.OOPD_Project.exception.OOPDException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Deposit {

	private JFrame depositFrame;
	private static AccountHolderBean user;
	private static AccountHolderDAO service; 
	private static AccountDAO account_service;
	private static AccountBean account;
	private DepositDAO dep = new DepositDAO();
	private JTextField textField;
	
	public static void setUser(AccountHolderBean usr)
	{
		user = usr;
	}
	
	public static void setService(AccountHolderDAO s)
	{
		service = s;
	}
	
	public static void setAccountbean(AccountBean b)
	{
		account = b;
	}
	public static void setAccountService(AccountDAO d)
	{
		account_service = d;
	}
	
	/**
	 * Launch the application.
	 */
	public static void CallDeposit() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposit window = new Deposit();
					window.depositFrame.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.depositFrame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.depositFrame.getWidth()) / 2);
					window.depositFrame.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Deposit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		depositFrame = new JFrame();
		depositFrame.setBounds(1500, 900, 1200, 900);
		depositFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		depositFrame.getContentPane().setLayout(null);
		
		/* If user tries to cancel the screen directly we will log them out */
		depositFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(depositFrame, 
		            "Are you sure you want to close this window?\n You will be logged out", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						service.ClientLogout(user);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            System.exit(0);
		        }
		    }
		});
		
		JLabel lblCurrentBalance = new JLabel("Current Balance :");
		lblCurrentBalance.setBounds(20, 20, 130, 15);
		depositFrame.getContentPane().add(lblCurrentBalance);
		
		JLabel lblNewLabel = new JLabel(Double.toString(account.getBalance()));
		lblNewLabel.setBounds(150, 20, 500, 15);
		depositFrame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(500, 169, 200, 30);
		depositFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterAmount = new JLabel("Enter Amount");
		lblEnterAmount.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEnterAmount.setBounds(320, 169, 150, 30);
		depositFrame.getContentPane().add(lblEnterAmount);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int status = dep.doDepsit(account, Double.parseDouble(textField.getText()));
					if(status == 0)
					{
						JOptionPane.showMessageDialog(null, "Transaction failed","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(status == 1)
					{
						JOptionPane.showMessageDialog(null, "You cannot have more than 50 lakhs in your account","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(status == 2)
					{
						JOptionPane.showMessageDialog(null, "Amount cannot be negative","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(status == 3)
					{
						depositFrame.setVisible(false);
						Welcome_user.callUserWelcomeScreen();
						Welcome_user.setAccount(account);
						Welcome_user.setAccountService(account_service);
						Welcome_user.setService(service);
						Welcome_user.setUser(user);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OOPDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeposit.setBounds(427, 230, 120, 25);
		depositFrame.getContentPane().add(btnDeposit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Welcome_user.callUserWelcomeScreen();
				Welcome_user.setAccount(account);
				Welcome_user.setAccountService(account_service);
				Welcome_user.setService(service);
				Welcome_user.setUser(user);
				depositFrame.setVisible(false);
			}
		});
		btnBack.setBounds(594, 230, 114, 25);
		depositFrame.getContentPane().add(btnBack);
		
		/* Disabling all the invalid inputs like special characters into the First Name field*/
		textField.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c<48 || c>57) { 
	                e.consume(); 
	            }
	        }
	    });
		
		}
}
