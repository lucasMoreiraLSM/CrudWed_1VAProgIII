package Controle;

import DAO.DAOcidade;
import Model.Cidade;
import Model.Pessoa;

public class CidadeController {
	DAOcidade dao = new DAOcidade();

	public void AddCidade(Cidade cid) {
		dao.addCidade(cid);
	}
	public void UpdateCidade(Cidade cid) {
		dao.updateCidade(cid);		
	}

	
}
