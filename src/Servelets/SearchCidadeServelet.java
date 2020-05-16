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
		int id_pessoa = Integer.parseInt(request.getParameter("id_pessoa"));
		String nome_pessoa= request.getParameter("nome_pessoa");
		Cidade cid = daocidade.getCidadeById_pesssoa(id_pessoa);
	
		String stringToReplaice2 = "";
		
			stringToReplaice2 = stringToReplaice2 + "<tr>";
			stringToReplaice2 += "<td style=\"border: 1px solid black;\">" + cid.getId_cidade() + "</td>";
			stringToReplaice2 += "<td style=\"border: 1px solid black;\">" + cid.getNome() + "</td>";
			stringToReplaice2 += "<td style=\"border: 1px solid black;\">" + cid.getEstado() + "</td>";
					
			stringToReplaice2 = stringToReplaice2 + "</tr>";
		
		html = html.replace("<%1%>", nome_pessoa);
		html = html.replace("<%2%>", stringToReplaice2);

		out.println(html);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
