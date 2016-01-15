package com.kerberos.client.linux.gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.InputStreamReader;

public class start_gui {

	private JFrame frmFreeipaLinuxClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start_gui window = new start_gui();
					window.frmFreeipaLinuxClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public start_gui()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		// Create some items to add to the list
		final DefaultListModel demoList = new DefaultListModel();
		demoList.addElement("<html><pre>Tickets:</pre></html>");
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frmFreeipaLinuxClient = new JFrame();
		frmFreeipaLinuxClient.setTitle("FreeIPA Linux Client");
		frmFreeipaLinuxClient.setResizable(false);
		frmFreeipaLinuxClient.setBounds(100, 100, 568, 231);
		frmFreeipaLinuxClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmFreeipaLinuxClient.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("Exiting...");
				frmFreeipaLinuxClient.dispose();
			}
		});
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mnNewMenu.add(mntmChangePassword);
		mnNewMenu.add(mntmExit);
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane.showMessageDialog(frmFreeipaLinuxClient, "Kerberos Linux Client v0.1", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnNewMenu_1.add(mntmAbout);
		
		final JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					System.out.println("Execute Refresh");
					// Get runtime
			        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
			        // Start a new process: UNIX command ls
			        java.lang.Process p = rt.exec("klist");
			        // You can or maybe should wait for the process to complete
			        p.waitFor();
			        //System.out.println("Process exited with code = " + p.exitValue());
			        // Get process' output: its InputStream
			        java.io.InputStream is = p.getInputStream();
			        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
			        // And print each line
			        String s = null;
			        String s_final = "";
			        while ((s = reader.readLine()) != null)
			        {
			            System.out.println("Line: "+s);
			            s_final = s_final.concat(s+"<br>");
			        }
			        demoList.clear();
			        demoList.addElement("<html><pre>"+s_final+"</pre></html>");
			        is.close();
				}
				catch (Exception e_refresh)
				{
					// TODO Auto-generated catch block
					e_refresh.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(427, 5, 127, 23);
		
		JButton btnNewButton_1 = new JButton("Renew Ticket");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					System.out.println("Execute Renew Ticket");
					// Get runtime
			        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
			        // Start a new process: UNIX command ls
			        java.lang.Process p = rt.exec("kinit -R");
			        // You can or maybe should wait for the process to complete
			        p.waitFor();
			        System.out.println("Process exited with code = " + p.exitValue());
			        int kinit_renew_cmd_exit_value = p.exitValue();
					if(kinit_renew_cmd_exit_value==0)
					{
						JOptionPane.showMessageDialog(frmFreeipaLinuxClient, "Ticket renew successful", "Renew Ticket", JOptionPane.INFORMATION_MESSAGE);
						btnNewButton_3.doClick();
					}
					else
					{
						JOptionPane.showMessageDialog(frmFreeipaLinuxClient, "Ticked renew failed. Try again.", "Renew Ticket", JOptionPane.ERROR_MESSAGE);
						btnNewButton_3.doClick();
					}
				}
				catch (Exception e_destroy)
				{
					// TODO Auto-generated catch block
					e_destroy.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(147, 5, 127, 23);
		
		JButton btnNewButton_2 = new JButton("Destroy Ticket");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					System.out.println("Execute Destroy Ticket");
					// Get runtime
			        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
			        // Start a new process: UNIX command ls
			        java.lang.Process p = rt.exec("kdestroy");
			        // You can or maybe should wait for the process to complete
			        p.waitFor();
			        System.out.println("Process exited with code = " + p.exitValue());
			        int kdestroy_cmd_exit_value = p.exitValue();
					if(kdestroy_cmd_exit_value==0)
					{
						JOptionPane.showMessageDialog(frmFreeipaLinuxClient, "Tickets deleted successfully", "Delete Ticket", JOptionPane.INFORMATION_MESSAGE);
						btnNewButton_3.doClick();
					}
					else
					{
						JOptionPane.showMessageDialog(frmFreeipaLinuxClient, "Ticked deletion failed. Try again.", "Delete Ticket", JOptionPane.ERROR_MESSAGE);
						btnNewButton_3.doClick();
					}
				}
				catch (Exception e_destroy)
				{
					// TODO Auto-generated catch block
					e_destroy.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(284, 5, 127, 23);
		
		frmFreeipaLinuxClient.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Get Ticket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("Execute Get Ticket");
				comp_get_ticket newWindow = new comp_get_ticket();
				newWindow.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 5, 127, 23);
		frmFreeipaLinuxClient.getContentPane().add(btnNewButton);
		frmFreeipaLinuxClient.getContentPane().add(btnNewButton_1);
		frmFreeipaLinuxClient.getContentPane().add(btnNewButton_2);
		frmFreeipaLinuxClient.getContentPane().add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 40, 542, 130);
		frmFreeipaLinuxClient.getContentPane().add(scrollPane);
		
		JList list = new JList(demoList);
		scrollPane.setColumnHeaderView(list);
		btnNewButton_3.doClick();
	}
}
