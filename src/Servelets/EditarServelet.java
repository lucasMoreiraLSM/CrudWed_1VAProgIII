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
	    	
	 
	    	out.println(html);
	    } else {
	    	Pessoa p =  new Pessoa();
	    	p.setId(Integer.parseInt(request.getParameter("idpessoa")));
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
	    	
	    	FileController fc = new FileController("EditCidade.html",this.getServletContext().getRealPath(File.separator));
	    	String html = fc.getFileFromDisk();

	    	DAOcidade daocidade = new DAOcidade();
	    	Cidade c =  daocidade.getCidadeById_pesssoa(Integer.parseInt(request.getParameter("idpessoa")));
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
		System.out.println(p.toString());
		//pc.NovaPessoa(p);
	   //p = pc.NovaPessoa(p);
		pc.UpdatePessoa(p);
		
		Cidade cid = new Cidade();
		cid.setId_cidade(Integer.parseInt(request.getParameter("id_cidade")));
		cid.setNome(request.getParameter("nome_cidade"));
		cid.setEstado(request.getParameter("estado"));
		cid.setId_pessoa(p.getId());
		//cc.AddCidade(cid);
		cc.UpdateCidade(cid);
		System.out.println(p.toString());
		
		// doGet(request, response);
		response.sendRedirect("http://localhost:8080/CrudWed_1VAProgIII/SearchServelet");
	}

}
