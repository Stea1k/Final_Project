import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JCheckBox;


public class ticketGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPD;
	private JTextField txtReporter;
	private JTextField txtPriority;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ticketGUI frame = new ticketGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	DefaultListModel<Ticket> ticketListModel;
	DefaultListModel<Ticket> ticket2;
	private GridBagConstraints gbc_listResolvedTickets;
	DefaultListModel<Ticket> ResolvedListModel;
	
	/**
	 * Create the frame.
	 */
	public ticketGUI() {
		super("List of Tickets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ticketListModel = new DefaultListModel<Ticket>();
		ResolvedListModel = new DefaultListModel<Ticket>();
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 187, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTicketSystem = new JLabel("Ticket System");
		GridBagConstraints gbc_lblTicketSystem = new GridBagConstraints();
		gbc_lblTicketSystem.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketSystem.gridx = 0;
		gbc_lblTicketSystem.gridy = 0;
		contentPane.add(lblTicketSystem, gbc_lblTicketSystem);
		
		JButton btnSaveData = new JButton("Save Data");
		GridBagConstraints gbc_btnSaveData = new GridBagConstraints();
		gbc_btnSaveData.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveData.gridx = 2;
		gbc_btnSaveData.gridy = 0;
		contentPane.add(btnSaveData, gbc_btnSaveData);
		
		JButton btnAddTicket = new JButton("Add Ticket");
				
		GridBagConstraints gbc_btnAddTicket = new GridBagConstraints();
		gbc_btnAddTicket.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddTicket.gridx = 2;
		gbc_btnAddTicket.gridy = 2;
		contentPane.add(btnAddTicket, gbc_btnAddTicket);
				
		btnAddTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String problem = textFieldPD.getText();
				String reporter = txtReporter.getText();
				String priorityString = txtPriority.getText();
				Date dReport = new Date();
				int priority;
				try{
					priority = Integer.parseInt(priorityString);
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(ticketGUI.this, "Enter an integer for priority.");
					return;
				}
				Ticket newTicket = new Ticket(problem,priority,reporter,dReport);
				ticketGUI.this.ticketListModel.addElement(newTicket);
			}
		});
		
		JLabel lblCurrentTickets = new JLabel("Current Tickets");
		GridBagConstraints gbc_lblCurrentTickets = new GridBagConstraints();
		gbc_lblCurrentTickets.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentTickets.gridx = 0;
		gbc_lblCurrentTickets.gridy = 7;
		contentPane.add(lblCurrentTickets, gbc_lblCurrentTickets);
		
		JLabel lblUnresolved = new JLabel("Unresolved");
		GridBagConstraints gbc_lblUnresolved = new GridBagConstraints();
		gbc_lblUnresolved.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnresolved.gridx = 0;
		gbc_lblUnresolved.gridy = 9;
		contentPane.add(lblUnresolved, gbc_lblUnresolved);
		
		JLabel lblResolved = new JLabel("Resolved");
		GridBagConstraints gbc_lblResolved = new GridBagConstraints();
		gbc_lblResolved.insets = new Insets(0, 0, 5, 0);
		gbc_lblResolved.gridx = 2;
		gbc_lblResolved.gridy = 9;
		contentPane.add(lblResolved, gbc_lblResolved);
		
		JLabel lblProblemDescription = new JLabel("Problem Description");
		GridBagConstraints gbc_lblProblemDescription = new GridBagConstraints();
		gbc_lblProblemDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblProblemDescription.anchor = GridBagConstraints.EAST;
		gbc_lblProblemDescription.gridx = 0;
		gbc_lblProblemDescription.gridy = 3;
		contentPane.add(lblProblemDescription, gbc_lblProblemDescription);
		
		textFieldPD = new JTextField();
		textFieldPD.setText(" ");
		GridBagConstraints gbc_textFieldPD = new GridBagConstraints();
		gbc_textFieldPD.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPD.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPD.gridx = 2;
		gbc_textFieldPD.gridy = 3;
		contentPane.add(textFieldPD, gbc_textFieldPD);
		textFieldPD.setColumns(10);
		
		JLabel labelReporter = new JLabel("Problem Reporter?");
		GridBagConstraints gbc_labelReporter = new GridBagConstraints();
		gbc_labelReporter.anchor = GridBagConstraints.EAST;
		gbc_labelReporter.insets = new Insets(0, 0, 5, 5);
		gbc_labelReporter.gridx = 0;
		gbc_labelReporter.gridy = 4;
		contentPane.add(labelReporter, gbc_labelReporter);
		
		txtReporter = new JTextField();
		GridBagConstraints gbc_txtReporter = new GridBagConstraints();
		gbc_txtReporter.insets = new Insets(0, 0, 5, 0);
		gbc_txtReporter.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtReporter.gridx = 2;
		gbc_txtReporter.gridy = 4;
		contentPane.add(txtReporter, gbc_txtReporter);
		txtReporter.setColumns(10);
		
		JLabel lblPriority = new JLabel("Priority (20 pt scale)");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.anchor = GridBagConstraints.EAST;
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 5;
		contentPane.add(lblPriority, gbc_lblPriority);
		
		txtPriority = new JTextField();
		GridBagConstraints gbc_txtPriority = new GridBagConstraints();
		gbc_txtPriority.insets = new Insets(0, 0, 5, 0);
		gbc_txtPriority.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPriority.gridx = 2;
		gbc_txtPriority.gridy = 5;
		contentPane.add(txtPriority, gbc_txtPriority);
		txtPriority.setColumns(10);
		
		final JList ticketJList = new JList();
		GridBagConstraints gbc_listResolved = new GridBagConstraints();
		gbc_listResolved.insets = new Insets(0, 0, 5, 5);
		gbc_listResolved.fill = GridBagConstraints.BOTH;
		gbc_listResolved.gridx = 0;
		gbc_listResolved.gridy = 10;
		contentPane.add(ticketJList, gbc_listResolved);
				
		ticketJList.setModel(ticketListModel);
			
		ticketJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final JList listResolvedTickets = new JList();
		GridBagConstraints gbc_listResolvedTickets;
		gbc_listResolvedTickets = new GridBagConstraints();
		gbc_listResolvedTickets.insets = new Insets(0, 0, 5, 0);
		gbc_listResolvedTickets.fill = GridBagConstraints.BOTH;
		gbc_listResolvedTickets.gridx = 2;
		gbc_listResolvedTickets.gridy = 10;
		contentPane.add(listResolvedTickets, gbc_listResolvedTickets);

		listResolvedTickets.setModel(ResolvedListModel);
		
		JButton btnResolveTicket = new JButton("Resolve Ticket");
		
		GridBagConstraints gbc_btnResolveTicket = new GridBagConstraints();
		gbc_btnResolveTicket.insets = new Insets(0, 0, 0, 5);
		gbc_btnResolveTicket.gridx = 0;
		gbc_btnResolveTicket.gridy = 11;
		contentPane.add(btnResolveTicket, gbc_btnResolveTicket);
		
		JButton btnDeleteTicket = new JButton("Delete Resolved Ticket");
		
		GridBagConstraints gbc_btnDeleteTicket = new GridBagConstraints();
		gbc_btnDeleteTicket.gridx = 2;
		gbc_btnDeleteTicket.gridy = 11;
		contentPane.add(btnDeleteTicket, gbc_btnDeleteTicket);
		
		btnResolveTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ticket2 = (DefaultListModel)ticketJList.getModel();
				Ticket resolvedTicket = ticket2.getElementAt(ticketJList.getSelectedIndex());
				resolvedTicket.setResolvedTrue();
				ticketGUI.this.ResolvedListModel.addElement(resolvedTicket);
				ticketGUI.this.ticketListModel.removeElement(resolvedTicket);
			}
		});
		
		btnDeleteTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				ticket2 = (DefaultListModel)listResolvedTickets.getModel();
				Ticket toDelete = ticket2.getElementAt(listResolvedTickets.getSelectedIndex());
				ticketGUI.this.ResolvedListModel.removeElement(toDelete);
			}
		});
		final File save_file = new File("save_file.txt");
		btnSaveData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					exportList(ticketListModel,ResolvedListModel,save_file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	public static void exportList(ListModel unresolved,ListModel resolved, File f) throws IOException {
	    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
	    try {
	        int lenU = unresolved.getSize();
	        int lanR = resolved.getSize();
	        for (int i = 0; i < lenU; i++) {
	            pw.println(unresolved.getElementAt(i).toString());
	        }
	        for(int i = 0; i<lanR; i ++){
	        	pw.println(resolved.getElementAt(i).toString());
	        }
	    } finally {
	        pw.close();
	    }
	}
}
