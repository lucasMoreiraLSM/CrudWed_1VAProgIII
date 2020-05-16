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
import DAO.DAOpessoa;
import Model.Pessoa;

/**
 * Servlet implementation class SearchServelet
 */
public class SearchServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServelet() {
        super();
       
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileController fc = new FileController("index.html",this.getServletContext().getRealPath(File.separator));
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out =  response.getWriter();
		
		String html = fc.getFileFromDisk();
		DAOpessoa daopessoa = new DAOpessoa();
		List<Pessoa> pessoaList = null;
		System.out.println(request.getParameter("pesq"));
		if(request.getParameter("pesq") == null) {
			pessoaList = daopessoa.getAllPessoas();
		}else {
			pessoaList = daopessoa.getPessoasByNome((String)request.getParameter("pesq"));
		}
		String stringToReplace =  "";
		for(Pessoa pessoa: pessoaList) {
			stringToReplace = stringToReplace + "<tr>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getNome() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getSexo() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getData_nascimento() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getCpf() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getSenha() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_rua() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_bairro() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_numero() + "</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\">" +pessoa.getEnd_complemento() +"</td>";
			stringToReplace += "<td style=\"border: 1px solid black;\"> <input type=\"button\" value=\"Edit\" onclick=\"window.location.href='http://localhost:8080/CrudWed_1VAProgIII/EditarServelet?id="+pessoa.getId()+"'\" /> </td>";
			stringToReplace += "<td style=\"border: 1px solid black;\"> <input type=\"button\" value=\"Delete\" onclick=\"window.location.href='http://localhost:8080/CrudWed_1VAProgIII/DeletarServelet?id="+pessoa.getId()+"'\" /></td>";
			stringToReplace += "<td style=\"border: 1px solid black;\"> <input type=\"button\" value=\"Exibir\" onclick=\"window.location.href='http://localhost:8080/CrudWed_1VAProgIII//SearchCidadeServelet?id_pessoa="+pessoa.getId()+"&nome_pessoa="+ pessoa.getNome()+"'\" /> </td>";
			stringToReplace = stringToReplace + "</tr>";
		}
		html =  html.replace("<%%>", stringToReplace);
		
		out.println(html);
	}

	
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
