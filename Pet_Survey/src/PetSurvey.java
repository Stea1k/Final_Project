
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PetSurvey extends JFrame {

	static boolean Dog = false;
	static boolean Cat = false;
	static boolean Fish = false;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetSurvey frame = new PetSurvey();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PetSurvey() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Please select the pets you have");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		final JCheckBox chckbxDog = new JCheckBox("Dog");
		chckbxDog.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				PetSurvey.Dog = chckbxDog.isSelected();
			}
		});
		GridBagConstraints gbc_chckbxDog = new GridBagConstraints();
		gbc_chckbxDog.anchor = GridBagConstraints.WEST;
		gbc_chckbxDog.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDog.gridx = 3;
		gbc_chckbxDog.gridy = 2;
		contentPane.add(chckbxDog, gbc_chckbxDog);
		
		final JCheckBox chckbxCat = new JCheckBox("Cat");
		chckbxCat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				PetSurvey.Cat = chckbxCat.isSelected();
			}
		});
		GridBagConstraints gbc_chckbxCat = new GridBagConstraints();
		gbc_chckbxCat.anchor = GridBagConstraints.WEST;
		gbc_chckbxCat.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxCat.gridx = 3;
		gbc_chckbxCat.gridy = 3;
		contentPane.add(chckbxCat, gbc_chckbxCat);
		
		final JCheckBox chckbxFish = new JCheckBox("Fish");
		chckbxFish.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				PetSurvey.Fish = chckbxFish.isSelected();
			}
		});
		GridBagConstraints gbc_chckbxFish = new GridBagConstraints();
		gbc_chckbxFish.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxFish.anchor = GridBagConstraints.WEST;
		gbc_chckbxFish.gridx = 3;
		gbc_chckbxFish.gridy = 4;
		contentPane.add(chckbxFish, gbc_chckbxFish);
		
		
		final JLabel lblSurveyResults = new JLabel("Survey Results");
		GridBagConstraints gbc_lblSurveyResults = new GridBagConstraints();
		gbc_lblSurveyResults.gridx = 3;
		gbc_lblSurveyResults.gridy = 6;
		contentPane.add(lblSurveyResults, gbc_lblSurveyResults);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String hasDog = (Dog) ? "one dog":"no dogs";
				String hasCat = (Cat) ? "one cat":"no cats";
				String hasFish = (Fish) ? "one fish":"no fish";
				
				String surveyResultsString = 
						"User has " + hasDog + " and " + hasCat + " and " + hasFish +".";
				lblSurveyResults.setText(surveyResultsString);
			}
		});
		
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 5;
		contentPane.add(btnSubmit, gbc_btnSubmit);
	}

}
