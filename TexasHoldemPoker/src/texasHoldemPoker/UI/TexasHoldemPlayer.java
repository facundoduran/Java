package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Persistence.Sql.Dao.IPlayerDAO;
import texasHoldemPoker.Persistence.Sql.Dao.PlayerDAO;
import texasHoldemPoker.Common.Validators;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TexasHoldemPlayer extends JFrame {

	private JFrame frmHistorialDeCargas;
	private JTable tblSearch;
	private JTextField txtPlayer;
	private JLabel lblResults;
	private IPlayerDAO playerDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasHoldemPlayer window = new TexasHoldemPlayer();
					window.frmHistorialDeCargas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TexasHoldemPlayer() {
		this.playerDao = new PlayerDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		this.setVisible(true);
		this.setTitle("Busqueda de jugador: ");
		this.setBounds(100, 100, 566, 426);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch();
			}
		});
		
		JLabel lblPlayer = new JLabel("Jugador:");
		
		txtPlayer = new JTextField();
		txtPlayer.setColumns(10);
		
		lblResults = new JLabel("Resultados para su busqueda:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnShowSalaryHistory = new JButton("Ver Historial");
		btnShowSalaryHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnShowSalaryHistory();
			}
		});
		
		JButton btnAddCredit = new JButton("Cargar Saldo");
		btnAddCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCredit();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPlayer, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(txtPlayer, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(lblResults)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAddCredit)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnShowSalaryHistory))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(12, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPlayer, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPlayer, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSearch)))
					.addGap(14)
					.addComponent(lblResults)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowSalaryHistory)
						.addComponent(btnAddCredit))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblSearch = new JTable();
		scrollPane.setViewportView(tblSearch);
		tblSearch.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Email", "Saldo"
			}
		));
		this.getContentPane().setLayout(groupLayout);
	}
	
	private void btnAddCredit() {
		int selectedRow = this.tblSearch.getSelectedRow();
		
		if (selectedRow >= 0)
		{
			String playerName = this.tblSearch.getModel().getValueAt(selectedRow, 0).toString();
			Player player = this.playerDao.getPlayer(playerName);	

			TexasHoldemPokerAddCredit playerDetail = new TexasHoldemPokerAddCredit(this, true, player);
			playerDetail.setVisible(true);
		}			
	}
	
	private void btnSearch() {
		String playerName = this.txtPlayer.getText();

		if(Validators.StringIsNotNullOrEmpty(playerName)) {
			ArrayList<Player> searchResult = this.playerDao.getAllPlayers(playerName);
			DrawTable(searchResult);
		}		
	}
	
	private void btnShowSalaryHistory()
	{
		int selectedRow = this.tblSearch.getSelectedRow();
		
		if (selectedRow >= 0)
		{
			String playerName = this.tblSearch.getModel().getValueAt(selectedRow, 0).toString();
			Player player = this.playerDao.getPlayer(playerName);	

			TexasHoldemSalaryHistoryDetail playerDetail = new TexasHoldemSalaryHistoryDetail(this, true, player);
			playerDetail.setVisible(true);
		}		
	}
	
	private void DrawTable(ArrayList<Player> players) {
	    DefaultTableModel tableModel = (DefaultTableModel) this.tblSearch.getModel();
	    	   
	    for(Player player : players)
	    {
		    tableModel.addRow(new Object[] { player.getName(), player.getEmail(), player.getSalary() });
	    }
	}
}
