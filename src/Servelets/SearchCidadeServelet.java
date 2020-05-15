package Servelets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controle.FileController;
import DAO.DAOcidade;
import DAO.DAOpessoa;
import Model.Cidade;

/**
 * Servlet implementation class SearchCidadeServelet
 */
public class SearchCidadeServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCidadeServelet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FileController fc = new FileController("ExibirCidade.html", this.getServletContext().getRealPath(File.separator));

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();

		String html = fc.getFileFromDisk();
		DAOcidade daocidade = new DAOcidade();
		List<Cidade> cidadeList = null;
		System.out.println(request.getParameter("id_pessoa"));
			cidadeList = daocidade.getCidadeByIdPessoa(request.getParameter("id_pessoa"));
	
		String stringToReplace = "";
		for (Cidade cid : cidadeList) {
			stringToReplace = stringToReplace + "<tr>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" + cid.getId_cidade() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" + cid.getNome() + "</td>";
					
			stringToReplace = stringToReplace + "</tr>";
		}
		html = html.replace("<%%>", stringToReplace);

		out.println(html);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
