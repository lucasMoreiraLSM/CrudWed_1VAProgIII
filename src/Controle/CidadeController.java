package Controle;

import DAO.DAOcidade;
import Model.Cidade;

public class CidadeController {
	DAOcidade dao = new DAOcidade();

	public void AddCidade(Cidade cid) {
		dao.addCidade(cid);
	}
}
