package com.kerberos.client.linux.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class comp_get_ticket extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldPrincipal;
	private JPasswordField passwordFieldTpassword;
	private JTextField textFieldLifetime;
	private JTextField textFieldRenewLifetime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			comp_get_ticket dialog = new comp_get_ticket();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public comp_get_ticket() {
		setType(Type.NORMAL);
		setResizable(false);
		setTitle("Get Ticket");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 305);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Principal");
		lblUsername.setBounds(10, 16, 75, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 52, 75, 14);
		contentPanel.add(lblPassword);
		
		textFieldPrincipal = new JTextField();
		textFieldPrincipal.setBounds(85, 8, 390, 31);
		contentPanel.add(textFieldPrincipal);
		textFieldPrincipal.setColumns(10);
		
		passwordFieldTpassword = new JPasswordField();
		passwordFieldTpassword.setBounds(85, 44, 390, 31);
		contentPanel.add(passwordFieldTpassword);
		
		JLabel lblCurrentLifetime = new JLabel("Ticket Lifetime:");
		lblCurrentLifetime.setBounds(10, 89, 111, 14);
		contentPanel.add(lblCurrentLifetime);
		
		final JCheckBox chckbxForwardable = new JCheckBox("Forwardable");
		chckbxForwardable.setSelected(true);
		chckbxForwardable.setBounds(10, 131, 115, 23);
		contentPanel.add(chckbxForwardable);
		
		final JCheckBox chckbxProxiable = new JCheckBox("Proxiable");
		chckbxProxiable.setSelected(true);
		chckbxProxiable.setBounds(129, 131, 87, 23);
		contentPanel.add(chckbxProxiable);
		
		final JCheckBox chckbxAnonymous = new JCheckBox("Anonymous");
		chckbxAnonymous.setBounds(218, 131, 115, 23);
		contentPanel.add(chckbxAnonymous);
		
		final JCheckBox chckbxAddresses = new JCheckBox("Include Addresses");
		chckbxAddresses.setBounds(333, 131, 150, 23);
		contentPanel.add(chckbxAddresses);
		
		final JCheckBox chckbxCanonicalize = new JCheckBox("Canonicalize");
		chckbxCanonicalize.setBounds(10, 167, 115, 23);
		contentPanel.add(chckbxCanonicalize);
		
		final JCheckBox chckbxEnterprise = new JCheckBox("Client is enterprise principal name");
		chckbxEnterprise.setBounds(129, 167, 311, 23);
		contentPanel.add(chckbxEnterprise);
		
		JLabel lblRenewableLifetime = new JLabel("Renewable Lifetime:");
		lblRenewableLifetime.setBounds(14, 206, 144, 14);
		contentPanel.add(lblRenewableLifetime);
		
		final DefaultComboBoxModel comboLifetimeName = new DefaultComboBoxModel();
		comboLifetimeName.addElement("seconds");
		comboLifetimeName.addElement("minutes");
		comboLifetimeName.addElement("hours");
		comboLifetimeName.addElement("days");
		
		final JComboBox comboBox = new JComboBox(comboLifetimeName);
		comboBox.setBounds(242, 81, 107, 31);
		contentPanel.add(comboBox);
		comboBox.setSelectedIndex(3);
		
		textFieldLifetime = new JTextField();
		textFieldLifetime.setText("1");
		textFieldLifetime.setBounds(146, 81, 86, 31);
		contentPanel.add(textFieldLifetime);
		textFieldLifetime.setColumns(10);
		
		textFieldRenewLifetime = new JTextField();
		textFieldRenewLifetime.setText("6");
		textFieldRenewLifetime.setColumns(10);
		textFieldRenewLifetime.setBounds(146, 197, 86, 31);
		contentPanel.add(textFieldRenewLifetime);
		
		final DefaultComboBoxModel comboRenewLifetimeName = new DefaultComboBoxModel();
		comboRenewLifetimeName.addElement("seconds");
		comboRenewLifetimeName.addElement("minutes");
		comboRenewLifetimeName.addElement("hours");
		comboRenewLifetimeName.addElement("days");
		
		final JComboBox comboBox_1 = new JComboBox(comboRenewLifetimeName);
		comboBox_1.setBounds(241, 197, 108, 31);
		contentPanel.add(comboBox_1);
		comboBox_1.setSelectedIndex(3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						System.out.println("Get Ticket OK clicked");
						String kinit_cmd = "kinit ";
						String dataPrincipal = textFieldPrincipal.getText();
						String dataTpassword = new String(passwordFieldTpassword.getPassword());
						System.out.println("Replacing non-alphanumberic, @ - . _ characters from principal.");
						//Replace all non-alphanumeric characters from principal
						dataPrincipal = dataPrincipal.replaceAll("[^A-Za-z0-9@-_.]", "");
						System.out.println("Principal: "+dataPrincipal);
						//Forwardable
						if(chckbxForwardable.isSelected())
							kinit_cmd = kinit_cmd.concat("-f ");
						else
							kinit_cmd = kinit_cmd.concat("-F ");
						//Proxiable
						if(chckbxProxiable.isSelected())
							kinit_cmd = kinit_cmd.concat("-p ");
						else
							kinit_cmd = kinit_cmd.concat("-P ");
						//Anonymous
						if(chckbxAnonymous.isSelected())
							kinit_cmd = kinit_cmd.concat("-n ");
						//Include Addresses
						if(chckbxAddresses.isSelected())
							kinit_cmd = kinit_cmd.concat("-a ");
						else
							kinit_cmd = kinit_cmd.concat("-A ");
						//Canonicalize
						if(chckbxCanonicalize.isSelected())
							kinit_cmd = kinit_cmd.concat("-C ");
						//Client is enterprise principal name
						if(chckbxEnterprise.isSelected())
							kinit_cmd = kinit_cmd.concat("-E ");
						//Set ticket lifetime
						String ticketLifetimeType = comboBox.getSelectedItem().toString();
						String ticketLifetimeValue = textFieldLifetime.getText();
						if(ticketLifetimeType.equals("days"))
							kinit_cmd = kinit_cmd.concat("-l "+ticketLifetimeValue+"d ");
						else if(ticketLifetimeType.equals("hours"))
							kinit_cmd = kinit_cmd.concat("-l "+ticketLifetimeValue+"h ");
						else if(ticketLifetimeType.equals("minutes"))
							kinit_cmd = kinit_cmd.concat("-l "+ticketLifetimeValue+"m ");
						else if(ticketLifetimeType.equals("seconds"))
							kinit_cmd = kinit_cmd.concat("-l "+ticketLifetimeValue+"s ");
						else
							kinit_cmd = kinit_cmd.concat("-l "+ticketLifetimeValue+"d ");
						//Set ticket renewable lifetime
						String ticketRenewLifetimeType = comboBox_1.getSelectedItem().toString();
						String ticketRenewLifetimeValue = textFieldRenewLifetime.getText();
						if(ticketRenewLifetimeType.equals("days"))
							kinit_cmd = kinit_cmd.concat("-r "+ticketRenewLifetimeValue+"d ");
						else if(ticketRenewLifetimeType.equals("hours"))
							kinit_cmd = kinit_cmd.concat("-r "+ticketRenewLifetimeValue+"h ");
						else if(ticketRenewLifetimeType.equals("minutes"))
							kinit_cmd = kinit_cmd.concat("-r "+ticketRenewLifetimeValue+"m ");
						else if(ticketRenewLifetimeType.equals("seconds"))
							kinit_cmd = kinit_cmd.concat("-r "+ticketRenewLifetimeValue+"s ");
						else
							kinit_cmd = kinit_cmd.concat("-r "+ticketRenewLifetimeValue+"d ");
						//Set username in kinit
						kinit_cmd = kinit_cmd.concat(dataPrincipal);
						//Final kinit command
						//kinit_cmd = "echo "+ dataTpassword + " | " + kinit_cmd;
						System.out.println("Final kinit: "+kinit_cmd);
						
						try
						{
							// Get runtime
					        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
					        // Start a new process: UNIX command ls
					        java.lang.Process p = rt.exec(kinit_cmd);
					        // Get process' output: its InputStream
					        /*
					        java.io.InputStream is = p.getInputStream();
					        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
					        // And print each line
					        String s = null;
					        String s_final = "";
					        while ((s = reader.readLine()) != null)
					        {
					            System.out.println("Line: "+s);
					            s_final = s_final.concat(s);
					        }
					        System.out.println(s_final);
					        is.close();
					        */
					        // send command
					        OutputStream out = p.getOutputStream();
							out.write(dataTpassword.getBytes());
							out.close();
							// You can or maybe should wait for the process to complete
					        p.waitFor();
							System.out.println("Process exited with code = " + p.exitValue());
							int kinit_cmd_exit_value = p.exitValue();
							if(kinit_cmd_exit_value==0)
							{
								JOptionPane.showMessageDialog(contentPanel, "Success", null, JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(contentPanel, "Failed. Try again.", null, JOptionPane.ERROR_MESSAGE);
							}
						}
						catch (Exception e_get_ticket_exec)
						{
							// TODO Auto-generated catch block
							e_get_ticket_exec.printStackTrace();
						}
						System.out.println("done");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						System.out.println("Get Ticket CANCEL clicked");
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
