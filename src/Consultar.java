import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Consultar extends JDialog {

	JTextField textFieldFiltro = new JTextField();
	JButton buttonFiltrar = new JButton();
	
	DefaultTableModel tableModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;//Torna a tabela não editável
		}
	};
	JTable tabela = new JTable(tableModel);
	JScrollPane scrollTable = new JScrollPane(tabela);

	Consultar() {
		desenhaTela();
	}

	private void desenhaTela() {
		this.setTitle("Consulta Agenda");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 300);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);

		this.textFieldFiltro.setLocation(10, 220);
		this.textFieldFiltro.setSize(300, 20);

		this.buttonFiltrar.setLocation(320, 220);
		this.buttonFiltrar.setSize(100, 20);
		this.buttonFiltrar.setText("Filtrar");
		this.buttonFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// AÇÕES DO BOTÃO DE FILTRAR
				Connection conexao;
				PreparedStatement comandoPreparado;
				ResultSet resultadoConsulta;
				
				String user = "root";
				String senha = "usbw";
				String database = "aula";
				String sql = "SELECT * FROM agenda where (nome  like '%" + textFieldFiltro.getText() +"%')";
				try {
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307", user, senha);
					System.out.println("Conectado ao MySQL");
					conexao.setCatalog(database);
					System.out.println("Database '" + database + "' selecionada");
				
					comandoPreparado = conexao.prepareStatement(sql);
					resultadoConsulta = comandoPreparado.executeQuery(sql);
					System.out.println("Consulta SQL executada: " + sql);
					// Limpa a tabela
					tableModel.getDataVector().removeAllElements();
					tableModel.fireTableDataChanged();
					
					while (resultadoConsulta.next()) {
						tableModel.addRow(
								new Object[] { resultadoConsulta.getString("Nome"),resultadoConsulta.getInt("Idade"), resultadoConsulta.getString("Telefone"), resultadoConsulta.getString("Email") });
					}
				
				} catch (Exception exc) {
					System.out.print(exc.getMessage());
				}
			}
		});

		tableModel.addColumn("Nome");
		tableModel.addColumn("Idade");
		tableModel.addColumn("Telefone");
		tableModel.addColumn("E-mail");
		scrollTable.setSize(580, 200);
		scrollTable.setLocation(10, 10);
		
		this.add(textFieldFiltro);
		this.add(buttonFiltrar);
		this.add(scrollTable);

		// this.add(buttonCalcula);

		this.setVisible(true);
	}
}
