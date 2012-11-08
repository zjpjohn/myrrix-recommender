/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: JspCServletContext/1.0
 * Generated at: 2012-11-08 17:53:46 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package net.myrrix.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import net.myrrix.online.ServerRecommender;
import net.myrrix.web.servlets.AbstractMyrrixServlet;
import net.myrrix.online.generation.Generation;
import net.myrrix.online.som.SelfOrganizingMaps;
import net.myrrix.online.som.Node;
import org.apache.mahout.common.Pair;
import java.util.Iterator;
import java.util.List;

public final class som_jspx extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private static String percentageToHexByte(double d) {
  int value = (int) (256 * d);
  if (value > 255) {
    value = 255;
  }
  if (value < 16) {
    return "0" + byteToHexChar(value);
  }
  return String.valueOf(byteToHexChar(value / 16)) + byteToHexChar(value % 16);
}
private static char byteToHexChar(int value) {
  if (value < 10) {
    return (char) ('0' + value);
  }
  return (char) ('A' + (value - 10));
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>");
      out.write("<html>");
      out.write("<!-- Copyright 2012 Myrrix. See myrrix.com/legal for license. -->");
      out.write("<head>");
      out.write("<title>");
      out.write("Myrrix Serving Layer");
      out.write("</title>");
      out.write("<style type=\"text/css\">");
      out.write("\nbody {background-color:#202020;border:0;padding:0;margin:0}\ntable.htt,th.htt,tr.htt,td.htt {border:none;border-spacing:0;border-collapse:collapse}\ntd.htt {width:16px;height:16px;line-height:30%}\ntd.htt div {display:none;padding:10px;font-family:'Gill Sans','Gill Sans MT',Arial,sans-serif;line-height:100%;position:absolute;background-color:white;opacity:0.5}\ntd.htt:hover div, td.htt div:hover {display:block}\n");
      out.write("</style>");
      out.write("</head>");
      out.write("<body>");

String mapSizeParam = request.getParameter("mapSize");
int mapSize = mapSizeParam == null ? 40 : Integer.parseInt(mapSizeParam);
boolean userMatrix = "user".equalsIgnoreCase(request.getParameter("matrix"));
ServerRecommender rec = (ServerRecommender) application.getAttribute(AbstractMyrrixServlet.RECOMMENDER_KEY);
Generation generation = rec.getGenerationManager().getCurrentGeneration();
if (generation != null) {
  Node[][] map = new SelfOrganizingMaps().buildSelfOrganizedMap(userMatrix ? generation.getX() : generation.getY(),
                                                                mapSize);

      out.write("<table class=\"htt\">");
 for (Node[] mapRow : map) { 
      out.write("<tr class=\"htt\">");

      for (Node node : mapRow) {
        float[] projection3D = node.getProjection3D();
        String hexColor = '#' +
            percentageToHexByte(projection3D[0]) +
            percentageToHexByte(projection3D[1]) +
            percentageToHexByte(projection3D[2]);
        pageContext.setAttribute("hexColor", hexColor);
    
      out.write("<td style=\"" + (java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("background-color:${hexColor}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false) + "\" class=\"htt\">");

        List<Pair<Double,Long>> contentIDs = node.getAssignedIDs();
        int n = Math.min(16, contentIDs.size());
        int rowSize = n <= 4 ? 2 : n <= 9 ? 3 : 4;
        if (n > 0) {
      
      out.write("<div>");

        Iterator<Pair<Double,Long>> it = contentIDs.iterator();
        int k = 0;
        while (k < 9 && it.hasNext()) {
          out.write(String.valueOf(it.next().getSecond()));
          out.write(' ');
          if ((k+1) % rowSize == 0 && k != n-1) {
            out.write("<br/>");
          }
          k++;
        }
        if (it.hasNext()) {
          out.write("...");
        }
        
      out.write("</div>");

        k = 0;
        while (k < n) {
          out.write('·');
          if ((k+1) % rowSize == 0 && k != n-1) {
            out.write("<br/>");
          }
          k++;
        }
        
 } 
      out.write("</td>");
 } 
      out.write("</tr>");
 } 
      out.write("</table>");
 } 
      out.write("</body>");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
