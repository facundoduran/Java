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
import javax.swing.table.DefaultTableModel;

public class TexasHoldemSalaryHistoryDetail {

	private JFrame frmDetalleDelHistorial;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TexasHoldemSalaryHistoryDetail window = new TexasHoldemSalaryHistoryDetail();
					window.frmDetalleDelHistorial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TexasHoldemSalaryHistoryDetail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDetalleDelHistorial = new JFrame();
		frmDetalleDelHistorial.setTitle("Detalle del historial de cargas");
		frmDetalleDelHistorial.setBounds(100, 100, 450, 300);
		frmDetalleDelHistorial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDetalleDelHistorial.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frmDetalleDelHistorial.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblNewLabel = new JLabel("Historial de cargas de :");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, panel);
		panel.add(lblNewLabel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Saldo", "Fecha de carga"
			}
		));
		table.getColumnModel().getColumn(1).setResizable(false);
		sl_panel.putConstraint(SpringLayout.NORTH, table, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, table, 195, SpringLayout.SOUTH, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, table, 424, SpringLayout.WEST, panel);
		panel.add(table);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.SOUTH, table);
		sl_panel.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, panel);
		panel.add(btnNewButton);
	}
}
