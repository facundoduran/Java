package texasHoldemPoker.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TexasHoldemSalaryHistory {

	private JFrame frmHistorialDeCargas;
	private JTable table;
	private JTextField textField;
	private JLabel lblNewLabel_1;

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
		frmHistorialDeCargas.setBounds(100, 100, 449, 403);
		frmHistorialDeCargas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmHistorialDeCargas.getContentPane().setLayout(springLayout);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Saldo"
			}
		));
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, frmHistorialDeCargas.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, -10, SpringLayout.SOUTH, frmHistorialDeCargas.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 423, SpringLayout.WEST, frmHistorialDeCargas.getContentPane());
		frmHistorialDeCargas.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("Buscar");
		springLayout.putConstraint(SpringLayout.NORTH, table, 34, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, frmHistorialDeCargas.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, frmHistorialDeCargas.getContentPane());
		frmHistorialDeCargas.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Jugador:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frmHistorialDeCargas.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 70, SpringLayout.WEST, frmHistorialDeCargas.getContentPane());
		frmHistorialDeCargas.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 10, SpringLayout.NORTH, frmHistorialDeCargas.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 92, SpringLayout.WEST, frmHistorialDeCargas.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -34, SpringLayout.NORTH, table);
		springLayout.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, btnNewButton);
		frmHistorialDeCargas.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Resultados para su busqueda:");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, frmHistorialDeCargas.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -6, SpringLayout.NORTH, table);
		frmHistorialDeCargas.getContentPane().add(lblNewLabel_1);
	}
}
