package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ConecaoUtil.DbUtil;
import Model.Cidade;
import Model.Pessoa;

public class DAOcidade {
	private Connection connection;

	public DAOcidade() {
		connection = DbUtil.getConnection();
	}

	public void addCidade(Cidade cidade) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into cidade (nome, estado, id_pessoa) values (?, ?, ?);");

			preparedStatement.setString(1, cidade.getNome());
			preparedStatement.setString(2, cidade.getEstado());
			preparedStatement.setInt(3, cidade.getId_pessoa());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCidade(int id_cidade) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from cidade where id_cidade=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id_cidade);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCidade(Cidade cidade) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update cidade set nome=?,estado=? where id_pessoa=?");
			// Parameters start with 1
			preparedStatement.setString(1, cidade.getNome());
			preparedStatement.setString(2, cidade.getEstado());
			preparedStatement.setInt(3, cidade.getId_pessoa());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Cidade> getAllCidades() {
		List<Cidade> listaDeCidade = new ArrayList<Cidade>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from cidade");
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId_cidade(rs.getInt("id_cidade"));
				cidade.setNome(rs.getString("nome"));
				

				listaDeCidade.add(cidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDeCidade;
	}

	public Cidade getCidadeById(int id, Cidade ListaDeCidade) {
		Cidade cidade = new Cidade();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from cidade where id_cidade=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				cidade.setId_cidade(rs.getInt("id"));
				cidade.setNome(rs.getString("nome"));
				cidade.setEstado(rs.getString("estado"));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		public List<Cidade> getCidadesById_pesssoa  (String id_pessoa) {
			List<Cidade> listaDeCidade = new ArrayList<Cidade>();
	        Cidade cid = new Cidade();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from cidade where id_pessoa=?");
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	cid.setId_cidade(rs.getInt("id_Cidade"));
					cid.setNome(rs.getString("nome"));
					cid.setEstado(rs.getString("estado"));
					cid.setId_pessoa(rs.getInt("id_pessoa"));
					
					
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cid;
	    }

		return ListaDeCidade;
	}
}
