package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.demo.OOPD_Project.Bean.AccountBean;
import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.dao.AccountDAO;
import com.demo.OOPD_Project.dao.AccountHolderDAO;
import com.demo.OOPD_Project.dao.Withdraw;
import com.demo.OOPD_Project.exception.OOPDException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class WithdrawForm {

	private JFrame withdrawFrame;
	private static AccountHolderBean user;
	private static AccountHolderDAO service; 
	private static AccountDAO account_service;
	private static AccountBean account;
	public Withdraw withdraw = new Withdraw();
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
	public static void callWithdrawFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawForm window = new WithdrawForm();
					window.withdrawFrame.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.withdrawFrame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.withdrawFrame.getWidth()) / 2);
					window.withdrawFrame.setLocation(x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WithdrawForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		withdrawFrame = new JFrame();
		withdrawFrame.setBounds(1500, 900, 1200, 900);
		withdrawFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		withdrawFrame.getContentPane().setLayout(null);
		
		/* If user tries to cancel the screen directly we will log them out */
		withdrawFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(withdrawFrame, 
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
		lblCurrentBalance.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCurrentBalance.setBounds(46, 12, 155, 25);
		withdrawFrame.getContentPane().add(lblCurrentBalance);
		
		JLabel lblgetBalance = new JLabel(Double.toString(account.getBalance()));
		lblgetBalance.setFont(new Font("Dialog", Font.BOLD, 15));
		lblgetBalance.setBounds(200, 12, 500, 25);
		withdrawFrame.getContentPane().add(lblgetBalance);
		
		textField = new JTextField();
		textField.setBounds(500, 169, 200, 30);
		withdrawFrame.getContentPane().add(textField);
		textField.setColumns(10);
		/* Disabling all the invalid inputs like special characters into the First Name field*/
		textField.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (c<48 || c>57) { 
	                e.consume(); 
	            }
	        }
	    });
		JLabel lblEnterAmount = new JLabel("Enter Amount");
		lblEnterAmount.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEnterAmount.setBounds(320, 169, 150, 30);
		withdrawFrame.getContentPane().add(lblEnterAmount);
		
		JButton btnNewButton = new JButton("Withdraw");
		withdrawFrame.getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println(Double.parseDouble(textField.getText()));
					int status = withdraw.doWithdraw(account, Double.parseDouble(textField.getText()));
					if(status == 1)
					{
						JOptionPane.showMessageDialog(null, "Invalid Amount","Error",JOptionPane.ERROR_MESSAGE);
						textField.setText(null);
					}
					else if(status == 2){
					withdrawFrame.setVisible(false);
					Welcome_user.callUserWelcomeScreen();
					Welcome_user.setAccount(account);
					Welcome_user.setAccountService(account_service);
					Welcome_user.setService(service);
					Welcome_user.setUser(user);
					}
					else
						JOptionPane.showMessageDialog(null, "Transaction failed","Error",JOptionPane.ERROR_MESSAGE);
				} catch (OOPDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(427, 230, 120, 25);
		withdrawFrame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Welcome_user.callUserWelcomeScreen();
				Welcome_user.setAccount(account);
				Welcome_user.setAccountService(account_service);
				Welcome_user.setService(service);
				Welcome_user.setUser(user);
				withdrawFrame.setVisible(false);
			}
		});
		btnBack.setBounds(594, 230, 114, 25);
		withdrawFrame.getContentPane().add(btnBack);
		
		/* If user tries to cancel the screen directly we will log them out */
		withdrawFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(withdrawFrame, 
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
		
	}
}