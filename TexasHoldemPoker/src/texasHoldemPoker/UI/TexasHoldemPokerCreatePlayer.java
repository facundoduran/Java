package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import texasHoldemPoker.Common.Validators;
import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Persistence.Sql.Dao.PlayerDAO;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TexasHoldemPokerCreatePlayer extends javax.swing.JDialog {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtBalance;

	public TexasHoldemPokerCreatePlayer(java.awt.Frame parent, boolean modal) {
		super(parent, modal);		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		this.setBounds(100, 100, 270, 202);
		this.setTitle("Crear jugador");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		JLabel lblName = new JLabel("Nombre:");
		
		JLabel lblEmail = new JLabel("Email:");
		
		JLabel lblBalance = new JLabel("Salario:");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblEmail)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBalance)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnCancel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSave))
								.addComponent(txtEmail)
								.addComponent(txtBalance, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBalance)
						.addComponent(txtBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);		
	}
	
	private void btnSaveClick()
	{
		PlayerDAO playerDao = new PlayerDAO();
		String playerName = txtName.getText();
		String playerEmail = txtEmail.getText();
		String playerBalance = txtBalance.getText();
		
		if (Validators.StringIsNotNullOrEmpty(playerName) &&
			Validators.StringIsNotNullOrEmpty(playerEmail) &&
			Validators.isNumeric(playerBalance))
		{
			int playerSalary = Integer.parseInt(playerBalance);
			
			Player player = new Player(playerName, playerEmail, playerSalary);
			playerDao.insertPlayer(player);
			
			JOptionPane.showMessageDialog(new JFrame(), "Se ha creado el jugador", "Jugador creado con exito",
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