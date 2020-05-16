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
import DAO.DAOcidade;
import DAO.DAOpessoa;
import Model.Cidade;
import Model.Pessoa;

/**
 * Servlet implementation class CadastroServelet
 */
public class EditarServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditarServelet() {
		super();

	}
	
	public void init() {
	
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =  response.getWriter();
	    if(request.getAttribute("step") == null || request.getAttribute("step") == "1"){
	    	FileController fc = new FileController("EditPessoa.html",this.getServletContext().getRealPath(File.separator));
	    	String html = fc.getFileFromDisk();

	    	DAOpessoa daopessoa = new DAOpessoa();
	    	Pessoa p =  daopessoa.getPessoaById(Integer.parseInt(request.getParameter("id")));
	    	
	    	html = html.replace("<%idpessoa%>",Integer.toString(p.getId()));
	    	html = html.replace("<%nome%>",p.getNome());
	    	String sexo = p.getSexo();
	    	if(sexo.equals("M")) {
	    		html = html.replace("<%opm%>", "checked");
	    		html = html.replace("<%opf%>", "");
	    	} else {
	    		html = html.replace("<%opm%>", "");
	    		html = html.replace("<%opf%>", "checked");
	    	}
	    	html = html.replace("<%data_nascimento%>",p.getData_nascimento());
	    	html = html.replace("<%cpf%>",p.getCpf());
	    	html = html.replace("<%senha%>",p.getSenha());
	    	html = html.replace("<%end_rua%>",p.getEnd_rua());
	    	html = html.replace("<%end_bairro%>",p.getEnd_bairro());
	    	html = html.replace("<%end_numero%>",p.getEnd_numero());
	    	html = html.replace("<%end_complemento%>",p.getEnd_complemento());
	    	
	    	
	    	
	    	HttpSession session = request.getSession(true);
	    	
	    	session.setAttribute("pessoa", p);
	 
	    	out.println(html);
	    } else {
	    	FileController fc = new FileController("EditCidade.html",this.getServletContext().getRealPath(File.separator));
	    	String html = fc.getFileFromDisk();

	    	DAOcidade daocidade = new DAOcidade();
	    	Cidade c =  daocidade.getCidadeById_pesssoa(Integer.parseInt(request.getParameter("idpessoa")));
	    	System.out.println(c.toString());
	    	html = html.replace("<%id_cidade%>",Integer.toString(c.getId_cidade()));
	    	html = html.replace("<%nome_cidade%>",c.getNome());
	    	html = html.replace("<%estado%>",c.getEstado());
	    	html = html.replace("<%1%>", request.getParameter("nome"));
	 
	    	out.println(html);
	    }
	    
	  		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PessoaController pc =  new PessoaController();
		CidadeController cc =  new CidadeController();
		Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");
		
		//pc.NovaPessoa(p);
		p = pc.NovaPessoa(p);
		
		Cidade cid = new Cidade();
		cid.setNome(request.getParameter("nome"));
		cid.setEstado(request.getParameter("estado"));
		cid.setId_pessoa(p.getId());
		cc.AddCidade(cid);
		System.out.println(p.toString());
		System.out.println(cid.toString());
		System.out.println("bateu");
		
		// doGet(request, response);
	}

}
