package Servelets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.websocket.Session;

import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;

import Controle.CidadeController;
import Controle.FileController;
import Controle.PessoaController;
import Model.Cidade;
import Model.Pessoa;

/**
 * Servlet implementation class CadastroServelet
 */
public class CadastroServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CadastroServelet() {
		super();

	}
	
	public void init() {
	
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out =  response.getWriter();
	    if(request.getAttribute("step") == null || request.getAttribute("step") == "1"){
	    	FileController fc = new FileController("CadastroPessoa.html",this.getServletContext().getRealPath(File.separator));
			try {
				String html = fc.getFileFromDisk();
				out.println(html);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }else {
	    	System.out.println("passou else casdastro servelet");

	    	Pessoa p =  new Pessoa();
	    	p.setNome(request.getParameter("nome"));
	    	p.setSexo(request.getParameter("sexo"));
	    	p.setData_nascimento(request.getParameter("data_nascimento"));
	    	p.setCpf(request.getParameter("cpf"));
	    	p.setSenha(request.getParameter("senha"));
	    	p.setEnd_rua(request.getParameter("end_rua"));
	    	p.setEnd_bairro(request.getParameter("end_bairro"));
	    	p.setEnd_numero(request.getParameter("end_numero"));
	    	p.setEnd_complemento(request.getParameter("end_complemento"));
	    	HttpSession session = request.getSession(true);
	    	
	    	session.setAttribute("pessoa", p);
	    	
	    	FileController fc = new FileController("CadastroCidade.html",this.getServletContext().getRealPath(File.separator));
	    	String html = fc.getFileFromDisk();
	    	html = html.replace("<%%>", p.getNome());
	    	out.println(html);
	    }
	   FileController fc = new FileController("CadastroCidade.html",this.getServletContext().getRealPath(File.separator));
		
	 

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PessoaController pc =  new PessoaController();
		CidadeController cc =  new CidadeController();
		//String nome = request.getParameter("nome");
		String estado = request.getParameter("estado");
		Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");
		System.out.println("passou else do post cidade");
		//pc.NovaPessoa(p);
		p = pc.NovaPessoa(p);
		
		Cidade cid = new Cidade();
		cid.setNome(request.getParameter("nome_cidade"));
		cid.setEstado(request.getParameter("estado"));
		cid.setId_pessoa(p.getId());
		cc.AddCidade(cid);
		response.sendRedirect("http://localhost:8080/CrudWed_1VAProgIII/SearchServelet");
		
		doGet(request, response);
	}

}
