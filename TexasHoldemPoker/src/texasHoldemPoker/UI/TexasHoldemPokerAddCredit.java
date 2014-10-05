package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import texasHoldemPoker.Common.DateHelper;
import texasHoldemPoker.Common.Validators;
import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Persistence.Sql.Dao.IPlayerDAO;
import texasHoldemPoker.Persistence.Sql.Dao.ISalaryHistoryDAO;
import texasHoldemPoker.Persistence.Sql.Dao.PlayerDAO;
import texasHoldemPoker.Persistence.Sql.Dao.SalaryHistoryDAO;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TexasHoldemPokerAddCredit extends javax.swing.JDialog {

	private JFrame frame;
	private JTextField txtBalance;
	private JLabel lblName;
	private JLabel lblPlayerName;
	private Player player;
	private IPlayerDAO playerDAO;
	private ISalaryHistoryDAO salaryHistoryDAO;
	
	public TexasHoldemPokerAddCredit(java.awt.Frame parent, boolean modal, Player player) {
		super(parent, modal);
		
		this.player = player;
		this.salaryHistoryDAO = new SalaryHistoryDAO();
		this.playerDAO = new PlayerDAO();
		
		JButton btnSave = new JButton("Aceptar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveClick();
			}
		});
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelClick();
			}
		});
		
		lblName = new JLabel("Nombre:");
		
		JLabel lblBalance = new JLabel("Saldo a cargar:");
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		
		lblPlayerName = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblName)
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlayerName)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(btnCancel)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSave)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBalance)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtBalance, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
							.addGap(9))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblPlayerName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBalance)
						.addComponent(txtBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		this.setBounds(100, 100, 270, 137);
		this.setTitle("Crear jugador");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void btnSaveClick()
	{		
		String playerBalance = txtBalance.getText();
		
		if (Validators.isNumeric(playerBalance) && this.player != null)
		{
			double salary = Double.parseDouble(playerBalance);
			double amount = Double.parseDouble(playerBalance);
			int playerId = player.getId();

			//insert into table
			salaryHistoryDAO.AddBalance(amount, salary, playerId);			
			//update credit
			playerDAO.updateSalary(playerId, salary);
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "El valor ingresado no es un numero ", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void btnCancelClick()
	{
		dispose();
	}	
}