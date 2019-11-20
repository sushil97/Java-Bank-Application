package com.demo.OOPD_Project.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.demo.OOPD_Project.Bean.AccountHolderBean;
import com.demo.OOPD_Project.dao.AccountHolderDAO;
import com.demo.OOPD_Project.exception.OOPDException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WithdrawForm {

	private JFrame withdrawFrame;
	private static AccountHolderBean user;
	private static AccountHolderDAO service; 
	private JTextField textField;
	
	public static void setUser(AccountHolderBean usr)
	{
		user = usr;
	}
	
	public static void setService(AccountHolderDAO s)
	{
		service = s;
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
		
		JLabel lblCurrentBalance = new JLabel("Current Balance :");
		lblCurrentBalance.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCurrentBalance.setBounds(46, 12, 200, 25);
		withdrawFrame.getContentPane().add(lblCurrentBalance);
		
		JLabel lblgetBalance = new JLabel("New label");
		lblgetBalance.setFont(new Font("Dialog", Font.BOLD, 15));
		lblgetBalance.setBounds(200, 12, 200, 25);
		withdrawFrame.getContentPane().add(lblgetBalance);
		
		textField = new JTextField();
		textField.setBounds(500, 169, 200, 30);
		withdrawFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterAmount = new JLabel("Enter Amount");
		lblEnterAmount.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEnterAmount.setBounds(320, 169, 150, 30);
		withdrawFrame.getContentPane().add(lblEnterAmount);
		
		JButton btnNewButton = new JButton("Withdraw");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(427, 230, 120, 25);
		withdrawFrame.getContentPane().add(btnNewButton);
		
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