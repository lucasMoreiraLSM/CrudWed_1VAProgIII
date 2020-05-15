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
	    	FileController fc = new FileController("CadastroPessoa.html",this.getServletContext().getRealPath(File.separator));
			try {
				String html = fc.getFileFromDisk();
				out.println(html);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }else {
	    	DAOpessoa daopessoa = new DAOpessoa();
	    	Pessoa p =  daopessoa.getPessoaById(Integer.parseInt(request.getParameter("id")));
	    	request.setAttribute("nome",p.getNome());
	    	request.setAttribute("sexo",p.getSexo());
	    	request.setAttribute("data_nascimento",p.getData_nascimento());
	    	request.setAttribute("cpf",p.getCpf());
	    	request.setAttribute("senha",p.getSenha());
	    	request.setAttribute("cpf",p.getEnd_rua());
	    	request.setAttribute("end_rua",p.getEnd_rua());
	    	request.setAttribute("end_bairro",p.getEnd_bairro());
	    	request.setAttribute("end_complemento", p.getEnd_complemento());
	    	
	    	
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
		String nome = request.getParameter("nome");
		String estado = request.getParameter("estado");
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
