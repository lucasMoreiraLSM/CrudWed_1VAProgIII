package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ConecaoUtil.DbUtil;
import Model.Pessoa;

public class DAOpessoa {
	private Connection connection;

	public DAOpessoa() {
		connection = DbUtil.getConnection();
	}

	public int addPessoa(Pessoa pessoa) {
		int IdAux = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into pessoa(nome,sexo,data_nascimento,cpf,senha,end_rua,end_bairro,end_numero,end_complemento) values (?, ?, ?, ? ,?, ?, ?, ?, ?) returning id_pessoa;"
					, Statement.RETURN_GENERATED_KEYS );
			
			//preparedStatement.setInt(0, pessoa.getId()); // duvido uma vez que alto
			// incremente ??
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getSexo());
			preparedStatement.setString(3, pessoa.getData_nascimento());
			preparedStatement.setString(4, pessoa.getCpf());
			preparedStatement.setString(5, pessoa.getSenha());
			preparedStatement.setString(6, pessoa.getEnd_rua());
			preparedStatement.setString(7, pessoa.getEnd_bairro());
			preparedStatement.setString(8, pessoa.getEnd_numero());
			preparedStatement.setString(9, pessoa.getEnd_complemento());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
				IdAux = rs.getInt(1);
			}
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	return IdAux; }

	public void deletePessoa(int pessoaId) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from pessoa where id_pessoa=?");
			// Parameters start with 1
			preparedStatement.setInt(1, pessoaId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePessoa(Pessoa pessoa) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update pessoa set nome=?,sexo=?,data_nascimento=?,cpf=?,senha=?,end_rua=?,end_bairro=?,end_numero=?,end_complemento=?"
							+ "where id_pessoa=?");
			// Parameters start with 1
			
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getSexo());
			preparedStatement.setString(3,pessoa.getData_nascimento());
			preparedStatement.setString(4, pessoa.getCpf());
			preparedStatement.setString(5, pessoa.getSenha());
			preparedStatement.setString(6, pessoa.getEnd_rua());
			preparedStatement.setString(7, pessoa.getEnd_bairro());
			preparedStatement.setString(8, pessoa.getEnd_numero());
			preparedStatement.setString(9, pessoa.getEnd_complemento());
			preparedStatement.setInt(10, pessoa.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Pessoa> getAllPessoas() {
		List<Pessoa> listaDePessoa = new ArrayList<Pessoa>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from pessoa");
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setData_nascimento(rs.getString("data_nascimento"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setSenha(rs.getString("senha"));
				pessoa.setEnd_rua(rs.getString("end_rua"));
				pessoa.setEnd_bairro(rs.getString("end_bairro"));
				pessoa.setEnd_numero(rs.getString("end_numero"));
				pessoa.setEnd_complemento(rs.getString("end_complemento"));
				


				listaDePessoa.add(pessoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDePessoa;
	}
	public Pessoa getPessoaById(int id) {
        Pessoa pessoa = new Pessoa();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from pessoa where id_pessoa=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	pessoa.setId(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setData_nascimento(rs.getString("data_nascimento"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setSenha(rs.getString("senha"));
				pessoa.setEnd_rua(rs.getString("end_rua"));
				pessoa.setEnd_bairro(rs.getString("end_bairro"));
				pessoa.setEnd_numero(rs.getString("end_numero"));
				pessoa.setEnd_complemento(rs.getString("end_complemento"));
				
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }
	public List<Pessoa> getPessoasByNome(String nome) {
		List<Pessoa> listaDePessoa = new ArrayList<Pessoa>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from pessoa where nome like '"+nome+"%'");
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setData_nascimento(rs.getString("data_nascimento"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setSenha(rs.getString("senha"));
				pessoa.setEnd_rua(rs.getString("end_rua"));
				pessoa.setEnd_bairro(rs.getString("end_bairro"));
				pessoa.setEnd_numero(rs.getString("end_numero"));
				pessoa.setEnd_complemento(rs.getString("end_complemento"));
				
				listaDePessoa.add(pessoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDePessoa;
	}
}
