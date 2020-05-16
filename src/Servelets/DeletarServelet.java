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
import Model.Pessoa;

/**
 * Servlet implementation class DeletarServelet
 */
public class DeletarServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletarServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
FileController fc = new FileController("DeleteCadastro.html",this.getServletContext().getRealPath(File.separator));
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out =  response.getWriter();
		
		String html = fc.getFileFromDisk();
		DAOpessoa daopessoa = new DAOpessoa();
		List<Pessoa> pessoaList = null;
		System.out.println(request.getParameter("id_pessoa"));
	
	    Pessoa pessoa = daopessoa.getPessoaById(Integer.parseInt(request.getParameter("id")));
		
		String stringToReplace =  "";
		 
			stringToReplace = stringToReplace + "<tr>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getId() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getNome() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getSexo() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getData_nascimento() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getCpf() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getSenha() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_rua() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_bairro() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_numero() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_complemento() +"</td>";
			stringToReplace = stringToReplace + "</tr>";
		
		html =  html.replace("<%tabelaPessoa%>", stringToReplace);
		
		
		DAOcidade daocidade = new DAOcidade();
		Cidade cid = daocidade.getCidadeById_pesssoa(pessoa.getId());
		
		String stringToReplaice2 = "";
		
			stringToReplaice2 = stringToReplaice2 + "<tr>";
			stringToReplaice2 += "<td style=\"border: 1px solid black;\">" + cid.getId_cidade() + "</td>";
			stringToReplaice2 += "<td style=\"border: 1px solid black;\">" + cid.getNome() + "</td>";
			stringToReplaice2 += "<td style=\"border: 1px solid black;\">" + cid.getEstado() + "</td>";
					
			stringToReplaice2 = stringToReplaice2 + "</tr>";
		
		html = html.replace("<%tabelaCidade%>", stringToReplaice2);
		html = html.replace("<%1%>", pessoa.getNome());
		html = html.replace("<%idpessoa%>",Integer.toString(pessoa.getId()));
		out.println(html);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_pessoa = Integer.parseInt(request.getParameter("id"));
		DAO.DAOcidade daocidade = new DAOcidade();
		Cidade ced = daocidade.getCidadeById_pesssoa(id_pessoa);
		daocidade.deleteCidade(ced.getId_cidade());
		
		DAO.DAOpessoa daopessoa= new DAOpessoa();
		daopessoa.deletePessoa(id_pessoa);
		
		response.sendRedirect("http://localhost:8080/CrudWed_1VAProgIII/SearchServelet");
		
		
		//doGet(request, response);
	}

}
