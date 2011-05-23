package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');

String returned="";
if(session.getAttribute("errReturn").equals(1))
    {
    returned="Some of the information you entered is wrong";
    }

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <link rel=\"stylesheet\" href=\"style.css\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <body bgcolor=\"#c9c9c9\">\n");
      out.write("\n");
      out.write("        <table border=\"1\" bgcolor=\"#e6e6e6\" align=\"center\" width=\"40%\" height=\"80%\">\n");
      out.write("            <!---Beginning of the main table--->\n");
      out.write("            <tr><td>\n");
      out.write("\n");
      out.write("                    <font align=\"center\" color=\"#dd0000\">\n");
      out.write("                    ");
      out.print(returned );
      out.write("\n");
      out.write("                    </font>\n");
      out.write("                    \n");
      out.write("                    <form align=\"center\" action=\"authorised.jsp\" method=\"get\">\n");
      out.write("                        Username:<br>\n");
      out.write("                        <input type=\"text\" name=\"username\"><br>\n");
      out.write("                        Password:<br>\n");
      out.write("                        <input type=\"password\" name=\"pwd\"><br>\n");
      out.write("                        <input type=\"submit\" value=\"Send\">\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                </td></tr>\n");
      out.write("\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
