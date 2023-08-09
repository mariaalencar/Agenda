dimport java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ConexaoMySQL {
	// Configuração da Conexão com o Banco de Dados
	private Connection conexao;
	private PreparedStatement comandoPreparado;
	private ResultSet resultadoConsulta;

	String user = "root";
	String senha = "usbw";
	String database = "aula";

	public Connection conexao() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307", user, senha);
			System.out.println("Conectado ao MySQL");

			conexao.setCatalog(database);
			System.out.println("Database '" + database + "' selecionada");
		} catch (Exception exc) {
			System.out.print(exc.getMessage());
		}
		return conexao;
	}

	public ResultSet consultar(String sql) {
		try {
			comandoPreparado = conexao().prepareStatement(sql);
			resultadoConsulta = comandoPreparado.executeQuery(sql);
			System.out.println("Consulta SQL executada: " + sql);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		return resultadoConsulta;
	}

	public boolean inserir(String sql) {
		try {
			comandoPreparado = conexao().prepareStatement(sql);
			comandoPreparado.execute();
			System.out.println("Inserção SQL executada: " + sql);

			return true;
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir o registro!", "Erro ao Inserir Registro", JOptionPane.ERROR_MESSAGE);
			System.out.print(exc.getMessage());
			return false;
		}
	}
	
	public boolean atualizar(String sql) {
		try {
			comandoPreparado = conexao().prepareStatement(sql);
			comandoPreparado.execute();
			System.out.println("Atualização SQL executada: " + sql);
			return true;
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o registro!", "Erro ao Atualizar Registro", JOptionPane.ERROR_MESSAGE);
			System.out.println(exc.getMessage());
			return false;
		}
	}
	
	public boolean excluir(String sql) {
		try {
			comandoPreparado = conexao().prepareStatement(sql);
			comandoPreparado.execute();
			System.out.println("Exclusão SQL executada: " + sql);
			return true;
		} 
		catch (MySQLIntegrityConstraintViolationException exc)
		{
			if(exc.getMessage().contains("fk_agenda_tipotelefone"))
			{
				JOptionPane.showMessageDialog(null, "O registro não pode ser excluído pois está vinculado a outro registro da Agenda", "Erro ao Excluir Registro", JOptionPane.ERROR_MESSAGE);	
			}
			return false;
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o registro!", "Erro ao Excluir Registro", JOptionPane.ERROR_MESSAGE);
			System.out.print(exc.getMessage());
			return false;
		}
	}
}
