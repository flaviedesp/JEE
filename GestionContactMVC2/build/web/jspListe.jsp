

<%@page import="metierMapping.Contact"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");
    //String tableListe = (String) session.getAttribute("tableListe");
    Vector<Contact> listeContact = (Vector<Contact>) session.getAttribute("listeContact");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des contacts</title>
        <link href="miseEnPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>        
        <form action ="ServletControleur?idEcran=2" method="post">
            <div id="contenu">                
                <table>
                    <caption>LISTE DES CONTACTS</caption>
                    <thead>                
                        <tr>
                            <th>NUMERO</th>
                            <th>NOM</th>
                            <th>ADRESSE</th>
                            <th>CODE_POSTAL</th>
                            <th>VILLE</th>
                            <th>CODE_SECTEUR</th>
                        </tr>
                    </thead>  
                    <tbody>
                        <%for (int i = 0; i < listeContact.size(); i++)
                        {%>
                        <tr>
                            <td>
                                <%=listeContact.get(i).getNumero()%>
                            </td>
                            <td>
                                <%=listeContact.get(i).getNom()%>
                            </td>
                            <td>
                                <%=listeContact.get(i).getAdresse()%>
                            </td>
                            <td>
                                <%=listeContact.get(i).getCodePostal()%>
                            </td>
                            <td>
                                <%=listeContact.get(i).getVille()%>
                            </td>
                            <td>
                                <%=listeContact.get(i).getCodeSecteur()%>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
            <div>              
                <a href="jspAccueil.jsp">Retour au menu principal</a></li>
            </div>
        </form>
    </body>
</html>
