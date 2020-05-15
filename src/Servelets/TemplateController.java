package Servelets;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;

import org.apache.commons.io.FileUtils;

@SuppressWarnings("serial")
public class TemplateController {
	public String getStringFromTemplate(HttpServlet servlet,String TemplateName) {
		String path = servlet.getServletContext().getRealPath(File.separator)+File.separator+"templates"+File.separator+TemplateName;
		File headerTamplate = new File(path);
		String htmlString = "";
		try {
			htmlString =  FileUtils.readFileToString(headerTamplate,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return htmlString;
	}

}
