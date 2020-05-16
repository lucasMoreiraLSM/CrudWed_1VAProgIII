package Model;

public class Cidade {
	private int id_cidade;
	private String nome;
	private String estado;
	private int id_pessoa;
	
	public int getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(int id_cidade) {
		this.id_cidade = id_cidade;
	}
	public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id_cidade=" + id_cidade + ", nome=" + nome + ", estado=" + estado + ", id_pessoa=" + id_pessoa
				+ "]";
	}


 
	 
}
