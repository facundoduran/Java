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
	private Player player;
	private IPlayerDAO playerDAO;
	private ISalaryHistoryDAO salaryHistoryDAO;
	
	public TexasHoldemPokerAddCredit(java.awt.Frame parent, boolean modal, Player player) {
		super(parent, modal);
		setResizable(false);
		
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
		
		String playerName = player != null ? player.getName() : "";
		
		JLabel lblPlayerName = new JLabel(playerName);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(130)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBalance)
								.addComponent(lblName))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlayerName)
								.addComponent(txtBalance, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))))
					.addGap(21))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblPlayerName))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBalance)
						.addComponent(txtBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		this.setBounds(100, 100, 309, 138);
		this.setTitle("Cargar Saldo");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void btnSaveClick()
	{		
		String playerBalance = txtBalance.getText();
		
		if (Validators.isNumeric(playerBalance) && this.player != null)
		{
			int salary = player.getSalary();
			int amount = Integer.parseInt(playerBalance);
			int playerId = player.getId();
			
			int newSalary = salary + amount;

			//insert into table
			salaryHistoryDAO.AddBalance(amount, newSalary, playerId);
			
			//update credit			
			playerDAO.updateSalary(playerId, newSalary);
			
			JOptionPane.showMessageDialog(new JFrame(), "Se ha acreditado el saldo en la cuenta ", "Carga realizada con exito",
			        JOptionPane.INFORMATION_MESSAGE);
			
			this.dispose();
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