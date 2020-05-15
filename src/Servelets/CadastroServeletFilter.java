package Servelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.NotNull;

/**
 * Servlet Filter implementation class CadastroServeletFilter
 */
public class CadastroServeletFilter implements Filter {
	

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp =  (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request; 
		resp.setContentType("text/html; charset=ISO-8859-1");
		//TODO atribuir isso ao modelo
		//--------------
		ArrayList<String> mandatoryFields = new ArrayList<String>();
		ArrayList<String> errorFields = new ArrayList<String>();
		mandatoryFields.add("nome");
		mandatoryFields.add("data_nascimento");
		mandatoryFields.add("cpf");
		mandatoryFields.add("senha");
		
		//--------------
		for(String mandatoryField: mandatoryFields) {
			if( req.getParameter(mandatoryField) == null || req.getParameter(mandatoryField) == "") {
				errorFields.add(mandatoryField);
			}
		}
		if(errorFields.isEmpty()) {
			req.setAttribute("step","2");
			chain.doFilter(req, resp);
		} else {
			chain.doFilter(req, resp);
		}
		
	}
		

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
