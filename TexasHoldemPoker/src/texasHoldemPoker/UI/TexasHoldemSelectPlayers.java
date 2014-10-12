package texasHoldemPoker.UI;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;

import texasHoldemPoker.Common.Validators;
import texasHoldemPoker.Model.Player;
import texasHoldemPoker.Persistence.Sql.Dao.IPlayerDAO;
import texasHoldemPoker.Persistence.Sql.Dao.PlayerDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TexasHoldemSelectPlayers extends JFrame {

	private JList listPlayers;
	private JList listPokerPlayers;
	private IPlayerDAO playerDAO;
	private JLabel lblPlayerSalary;
	private JTextField txtBigBlind;

	/**
	 * Create the application.
	 */
	public TexasHoldemSelectPlayers() {
		this.playerDAO = new PlayerDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
				
		this.setResizable(false);
		this.setTitle("Seleccion de jugadores");
		this.setBounds(100, 100, 448, 339);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		listPlayers = new JList();
		listPlayers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateBinding();
			}
		});
		
		JButton btnAddPlayer = new JButton("Agregar >>");
		btnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPlayer();
			}
		});
		
		JButton btnRemovePlayer = new JButton("<< Quitar ");
		btnRemovePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePlayer();
			}
		});
		
		listPokerPlayers = new JList();
		
		JButton btnLetsPlayPoker = new JButton("Iniciar Partida");
		btnLetsPlayPoker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playPoker();
			}
		});
		
		JLabel lblPlayers = new JLabel("Jugadores:");
		
		JLabel lblLobby = new JLabel("Mesa:");
		
		JLabel lblSalary = new JLabel("Salario:");
		
		lblPlayerSalary = new JLabel("");
		
		JLabel lblBigBlind = new JLabel("Ciega grande");
		
		txtBigBlind = new JTextField();
		txtBigBlind.setText("10");
		txtBigBlind.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSalary)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPlayerSalary))
						.addComponent(lblPlayers)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(listPlayers, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLetsPlayPoker)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnRemovePlayer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnAddPlayer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtBigBlind))
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblBigBlind, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
											.addGap(16)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLobby)
										.addComponent(listPokerPlayers, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(277, Short.MAX_VALUE)
					.addComponent(btnLetsPlayPoker)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayers)
						.addComponent(lblLobby))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(listPlayers, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(listPokerPlayers, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAddPlayer)
							.addGap(9)
							.addComponent(btnRemovePlayer)
							.addGap(14)
							.addComponent(lblBigBlind)
							.addGap(5)
							.addComponent(txtBigBlind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSalary)
						.addComponent(lblPlayerSalary))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		this.getContentPane().setLayout(groupLayout);
		
		loadPlayers();
	}
	
	private void loadPlayers() {
		ArrayList<Player> players = this.playerDAO.getAllPlayers();		
		
		DefaultListModel listModel = new DefaultListModel();
 	   
	    for(Player player : players)
	    {
	    	listModel.addElement(player.getName());
    	}
	    
	    listPlayers.setModel(listModel);
	}
	
	private void addPlayer() {
		Object actionElement = this.listPlayers.getSelectedValue(); 
		
		DefaultListModel playerModel = this.getDefaultListModel(this.listPlayers, actionElement, false);
		DefaultListModel lobbyModel = this.getDefaultListModel(this.listPokerPlayers, actionElement, true);
		
		this.listPlayers.setModel(playerModel);		
		this.listPokerPlayers.setModel(lobbyModel);
	}
	
	private void removePlayer() {
		Object actionElement = this.listPokerPlayers.getSelectedValue(); 
		
		DefaultListModel playerModel = this.getDefaultListModel(this.listPlayers, actionElement, true);
		DefaultListModel lobbyModel = this.getDefaultListModel(this.listPokerPlayers, actionElement, false);
		
		this.listPlayers.setModel(playerModel);
		this.listPokerPlayers.setModel(lobbyModel);
	}
	
	private DefaultListModel getDefaultListModel(JList list, Object actionElement, boolean add) {
		DefaultListModel listModel = new DefaultListModel();
		
		ListModel model = list.getModel();
		
		for(int i= 0; i < model.getSize(); i++) {
			Object element = model.getElementAt(i);			
			listModel.addElement(element);
		}
		
		if (add) {
			listModel.addElement(actionElement);
		}
		else {
			listModel.removeElement(actionElement);
		}
		
		return listModel;
	}

	
	private void updateBinding() {
		Object actionElement = this.listPlayers.getSelectedValue(); 

		String playerName = (String)actionElement;
		
		if (playerName != null) {
			Player player = playerDAO.getPlayer(playerName);
			
			String playerSalary = Integer.toString(player.getSalary());
				
			this.lblPlayerSalary.setText(playerSalary);
		}
		else {
			this.lblPlayerSalary.setText("");
		}
	}
	
	private void playPoker() {	
		if (listPokerPlayers.getModel() != null && listPokerPlayers.getModel().getSize() >= 2) {
			try {
				String bigBlind = this.txtBigBlind.getText();
				
				if (Validators.isNumeric(bigBlind)) {
					
					int startBigBlind = Integer.parseInt(bigBlind);
					ArrayList<Player> players = this.getPlayers();	
					
					ArrayList<String> playerCantPlay = Validators.somePlayerHasSalaryLessThanBigBlind(players, startBigBlind);
					
					if (playerCantPlay.isEmpty()) {
						TexasHoldemGame game = new TexasHoldemGame(players, startBigBlind);
						game.setVisible(true);
					}
					else {
						JOptionPane.showInputDialog(
								new JFrame(), 
                                "No es posible iniciar el juego, los siguientes jugadores tienen un salario menor a la ciega grande", 
                                "Error al Iniciar el juego", 
                                 JOptionPane.ERROR_MESSAGE, 
                                 null,
                                 playerCantPlay.toArray(), 
                                 playerCantPlay.toArray()[0]);
						playerCantPlay.toArray();
					}				
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "El valor ingresado no es un numero ", "Error",
				        JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "Se necesita un minimo de dos jugadores para iniciar la partida", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(int i = 0 ; i< listPokerPlayers.getModel().getSize(); i++ ){
			String playerName = listPokerPlayers.getModel().getElementAt(i).toString();
			Player player = this.playerDAO.getPlayer(playerName);
			players.add(player);
		}
		
		return players;
	}
}
