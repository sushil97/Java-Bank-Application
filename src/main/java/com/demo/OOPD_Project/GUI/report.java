package com.demo.OOPD_Project.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class report {

	private JFrame frame;
	private static String data;
	public static void setData(String s)
	{
		data = s;
	}
	/**
	 * Launch the application.
	 */
	public static void callreport() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					report window = new report();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public report() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(100, 40, 200, 200);
		textArea.setText(data);
		textArea.setEnabled(false);
		frame.getContentPane().add(textArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnClose.setBounds(314, 215, 114, 25);
		frame.getContentPane().add(btnClose);
	}
}
