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
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;


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
	
	/**
	 * Create the frame.
	 */
	public ticketGUI() {
		super("List of Tickets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ticketListModel = new DefaultListModel<Ticket>();
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTicketSystem = new JLabel("Ticket System");
		GridBagConstraints gbc_lblTicketSystem = new GridBagConstraints();
		gbc_lblTicketSystem.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketSystem.gridx = 1;
		gbc_lblTicketSystem.gridy = 0;
		contentPane.add(lblTicketSystem, gbc_lblTicketSystem);

		final JList ticketJList = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 3;
		gbc_list.gridy = 7;
		contentPane.add(ticketJList, gbc_list);

		JButton btnAddTicket = new JButton("Add Ticket");
		
		GridBagConstraints gbc_btnAddTicket = new GridBagConstraints();
		gbc_btnAddTicket.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddTicket.gridx = 1;
		gbc_btnAddTicket.gridy = 2;
		contentPane.add(btnAddTicket, gbc_btnAddTicket);
		
		JLabel lblProblemDescription = new JLabel("Problem Description");
		GridBagConstraints gbc_lblProblemDescription = new GridBagConstraints();
		gbc_lblProblemDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblProblemDescription.anchor = GridBagConstraints.EAST;
		gbc_lblProblemDescription.gridx = 1;
		gbc_lblProblemDescription.gridy = 3;
		contentPane.add(lblProblemDescription, gbc_lblProblemDescription);
		
		textFieldPD = new JTextField();
		textFieldPD.setText(" ");
		GridBagConstraints gbc_textFieldPD = new GridBagConstraints();
		gbc_textFieldPD.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPD.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPD.gridx = 3;
		gbc_textFieldPD.gridy = 3;
		contentPane.add(textFieldPD, gbc_textFieldPD);
		textFieldPD.setColumns(10);
		
		JLabel labelReporter = new JLabel("Problem Reporter?");
		GridBagConstraints gbc_labelReporter = new GridBagConstraints();
		gbc_labelReporter.anchor = GridBagConstraints.EAST;
		gbc_labelReporter.insets = new Insets(0, 0, 5, 5);
		gbc_labelReporter.gridx = 1;
		gbc_labelReporter.gridy = 4;
		contentPane.add(labelReporter, gbc_labelReporter);
		
		txtReporter = new JTextField();
		GridBagConstraints gbc_txtReporter = new GridBagConstraints();
		gbc_txtReporter.insets = new Insets(0, 0, 5, 5);
		gbc_txtReporter.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtReporter.gridx = 3;
		gbc_txtReporter.gridy = 4;
		contentPane.add(txtReporter, gbc_txtReporter);
		txtReporter.setColumns(10);
		
		JLabel lblPriority = new JLabel("Priority (20 pt scale)");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.anchor = GridBagConstraints.EAST;
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 1;
		gbc_lblPriority.gridy = 5;
		contentPane.add(lblPriority, gbc_lblPriority);
		
		txtPriority = new JTextField();
		GridBagConstraints gbc_txtPriority = new GridBagConstraints();
		gbc_txtPriority.insets = new Insets(0, 0, 5, 5);
		gbc_txtPriority.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPriority.gridx = 3;
		gbc_txtPriority.gridy = 5;
		contentPane.add(txtPriority, gbc_txtPriority);
		txtPriority.setColumns(10);
		
		JLabel lblCurrentTickets = new JLabel("Current Tickets");
		GridBagConstraints gbc_lblCurrentTickets = new GridBagConstraints();
		gbc_lblCurrentTickets.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentTickets.gridx = 3;
		gbc_lblCurrentTickets.gridy = 6;
		contentPane.add(lblCurrentTickets, gbc_lblCurrentTickets);
		
		JButton btnDeleteTicket = new JButton("Delete Ticket");
		
		GridBagConstraints gbc_btnDeleteTicket = new GridBagConstraints();
		gbc_btnDeleteTicket.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteTicket.gridx = 1;
		gbc_btnDeleteTicket.gridy = 7;
		contentPane.add(btnDeleteTicket, gbc_btnDeleteTicket);
		
		ticketJList.setModel(ticketListModel);
		
		ticketJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		
		btnDeleteTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				ticket2 = (DefaultListModel)ticketJList.getModel();
				Ticket toDelete = ticket2.getElementAt(ticketJList.getSelectedIndex());
				ticketGUI.this.ticketListModel.removeElement(toDelete);
			}
		});
	}

}
