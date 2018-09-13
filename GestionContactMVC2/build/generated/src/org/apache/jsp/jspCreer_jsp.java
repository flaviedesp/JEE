package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import metierMapping.*;
import java.util.*;

public final class jspCreer_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 request.setCharacterEncoding("utf-8");
    String numContact = (String) session.getAttribute("numContact");
    Contact contact = (Contact) session.getAttribute("contact");
    Vector<Secteur> secteur = (Vector<Secteur>) session.getAttribute("listeSecteur");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Création d'un contact</title>\n");
      out.write("        <link href=\"miseEnPage.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action =\"ServletControleur?idEcran=5\" method=\"post\">\n");
      out.write("            <fieldset style=\"width: 80%\">\n");
      out.write("                <legend>Modification du contact ");
      out.print( numContact);
      out.write("</legend>\n");
      out.write("                <div><p>\n");
      out.write("                        <label class =\"creerContact\">Numéro :</label>\n");
      out.write("                        <input type=\"text\" name=\"numero\" id=\"numerocontact\"/>\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                        <label class=\"creerContact\">Nom :</label>\n");
      out.write("                        <input type=\"text\" name=\"nom\" id=\"nomcontact\"/>\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                        <label class=\"creerContact\">Adresse :</label>\n");
      out.write("                        <input type=\"text\" name=\"adresse\" id=\"adresseContact\" />\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                        <label class=\"creerContact\">Code Postal :</label>\n");
      out.write("                        <input type=\"text\" name=\"codePostal\" id=\"codePostal\"/>\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                        <label class=\"creerContact\">Ville :</label>\n");
      out.write("                        <input type=\"text\" name=\"ville\" id=\"villeContact\"/>\n");
      out.write("                    </p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"divSaisieCreer\">\n");
      out.write("                    <label  for=\"codeSecteur\">Code Secteur :</label>\n");
      out.write("                    <select name=\"codeSecteur\" id=\"CodeSecteurContact\">\n");
      out.write("                        ");
 if (contact.getCodeSecteur() == null)
                            { 
      out.write("\n");
      out.write("                        <option selected=\"selected\"></option>\n");
      out.write("                        ");
 for (int i = 0; i < secteur.size(); i++)
                            {
                        
      out.write("\n");
      out.write("                        <option>");
      out.print( secteur.get(i).getCode());
      out.write("</option>\n");
      out.write("                        ");
}
                        }                        
                        
      out.write(" \n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("            </fieldset>\n");
      out.write("            <div id=\"enregistrer\">\n");
      out.write("                <br>\n");
      out.write("                <a href=\"jspRecap.jsp\"/><button type=\"submit\" value=\"enregistrer\" title=\"bouton d'enregistrement\">Enregistrer</button>\n");
      out.write("                <button type='reset' value='Annuler' title=\"bouton d'annulation\" onclick=\"javascript:location.href = 'jspAccueil.jsp'\" >Annuler</button>\n");
      out.write("\n");
      out.write("                <br><br>\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <textarea style=\"resize: none\" disabled name=\"messageModif\" id=\"messageModif\" cols=\"167\">                    \n");
      out.write("                </textarea>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
