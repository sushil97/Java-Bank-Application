package com.demo.OOPD_Project.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.demo.OOPD_Project.Bean.AdminBean;
import com.demo.OOPD_Project.dao.AdminDAO;
import com.demo.OOPD_Project.exception.OOPDException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowReportGUI {

	private JFrame showReport;
	private JTextField textField;
	private static AdminBean admin;
	private static AdminDAO service;
	private JButton btnShow;
	
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
	public static void callShowReport() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowReportGUI window = new ShowReportGUI();
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - window.showReport.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - window.showReport.getWidth()) / 2);
					window.showReport.setLocation(x, y);
					window.showReport.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowReportGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		showReport = new JFrame();
		showReport.setBounds(1500, 900, 1200, 900);
		showReport.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		showReport.getContentPane().setLayout(null);
		
		showReport.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(showReport, 
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
		
		JLabel lblEnterAccountNumber = new JLabel("Enter Account Number");
		lblEnterAccountNumber.setFont(new Font("Dialog", Font.BOLD, 19));
		lblEnterAccountNumber.setBounds(257, 419, 270, 15);
		showReport.getContentPane().add(lblEnterAccountNumber);
		
		textField = new JTextField();
		textField.setBounds(537, 410, 300, 35);
		showReport.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnButton = new JButton("Back");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminWelcomeScreen.setAdmin(admin);
				AdminWelcomeScreen.setService(service);
				AdminWelcomeScreen.callAdminWelcomeScreen();
				showReport.setVisible(false);
			}
		});
		btnButton.setBounds(640, 496, 114, 35);
		showReport.getContentPane().add(btnButton);
		
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					report.setData(service.showReport(textField.getText()));
					report.callreport();
				} catch (OOPDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnShow.setBounds(456, 496, 114, 35);
		showReport.getContentPane().add(btnShow);
	}
}
