import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cadastro extends JFrame {
	JTextField textFieldNome = new JTextField();
	JTextField textFieldIdade = new JTextField();
	JTextField textFieldTelefone = new JTextField();
	JTextField textFieldEmail = new JTextField();
	JLabel labelNome = new JLabel();
	JLabel labelIdade = new JLabel();
	JLabel labelTelefone = new JLabel();
	JLabel labelEmail = new JLabel();
	JButton buttonInserir = new JButton();
	JButton buttonCancelar = new JButton();
	JButton buttonConsultar = new JButton();

	Cadastro() {
		desenhaTela();
	}

	private void validaCampos() throws Exception {
		if (textFieldNome.getText().trim().length() < 2 || textFieldNome.getText().trim().equals("")) {
			textFieldNome.requestFocus();
			throw new Exception("Digite um nome válido");
		}
	}

	private void desenhaTela() {
		this.setTitle("Agenda V1");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.labelNome.setLocation(20, 20);
		this.labelNome.setSize(40, 20);
		this.labelNome.setText("Nome:");

		this.textFieldNome.setLocation(80, 20);
		this.textFieldNome.setSize(200, 20);

		this.labelTelefone.setLocation(20, 50);
		this.labelTelefone.setSize(60, 20);
		this.labelTelefone.setText("Idade:");

		this.textFieldTelefone.setLocation(80, 50);
		this.textFieldTelefone.setSize(200, 20);

		this.labelIdade.setLocation(20, 80);
		this.labelIdade.setSize(60, 20);
		this.labelIdade.setText("Telefone:");

		this.textFieldIdade.setLocation(80, 80);
		this.textFieldIdade.setSize(200, 20);

		this.textFieldEmail.setLocation(80, 110);
		this.textFieldEmail.setSize(200, 20);

		this.labelEmail.setLocation(20, 110);
		this.labelEmail.setSize(60, 20);
		this.labelEmail.setText("E-mail:");

		this.buttonInserir.setLocation(20, 140);
		this.buttonInserir.setSize(100, 20);
		this.buttonInserir.setText("Inserir");
		this.buttonInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// AÇÕES DO BOTÃO DE INSERIR

				try {
					validaCampos();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Erro ao Inserir Registro",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		this.buttonCancelar.setLocation(140, 140);
		this.buttonCancelar.setSize(100, 20);
		this.buttonCancelar.setText("Cancelar");
		this.buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				textFieldIdade.setText("");
				textFieldTelefone.setText("");
				textFieldEmail.setText("");
				textFieldNome.requestFocus();
			}
		});

		this.buttonConsultar.setLocation(260, 140);
		this.buttonConsultar.setSize(100, 20);
		this.buttonConsultar.setText("Consultar");
		this.buttonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// AÇÕES DO BOTÃO DE CONSULTAR
				new Consultar();
			}
		});

		this.add(labelNome);
		this.add(textFieldNome);
		this.add(labelTelefone);
		this.add(textFieldTelefone);
		this.add(labelIdade);
		this.add(textFieldIdade);
		this.add(labelEmail);
		this.add(textFieldEmail);
		this.add(buttonCancelar);
		this.add(buttonInserir);
		this.add(buttonConsultar);

		// this.add(buttonCalcula);

		this.setVisible(true);
	}
}
