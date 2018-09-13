<%-- 
    Document   : jspAccueil
    Created on : 10 sept. 2018, 14:39:35
    Author     : afpa1797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8");
String message = (String) session.getAttribute("message");
String choix = (String) session.getAttribute("choix");
String numContact = (String) session.getAttribute("numContact");
String messageContact = (String) session.getAttribute("messageContact");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>gestion des contacts : menu</title>
        <link href="miseEnPage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action ="ServletControleur?idEcran=1" method="post">
            <fieldset style="width: 80%">
                <legend>Gestion des contacts</legend>
                <div class="divTexte">
                    <label>Num&eacute;ro de contacts :</label>
                    <input type="text" max="5" pattern="[0-9]+" name="numContact" value="" id="numContact"/>
                </div>
                <div class="divTexte">
                    <input type="radio" name="choix" value="modification" id="modif"checked="checked"/>
                    <label for="modif">Modification</label>

                    <input type="radio" name="choix" value="creation" id="creation"/>
                    <label for="creation">Cr&eacute;ation</label>

                    <input type="radio" name="choix" value="suppression" id="supp"/>
                    <label for="supp">Suppression</label>
                    <p>
                        <input type="radio" name="choix" value="listeContact" id="liste"/>
                        <label for="liste">Liste des contacts</label>
                    </p>
                </div>
            </fieldset>
            <div id="envoyer">
                <br>
                <button type="submit" value="envoyer" title="bouton d'envoi">Envoyer</button>
                <br><br>
            </div>
            <div>
                <textarea style="resize: none" name="messageErreur" id="messageErreur" cols="167">
                    <%=messageContact%>                                       
                </textarea>
            </div>
        </form>
    </body>
</html>

