package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Model.SalaryHistory;
import texasHoldemPoker.Persistence.Sql.Dao.ISalaryHistoryDAO;
import texasHoldemPoker.Persistence.Sql.Dao.SalaryHistoryDAO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class TexasHoldemSalaryHistoryDetail extends javax.swing.JDialog {

	private JFrame frmDetalleDelHistorial;
	private JTable tblSalaryHistory;
	private JLabel lblPlayerName;
	private Player player;
	private ISalaryHistoryDAO salaryHistoryDAO;

	/**
	 * Create the application.
	 */
	public TexasHoldemSalaryHistoryDetail(java.awt.Frame parent, boolean modal, Player player) {
		super(parent, modal);
		
		this.player = player;
		this.salaryHistoryDAO = new SalaryHistoryDAO();

		JPanel panel = new JPanel();
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblHistorial = new JLabel("Historial de cargas de :");
		sl_panel.putConstraint(SpringLayout.NORTH, lblHistorial, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblHistorial, 10, SpringLayout.WEST, panel);
		panel.add(lblHistorial);
		
		JButton btnNewButton = new JButton("Salir");
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, panel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 31, SpringLayout.SOUTH, lblHistorial);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblHistorial);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, btnNewButton);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, panel);
		panel.add(scrollPane);
		
		tblSalaryHistory = new JTable();
		tblSalaryHistory.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Monto", "Fecha", "Saldo"
			}
		));
		
		scrollPane.setViewportView(tblSalaryHistory);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
		);
		
		lblPlayerName = new JLabel("");
		this.InitializeFormValues(this.player);

		sl_panel.putConstraint(SpringLayout.NORTH, lblPlayerName, 0, SpringLayout.NORTH, lblHistorial);
		sl_panel.putConstraint(SpringLayout.WEST, lblPlayerName, 33, SpringLayout.EAST, lblHistorial);
		panel.add(lblPlayerName);
		this.getContentPane().setLayout(groupLayout);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		this.setBounds(100, 100, 461, 315);
		this.setTitle("Detalle del historial de cargas");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void InitializeFormValues(Player player)
	{
		if (player != null) {
			this.lblPlayerName.setText(player.getName());
			
			int playerId = player.getId();
			
			ArrayList<SalaryHistory> playerSalaryHistory = this.salaryHistoryDAO.GetByPlayerId(playerId);
		
			this.DrawTable(playerSalaryHistory);
		}
	}
	
	private void DrawTable(ArrayList<SalaryHistory> history) {
		
	    DefaultTableModel tableModel = (DefaultTableModel) this.tblSalaryHistory.getModel();
	    	   
		for(SalaryHistory salaryHistory : history) {
			tableModel.addRow(new Object[] { salaryHistory.getDate(), salaryHistory.getAmount(), salaryHistory.getBalance() });
	    }
	}
}
