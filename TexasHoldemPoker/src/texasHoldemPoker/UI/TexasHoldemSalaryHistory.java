package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Persistence.Sql.Dao.PlayerDAO;
import texasHoldemPoker.Common.Validators;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TexasHoldemSalaryHistory {

	private JFrame frmHistorialDeCargas;
	private JTable tblSearch;
	private JTextField txtPlayer;
	private JLabel lblResults;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasHoldemSalaryHistory window = new TexasHoldemSalaryHistory();
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
	public TexasHoldemSalaryHistory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHistorialDeCargas = new JFrame();
		frmHistorialDeCargas.setTitle("Historial de cargas");
		frmHistorialDeCargas.setBounds(100, 100, 576, 403);
		frmHistorialDeCargas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		GroupLayout groupLayout = new GroupLayout(frmHistorialDeCargas.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblPlayer, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(txtPlayer, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnSearch))
						.addComponent(lblResults, Alignment.LEADING))
					.addGap(149))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(150, Short.MAX_VALUE))
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
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		tblSearch = new JTable();
		scrollPane.setViewportView(tblSearch);
		tblSearch.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Monto", "Fecha", "Saldo"
			}
		));
		frmHistorialDeCargas.getContentPane().setLayout(groupLayout);
	}
	
	private void btnSearch() {
		String playerName = txtPlayer.getText();

		if(Validators.StringIsNotNullOrEmpty(playerName)) {
			PlayerDAO playerDao = new PlayerDAO();
			//TO DO Crear la tabla de historiales de cargas...			
			ArrayList<Player> searchResult = playerDao.getAllPlayers(playerName);
			DrawTable(searchResult);
		}		
	}
	
	private void DrawTable(ArrayList<Player> players) {
	    DefaultTableModel tableModel = (DefaultTableModel) tblSearch.getModel();
	    	   
	    for(Player player : players)
	    {
		    tableModel.addRow(new Object[] { player.getName(), player.getEmail(), player.getSalary() });
	    }
	}
}
