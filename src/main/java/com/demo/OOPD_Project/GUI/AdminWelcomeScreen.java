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
	 * @throws OOPDException 
	 */
	public AdminWelcomeScreen() throws OOPDException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws OOPDException 
	 */
	private void initialize() throws OOPDException {
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
						System.exit(0);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            System.exit(0);
		        }
		    }
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to Logout","Logout Alert",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					Home_Screen.main(null);
					AdminWelcome.setVisible(false);
					try {
						service.adminLogout(admin);
						System.exit(0);
					} catch (OOPDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		btnLogout.setBounds(159, 248, 200, 25);
		AdminWelcome.getContentPane().add(btnLogout);
		
		JButton btnSetInterest = new JButton("Set Interest");
		btnSetInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setInterestGUI.adminBean(admin);
				setInterestGUI.adminDao(service);
				setInterestGUI.callSetInterest();
				AdminWelcome.setVisible(false);
			}
		});
		btnSetInterest.setBounds(159, 177, 200, 25);
		AdminWelcome.getContentPane().add(btnSetInterest);
		
		JLabel lblCurrentInterest = new JLabel("Current Interest Rate Structure:");
		lblCurrentInterest.setBounds(32, 71, 235, 15);
		AdminWelcome.getContentPane().add(lblCurrentInterest);
		
		JLabel lblNewLabel = new JLabel(Double.toString(service.getInterestDB(admin)));
		lblNewLabel.setBounds(270, 71, 66, 15);
		AdminWelcome.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Set Tax");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTaxGUI.adminBean(admin);
				setTaxGUI.adminDao(service);
				setTaxGUI.runSetTaxGui();
				AdminWelcome.setVisible(false);
			}
		});
		btnNewButton.setBounds(777, 177, 200, 25);
		AdminWelcome.getContentPane().add(btnNewButton);
		
		JLabel lblCurrentTaxRate = new JLabel("Current Tax Rate Structure :");
		lblCurrentTaxRate.setBounds(32, 93, 235, 15);
		AdminWelcome.getContentPane().add(lblCurrentTaxRate);
		
		JLabel lblNewLabel_1 = new JLabel(Double.toString(service.getTaxDB(admin)));
		lblNewLabel_1.setBounds(240, 93, 66, 15);
		AdminWelcome.getContentPane().add(lblNewLabel_1);
		
		JLabel curr = new JLabel("Interest");
		curr.setBounds(650, 38, 66, 15);
		AdminWelcome.getContentPane().add(curr);
		
		JLabel label = new JLabel("*");
		label.setBounds(730, 38, 66, 15);
		AdminWelcome.getContentPane().add(label);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(750, 38, 66, 15);
		AdminWelcome.getContentPane().add(lblBalance);
		
		JLabel label_1 = new JLabel("-----------");
		label_1.setBounds(710, 52, 66, 15);
		AdminWelcome.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("100");
		label_2.setBounds(720, 71, 66, 15);
		AdminWelcome.getContentPane().add(label_2);
		
		JLabel lblNewLabel_2 = new JLabel("Sum Of (");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel_2.setBounds(500, 50, 300, 35);
		AdminWelcome.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(")");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel_3.setBounds(810, 50, 66, 35);
		AdminWelcome.getContentPane().add(lblNewLabel_3);
		
		JButton btnShowReport = new JButton("Show Report");
		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowReportGUI.callShowReport();
				ShowReportGUI.setAdmin(admin);
				ShowReportGUI.setService(service);
				AdminWelcome.setVisible(false);
			}
		});
		btnShowReport.setBounds(777, 248, 200, 25);
		AdminWelcome.getContentPane().add(btnShowReport);
		
		JLabel label_3 = new JLabel("[");
		label_3.setFont(new Font("Dialog", Font.BOLD, 28));
		label_3.setBounds(825, 50, 20, 35);
		AdminWelcome.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("1");
		label_4.setBounds(847, 52, 10, 15);
		AdminWelcome.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("-");
		label_5.setFont(new Font("Dialog", Font.BOLD, 19));
		label_5.setBounds(872, 50, 20, 15);
		AdminWelcome.getContentPane().add(label_5);
		
		JLabel lblTax = new JLabel("tax/100");
		lblTax.setBounds(888, 50, 66, 15);
		AdminWelcome.getContentPane().add(lblTax);
		
		JLabel lblNewLabel_4 = new JLabel("]");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel_4.setBounds(950, 50, 66, 35);
		AdminWelcome.getContentPane().add(lblNewLabel_4);
	}
}
