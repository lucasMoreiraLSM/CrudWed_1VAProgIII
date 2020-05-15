package Model;

import java.sql.Date;

public class Pessoa {
	private int id;
	private String nome;
	private String sexo;
	private String data_nascimento;
	private String cpf;
	private String senha;
	private String end_rua;
	private String end_bairro;
	private String end_numero;
	private String end_complemento;
	

	public int getId() {
		return id;
	}
	
	  public void setId(int id) {
	        this.id = id;
	    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEnd_rua() {
		return end_rua;
	}

	public void setEnd_rua(String end_rua) {
		this.end_rua = end_rua;
	}

	public String getEnd_bairro() {
		return end_bairro;
	}

	public void setEnd_bairro(String end_bairro) {
		this.end_bairro = end_bairro;
	}

	public String getEnd_numero() {
		return end_numero;
	}

	public void setEnd_numero(String string) {
		this.end_numero = string;
	}


	public String getEnd_complemento() {
		return end_complemento;
	}

	public void setEnd_complemento(String end_complemento) {
		this.end_complemento = end_complemento;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", data_nascimento=" + data_nascimento
				+ ", cpf=" + cpf + ", senha=" + senha + ", end_rua=" + end_rua + ", end_bairro=" + end_bairro
				+ ", end_numero=" + end_numero + ", end_complemento=" + end_complemento + "]";
	}

	
	
}
