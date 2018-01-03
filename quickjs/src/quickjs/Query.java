package quickjs;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public class Query extends AbstractTemplate
{
	public void serve(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Writer wr=new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		setWriter(wr);
		generateResponse(request);
		wr.close();
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
	}

	private void generateResponse(HttpServletRequest request) throws IOException {
		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements())
		{
			String name=names.nextElement();
			writeObject(name);
			write(": ");
			writeObject(request.getParameter(name));
			write("<br/>\n");
		}
//		write("<!DOCTYPE html>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">\n<head>\n<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n\n<title>Example minimal we server</title>\n\n<script type=\"text/javascript\">\ninputhandler=function(event)\n{\n\tconsole.info(\"target: \"+event.target.id+\" \"+event.target.value);\n//\tdocument.getElementById(\"myform\").submit();\n\t\tvar xhr = new XMLHttpRequest();\n\t\txhr.responseType = \"text\";\n\t\txhr.onreadystatechange = function() {\n\t\t\tif (this.readyState == 4 && this.status == 200) {\n\t\t\t\tconsole.info(\"RESPONSE: \"+this.responseText);\n\t\t\t}\n\t\t}.bind(xhr);\n\t\txhr.open(\"GET\",\"query\");\n\t\txhr.send(new FormData( document.getElementById(\"myform\") ) );\n}\nwindow.onload = function(e){ \n\tvar inputs, index;\n\t\n\tinputs = document.getElementsByTagName('input');\n\tfor (index = 0; index < inputs.length; ++index) {\n\t\tvar i=inputs[index];\n\t\ti.onchange = inputhandler;\n\t\ti.onkeypress=inputhandler;\n\t\ti.onpaste=inputhandler;\n\t\ti.oninput=inputhandler;\n\t}\n}\n</script>\n\n</head>\n\n\n<h1>Example minimal Web server</h1>\n<form id=\"myform\" action=\"myform\">\n<table>\n");
//		for(int i=0;i<11;++i){
//			write("<tr><td>\n<input id=\"input-");
//			writeObject(i);
//			write("-1\">line ");
//			writeObject(i);
//			write(" row 1</input></td><td><input id=\"input-");
//			writeObject(i);
//			write("-2\">Line ");
//			writeObject(i);
//			write(" row 2</input></td></tr>\n");
//		}
//		write("</table>\n</form>\n\n</html>\n");
	}
}
