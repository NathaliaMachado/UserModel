package aula.ps.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import aula.ps.user.Pessoa;


public class PessoaDao {
	
	private Connection connection;
	
	public void create(Pessoa pessoa) throws Exception {
		
		this.connection = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = null;
		String sql = "insert into Pessoa "
		+ "(nome, email, cpf, telefone, sexo, datanascimento)"
		+ "values (?,?,?,?,?,?);" ;
		
		try {
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getEmail());
			stmt.setString(3, pessoa.getCpf());
			stmt.setString(4, pessoa.getTelefone());
			stmt.setString(5, pessoa.getSexo().toString());
			stmt.setDate(6, new Date(pessoa.getDatanascimento().getTime()));
			
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new Exception(e);
			
		}
		finally {
			
			try {
				stmt.close();
				connection.close();
				
			}
			catch (SQLException e) {
				throw new Exception(e);
			}
		}
	}
	
	
	public List<Pessoa> getList() throws Exception {
		
		this.connection = new ConnectionFactory().getConnection();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			
			stmt = this.connection.prepareStatement("select * from Pessoa;");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Pessoa pessoa = new Pessoa();
				
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoa.setSexo(rs.getString("sexo").charAt(0));
				pessoa.setDatanascimento(rs.getDate("datanascimento"));
				
		        pessoas.add(pessoa);
				
			}
		}	
			catch (SQLException e) {
				throw new Exception(e);
				
			}
			finally {
				
				try {
					rs.close();
					stmt.close();
					connection.close();
				}
				catch (SQLException e) {
					throw new Exception(e);
				}
			}
			return pessoas;
	}
	
	
	public Pessoa read(String cpf) throws Exception {
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "Select * from Pessoa where cpf = ?;" ;
		Pessoa pessoa = new Pessoa();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoa.setSexo(rs.getString("sexo").charAt(0));
				pessoa.setDatanascimento(rs.getDate("datanascimento"));
				
			}
			
		}
		catch (SQLException e) {
			throw new Exception(e);
			
		}
		finally {
			
			try {
				rs.close();
				stmt.close();
				connection.close();
			}
			catch (SQLException e) {
				throw new Exception(e);
			}
		}
		return pessoa;
	}
	
	
	public void update(Pessoa pessoa) throws Exception {
		
		this.connection = new ConnectionFactory().getConnection();
		
		String sql = "update Pessoa set nome = ?, email = ?, "
		+ " telefone = ?, sexo = ?, datanascimento = ? where cpf = ?;";
		
		PreparedStatement stmt = null;
		
		try {
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getEmail());
			stmt.setString(3, pessoa.getTelefone());
			stmt.setString(4, pessoa.getSexo().toString());
			stmt.setDate(5, new Date(pessoa.getDatanascimento().getTime()));
			stmt.setString(6, pessoa.getCpf());
			
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new Exception(e);
			
		}
		finally {
			
			try {
				stmt.close();
				connection.close();
			}
			catch (SQLException e) {
				throw new Exception(e);
			}
		}
	}
	
	
	public void delete(Pessoa pessoa) throws Exception {
		
		this.connection = new ConnectionFactory().getConnection();
		
		String sql = "delete from Pessoa where cpf = ?;" ;
		PreparedStatement stmt = null;
		
		try {
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getCpf());
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new Exception(e);
			
		}
		finally {
			
			try {
				stmt.close();
				connection.close();
				
			}
			catch (SQLException e) {
				throw new Exception(e);
			}
		}
	}
	
	
}
