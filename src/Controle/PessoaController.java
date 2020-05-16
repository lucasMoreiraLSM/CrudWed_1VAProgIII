package Controle;

import Model.Pessoa;

public class PessoaController {
	DAO.DAOpessoa dao = new DAO.DAOpessoa();

	public Pessoa NovaPessoa(Pessoa p) {
		int id = dao.addPessoa(p);
		p = dao.getPessoaById(id);
		return p;
	}

	public void UpdatePessoa(Pessoa p) {
		dao.updatePessoa(p);
	}

}
