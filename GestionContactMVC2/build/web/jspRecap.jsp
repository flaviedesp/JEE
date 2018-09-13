

<%@page import="metierMapping.Contact"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8");
    String numContact = (String) session.getAttribute("numContact");
    String nomcontact = (String)request.getParameter("nomcontact");
    String adresseContact = (String)request.getParameter("adresseContact");
    String codePostal = (String)request.getParameter("codePostal");
    String villeContact = (String)request.getParameter("villeContact");
    String CodeSecteurContact = (String)request.getParameter("CodeSecteurContact");
    Vector<Contact> listeContact = (Vector<Contact>) session.getAttribute("listeContact");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="miseEnPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action ="ServletControleur?idEcran=4" method="post">
            <h2>Enregistrement du contact <%= numContact%> effectu&eacute;</h2><br>

            <h2>R&eacute;capitulatif des donn&eacute;es entr&eacute;es</h2>

            <h5><%=nomcontact%></h5>
            <h5><%=adresseContact%></h5>
            <h5><%=codePostal%></h5>
            <h5><%=villeContact%></h5>
            <h5><%=CodeSecteurContact%></h5>
            <br>
            <div>              
                <a href="jspAccueil.jsp">Retour au menu principal</a></li>
            </div>
        </form>
    </body>
</html>
