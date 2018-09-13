
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="metierMapping.*, java.util.*" %>

<% request.setCharacterEncoding("utf-8");
    String numContact = (String) session.getAttribute("numContact");
    Contact contact = (Contact) session.getAttribute("contact");
    Vector<Secteur> secteur = (Vector<Secteur>) session.getAttribute("listeSecteur");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification d'un contact</title>
        <link href="miseEnPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action ="ServletControleur?idEcran=3" method="post">
            <fieldset style="width: 80%">
                <legend>Modification du contact <%= numContact%></legend>
                <div><p>
                        <label class="modifContact">Nom :</label>
                        <input type="text" name="nom" id="nomcontact"value="<%=contact.getNom()%>"/>
                    </p>
                    <p>
                        <label class="modifContact">Adresse :</label>
                        <input type="text" name="adresse" id="adresseContact" value="<%=contact.getAdresse()%>"/>
                    </p>
                    <p>
                        <label class="modifContact">Code Postal :</label>
                        <input type="text" name="codePostal" id="codePostal"value="<%=contact.getCodePostal()%>"/>
                    </p>
                    <p>
                        <label class="modifContact">Ville :</label>
                        <input type="text" name="ville" id="villeContact"value="<%=contact.getVille()%>"/>
                    </p>
                </div>
                <div class="divSaisieModif">
                    <label  class="modifContact" for="codeSecteur">Code Secteur :</label>
                    <select name="codeSecteur" id="CodeSecteurContact">
                        <% if (contact.getCodeSecteur() == null)
                            { %>
                        <option selected="selected"></option>
                        <% for (int i = 0; i < secteur.size(); i++)
                            {
                        %>
                        <option><%= secteur.get(i).getCode()%></option>
                        <%}
                        }
                        else
                        {
                        %>
                        <option></option>
                        <% for (int i = 0; i < secteur.size(); i++)
                            {
                                if (contact.getCodeSecteur().compareTo(secteur.get(i).getCode()) == 0)
                                {
                        %>
                        <option selected="selected">
                            <%= secteur.get(i).getCode()%>
                        </option>
                        <%}
                        else
                        {%>
                        <option><%=secteur.get(i).getCode()%></option>
                        <%}
                                }
                            }%>
                    </select>
                </div>
            </fieldset>
            <div id="enregistrer">
                <br>
                <a href="jspRecap.jsp"/><button type="submit" value="enregistrer" title="bouton d'enregistrement">Enregistrer</button>
                <button type='reset' value='Annuler' title="bouton d'annulation" onclick="javascript:location.href = 'jspAccueil.jsp'" >Annuler</button>

                <br><br>
            </div>
            <div>
                <textarea style="resize: none" disabled name="messageModif" id="messageModif" cols="167">                    
                </textarea>
            </div>
        </form>
    </body>
</html>
